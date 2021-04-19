package m06.uf4.DAO.comanda;

import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ComandaImpSerializable implements ComandaDAO {
    File file;
    public ComandaImpSerializable() {
        file = new File("comandes.dat");
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
