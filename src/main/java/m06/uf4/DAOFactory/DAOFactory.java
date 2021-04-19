package m06.uf4.DAOFactory;

import m06.uf4.DAO.comanda.ComandaDAO;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.proveidor.ProveidorDAO;

public abstract class DAOFactory {
    public static final int MYSQL = 1;
    public static final int MONGODB = 2;
    public static final int SERIALIZABLE = 3;

    public abstract EmpleatDAO getEmpleatDAO();

    public static DAOFactory getDAOFactory(int bd) {
        switch (bd) {
            case MYSQL:
                return new DAOFactorySQL();
            case MONGODB:
                return new DAOFactoryMongo();
            case SERIALIZABLE:
                return new DAOFactorySerializable();
            default :
                return null;
        }
    }
}