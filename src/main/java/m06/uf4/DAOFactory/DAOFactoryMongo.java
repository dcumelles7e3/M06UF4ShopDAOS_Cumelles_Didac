package m06.uf4.DAOFactory;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import m06.uf4.DAO.comanda.ComandaDAO;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.empleat.EmpleatImpMongo;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.proveidor.ProveidorDAO;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class DAOFactoryMongo extends DAOFactory {

    static MongoDatabase db = null;
    static final String HOST = "cluster0.AAAAAAAA.mongodb.net";
    static final String DATABASE = "shop";
    static final String USER = "";
    static final String PASSWORD = "";

    public DAOFactoryMongo() {}

    public static MongoDatabase connectaBD() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        try {
            MongoClient client = MongoClients.create("mongodb+srv://" + USER + ":" + PASSWORD + "@" + HOST + "/retryWrites=true&w=majority");
            db = client.getDatabase(DATABASE).withCodecRegistry(pojoCodecRegistry);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return db;
    }

    @Override
    public EmpleatDAO getEmpleatDAO() {
        return new EmpleatImpMongo();
    }




}