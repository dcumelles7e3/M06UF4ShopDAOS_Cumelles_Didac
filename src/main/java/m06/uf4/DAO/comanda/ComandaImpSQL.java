package m06.uf4.DAO.comanda;

import m06.uf4.DAOFactory.DAOFactorySQL;

import java.sql.Connection;
import java.util.List;

public class ComandaImpSQL implements ComandaDAO{
    Connection conexion;

    public ComandaImpSQL() {
        conexion = DAOFactorySQL.crearConexion();
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
