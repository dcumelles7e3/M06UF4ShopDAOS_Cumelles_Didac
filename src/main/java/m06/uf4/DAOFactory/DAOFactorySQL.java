package m06.uf4.DAOFactory;

import m06.uf4.DAO.comanda.ComandaDAO;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.empleat.EmpleatImpSQL;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.proveidor.ProveidorDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactorySQL extends DAOFactory {

    static Connection conexion = null;
    static final String URL = "";
    static final String BD = "shop";
    static final String USER = "";
    static final String PASSWORD = "";

    public DAOFactorySQL() {
    }

    public static Connection crearConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL + BD, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conexion;
    }

    @Override
    public EmpleatDAO getEmpleatDAO() {
        return new EmpleatImpSQL();
    }

}