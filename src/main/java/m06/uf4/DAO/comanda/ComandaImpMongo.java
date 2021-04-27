package m06.uf4.DAO.comanda;

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
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class ComandaImpMongo implements ComandaDAO {
    MongoDatabase mongoDB;
    String coleccio = "comandes";

    public ComandaImpMongo() {
        mongoDB = DAOFactoryMongo.connectaBD();
    }

    @Override
    public boolean insertar(Comanda emps) {
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
    public int insertarLlista(List<Comanda> emps) {
        int contador = 0;
        Gson gson = new Gson();
        MongoCollection<Document> collection = mongoDB.getCollection(coleccio);
        for (Comanda e : emps) {
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
            DeleteResult deleteResult = collectionProduct.deleteMany(eq("ID_COMANDA",empId));
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
    public boolean modificar(Comanda emp) {
        MongoCollection<Document> collectionProduct = mongoDB.getCollection(coleccio);
        boolean result = false;
        try {
            UpdateResult updateResult = collectionProduct.updateMany(eq("ID_COMANDA", emp.getIdComanda()), combine(set("ID_PRODUCTE", emp.getIdProducte()
            ), set("DATA_COMANDA", emp.getDataComanda()), set("QUANTITAT", emp.getQuantitat()), set("ID_PROV", emp.getIdProveidor()), set("DATA_TRAMESA", emp.getDataTramesa()), set("TOTAL", emp.getTotal())));
            result = true;
            System.out.println("Actualitzats: " + updateResult.getModifiedCount());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Comanda consultar(int empId) {
        Gson gson = new Gson();
        Document comandes;
        comandes = mongoDB.getCollection(coleccio).find(new Document("ID_COMANDA", empId)).first();
        return gson.fromJson(comandes.toJson(), Comanda.class);
    }

    @Override
    public List<Comanda> consultarLlistaPerProducte(int productID) {
        Gson gson = new Gson();
        List<Comanda> listaComandes = new ArrayList<>();
        FindIterable<Document> comandes;
        comandes = mongoDB.getCollection(coleccio).find(new Document("ID_PRODUCTE",productID));
        comandes.forEach( document -> listaComandes.add(gson.fromJson(document.toJson(), Comanda.class)));
        return listaComandes;
    }

    @Override
    public List<Comanda> consultarLlista() {
        Gson gson = new Gson();
        List<Comanda> listaComandes = new ArrayList<>();
        FindIterable<Document> comandes;
        comandes = mongoDB.getCollection(coleccio).find();
        comandes.forEach( document -> listaComandes.add(gson.fromJson(document.toJson(), Comanda.class)));
        return listaComandes;
    }
}
