package m06.uf4.DAO.proveidor;

import m06.uf4.DAOFactory.DAOFactorySQL;

import java.sql.Connection;
import java.util.List;

public class ProveidorImpSQL implements ProveidorDAO{
    Connection conexion;

    public ProveidorImpSQL() { conexion = DAOFactorySQL.crearConexion();
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
