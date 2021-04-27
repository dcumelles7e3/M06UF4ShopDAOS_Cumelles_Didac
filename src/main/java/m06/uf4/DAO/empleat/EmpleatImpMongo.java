package m06.uf4.DAO.empleat;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import m06.uf4.DAOFactory.DAOFactoryMongo;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class EmpleatImpMongo implements EmpleatDAO {
    MongoDatabase mongoDB;
    String coleccio = "empleats";

    public EmpleatImpMongo() {
        mongoDB = DAOFactoryMongo.connectaBD();
    }

    @Override
    public boolean insertar(Empleat emp) {
        boolean resultat = false;
        try {
            MongoCollection<Document> collection = mongoDB.getCollection(coleccio);
            Gson gson = new Gson();
            String json = gson.toJson(emp);
            collection.insertOne(Document.parse(json));
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    @Override
    public int insertarLlista(List<Empleat> emps) {
        Gson gson = new Gson();
        MongoCollection<Document> collection = mongoDB.getCollection(coleccio);
        for (Empleat e : emps) {
            String json = gson.toJson(e);
            collection.insertOne(Document.parse(json));
        }
        System.out.printf("Inserits %d empleats\n",emps.size());
        return emps.size();
    }

    @Override
    public boolean eliminar(int empId) {
        MongoCollection<Document> collectionProduct = mongoDB.getCollection(coleccio);
        boolean resultat = false;
        try {
            DeleteResult deleteResult = collectionProduct.deleteMany(eq("EMPLEAT_ID",empId));
            System.out.println("S'han eliminat: "+deleteResult.getDeletedCount());
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
    public boolean modificar(Empleat emp) {
        MongoCollection<Document> collectionProduct = mongoDB.getCollection(coleccio);
        boolean result = false;
        try {
            UpdateResult updateResult = collectionProduct.updateMany(eq("EMPLEAT_ID", emp.getEmplatID()), combine(set("COGNOM", emp.getCognom()
            ), set("OFICI", emp.getOfici()), set("CAP_ID", emp.getCapId()), set("DATA_ALTA", emp.getDataAlta()), set("SALARI", emp.getSalari()), set("COMISSIO", emp.getComissio()), set("DEPT_NO", emp.getDepNo())));
            result = true;
            System.out.println("Actualitzats: " + updateResult.getModifiedCount());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Empleat consultar(int empId) {
        Gson gson = new Gson();
        Document listGrades;
        listGrades = mongoDB.getCollection(coleccio).find(new Document("EMPLEAT_ID", empId)).first();
        return gson.fromJson(listGrades.toJson(), Empleat.class);
    }

    @Override
    public List<Empleat> consultarLlista() {
        Gson gson = new Gson();
        List<Empleat> listaEmpleados = new ArrayList<>();
        FindIterable<Document> listGrades;
        listGrades = mongoDB.getCollection(coleccio).find();
        listGrades.forEach( document -> listaEmpleados.add(gson.fromJson(document.toJson(), Empleat.class)));
        return listaEmpleados;
    }
}
