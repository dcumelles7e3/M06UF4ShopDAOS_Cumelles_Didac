package m06.uf4.DAO.producte;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAOFactory.DAOFactoryMongo;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ProducteImpMongo implements ProducteDAO {
    MongoDatabase mongoDB;
    String coleccio = "productes";

    public ProducteImpMongo() {
        mongoDB = DAOFactoryMongo.connectaBD();
    }

    @Override
    public boolean insertar(Producte emps) {
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
    public int insertarLlista(List<Producte> productes) {
        Gson gson = new Gson();
        MongoCollection<Document> collection = mongoDB.getCollection(coleccio);
        for (Producte p : productes) {
            String json = gson.toJson(p);
            collection.insertOne(Document.parse(json));
        }
        return productes.size();
    }

    @Override
    public boolean eliminar(int empId) {
        MongoCollection<Document> collectionProduct = mongoDB.getCollection(coleccio);
        boolean resultat = false;
        try {
            DeleteResult deleteResult = collectionProduct.deleteOne(eq("ID_PRODUCTE",empId));
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
    public boolean modificarStock(Producte product) {
        MongoCollection<Document> collectionProduct = mongoDB.getCollection(coleccio);
        boolean result = false;
        try {
            UpdateResult updateResult = collectionProduct.updateOne(eq("ID_PRODUCTE", product.getIdProducte()), set("STOCKACTUAL", product.getStockActual()));
            result = true;
            System.out.println("Se han actualizado: " + updateResult.getModifiedCount());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Producte consultar(int empId) {
        Gson gson = new Gson();
        Document listGrades;
        listGrades = mongoDB.getCollection(coleccio).find(new Document("ID_PRODUCTE", empId)).first();
        return gson.fromJson(listGrades.toJson(), Producte.class);
    }

    @Override
    public List<Producte> consultarLlista() {
        Gson gson = new Gson();
        List<Producte> listaProductos = new ArrayList<>();
        FindIterable<Document> listGrades;
        listGrades = mongoDB.getCollection(coleccio).find();
        listGrades.forEach( document -> listaProductos.add(gson.fromJson(document.toJson(), Producte.class)));
        return listaProductos;
    }
}
