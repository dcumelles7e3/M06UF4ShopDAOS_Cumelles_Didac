package m06.uf4.DAO.comanda;

import com.mongodb.client.MongoDatabase;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAOFactory.DAOFactoryMongo;

import java.util.List;

public class ComandaImpMongo implements ComandaDAO {
    MongoDatabase mongoDB;
    String coleccio = "comandes";

    public ComandaImpMongo() {
        mongoDB = DAOFactoryMongo.connectaBD();
    }

    @Override
    public boolean insertar(Comanda comanda) {
        return false;
    }

    @Override
    public int insertarLlista(List<Comanda> comandes) {
        return 0;
    }

    @Override
    public boolean eliminar(int comandaId) {
        return false;
    }

    @Override
    public boolean eliminarConjunt() {
        return false;
    }

    @Override
    public boolean modificar(Comanda comanda) {
        return false;
    }

    @Override
    public Comanda consultar(int comandaId) {
        return null;
    }

    @Override
    public List<Comanda> consultarLlistaPerProducte(int productID) {
        return null;
    }

    @Override
    public List<Comanda> consultarLlista() {
        return null;
    }
}
