package m06.uf4.DAO.proveidor;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAOFactory.DAOFactoryMongo;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ProveidorImpMongo implements ProveidorDAO {
    MongoDatabase mongoDB;
    String coleccio = "proveidors";

    public ProveidorImpMongo() {
        mongoDB = DAOFactoryMongo.connectaBD();
    }

    @Override
    public boolean insertar(Proveidor emps) {
        boolean resultat = false;
        try {
            MongoCollection<Document> collection = mongoDB.getCollection(coleccio);
            Gson gson = new Gson();
            String json = gson.toJson(emps);
            collection.insertOne(Document.parse(json));
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    @Override
    public int insertarLlista(List<Proveidor> emps) {
        int contador = 0;
        Gson gson = new Gson();
        MongoCollection<Document> collection = mongoDB.getCollection(coleccio);
        for (Proveidor e : emps) {
            String json = gson.toJson(e);
            collection.insertOne(Document.parse(json));
            contador++;
        }
        return contador;
    }

    @Override
    public boolean eliminar(int empId) {
        MongoCollection<Document> collectionProduct = mongoDB.getCollection(coleccio);
        boolean resultat = false;
        try {
            DeleteResult deleteResult = collectionProduct.deleteOne(eq("ID_PROV",empId));
            System.out.println("Se han borrado: "+deleteResult.getDeletedCount());
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;

    }

    @Override
    public boolean eliminarConjunt() {
        MongoCollection mongoCollection = mongoDB.getCollection(coleccio);
        mongoCollection.drop();
        System.out.println("Collecció borrada");
        return true;
    }

    @Override
    public boolean modificarQuantitat(Proveidor prov) {
        MongoCollection<Document> collectionProduct = mongoDB.getCollection(coleccio);
        boolean result = false;
        try {
            UpdateResult updateResult = collectionProduct.updateOne(eq("ID_PROV", prov.getIdProveidor()), set("QUANTITAT", prov.getQuantitat()));
            result = true;
            System.out.println("Se han actualizado: " + updateResult.getModifiedCount());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Proveidor consultar(int empId) {
        Gson gson = new Gson();
        Document listGrades;
        listGrades = mongoDB.getCollection(coleccio).find(new Document("ID_PROV", empId)).first();
        return gson.fromJson(listGrades.toJson(), Proveidor.class);
    }

    @Override
    public List<Proveidor> consultarLlista() {
        Gson gson = new Gson();
        List<Proveidor> listaProductos = new ArrayList<>();
        FindIterable<Document> listGrades;
        listGrades = mongoDB.getCollection(coleccio).find();
        listGrades.forEach( document -> listaProductos.add(gson.fromJson(document.toJson(), Proveidor.class)));
        return listaProductos;
    }

    @Override
    public Proveidor consultarPerIdProducte(int productID) {
        Gson gson = new Gson();
        Document listGrades;
        listGrades = mongoDB.getCollection(coleccio).find(new Document("ID_PRODUCTE", productID)).first();
        return gson.fromJson(listGrades.toJson(), Proveidor.class);
    }
}
