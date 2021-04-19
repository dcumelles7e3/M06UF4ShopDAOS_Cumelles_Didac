package m06.uf4.DAO.producte;

import m06.uf4.DAO.proveidor.Proveidor;
import m06.uf4.DAO.proveidor.ProveidorDAO;
import m06.uf4.DAOFactory.DAOFactorySQL;

import java.sql.Connection;
import java.util.List;

public class ProducteImpSQL implements ProducteDAO {
    Connection conexion;

    public ProducteImpSQL() { conexion = DAOFactorySQL.crearConexion();
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
