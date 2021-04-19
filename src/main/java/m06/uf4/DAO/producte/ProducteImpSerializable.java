package m06.uf4.DAO.producte;

import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProducteImpSerializable implements ProducteDAO {
    File file;
    public ProducteImpSerializable() {
        file = new File("productes.dat");
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
