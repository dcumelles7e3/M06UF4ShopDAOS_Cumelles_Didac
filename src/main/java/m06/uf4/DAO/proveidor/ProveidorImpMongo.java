package m06.uf4.DAO.proveidor;

import com.mongodb.client.MongoDatabase;
import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAOFactory.DAOFactoryMongo;

import java.util.List;

public class ProveidorImpMongo implements ProveidorDAO {
    MongoDatabase mongoDB;
    String coleccio = "proveidors";

    public ProveidorImpMongo() {
        mongoDB = DAOFactoryMongo.connectaBD();
    }


    @Override
    public boolean insertar(Proveidor prov) {
        return false;
    }

    @Override
    public int insertarLlista(List<Proveidor> provs) {
        return 0;
    }

    @Override
    public boolean eliminar(int idProv) {
        return false;
    }

    @Override
    public boolean eliminarConjunt() {
        return false;
    }

    @Override
    public boolean modificarQuantitat(Proveidor prov) {
        return false;
    }

    @Override
    public Proveidor consultar(int idProv) {
        return null;
    }

    @Override
    public List<Proveidor> consultarLlista() {
        return null;
    }

    @Override
    public Proveidor consultarPerIdProducte(int productID) {
        return null;
    }
}
