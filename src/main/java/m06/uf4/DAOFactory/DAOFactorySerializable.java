package m06.uf4.DAOFactory;

import m06.uf4.DAO.comanda.ComandaDAO;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.empleat.EmpleatImpSerializable;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.proveidor.ProveidorDAO;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class DAOFactorySerializable extends DAOFactory {

    public DAOFactorySerializable() {}


    @Override
    public EmpleatDAO getEmpleatDAO() {
        return new EmpleatImpSerializable();
    }




}