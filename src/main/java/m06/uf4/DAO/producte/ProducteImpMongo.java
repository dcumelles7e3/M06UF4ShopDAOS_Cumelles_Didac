package m06.uf4.DAO.producte;

import com.mongodb.client.MongoDatabase;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAOFactory.DAOFactoryMongo;

import java.util.List;

public class ProducteImpMongo implements ProducteDAO {
    MongoDatabase mongoDB;
    String coleccio = "productes";

    public ProducteImpMongo() {
        mongoDB = DAOFactoryMongo.connectaBD();
    }


    @Override
    public boolean insertar(Producte product) {
        return false;
    }

    @Override
    public int insertarLlista(List<Producte> prods) {
        return 0;
    }

    @Override
    public boolean eliminar(int productId) {
        return false;
    }

    @Override
    public boolean eliminarConjunt() {
        return false;
    }

    @Override
    public boolean modificarStock(Producte product) {
        return false;
    }

    @Override
    public Producte consultar(int productID) {
        return null;
    }

    @Override
    public List<Producte> consultarLlista() {
        return null;
    }
}
