package m06.uf4.DAO.producte;

import m06.uf4.DAO.empleat.Empleat;

import java.util.List;

public interface ProducteDAO {
    public boolean insertar(Producte product);
    public int insertarLlista(List<Producte> prods);

    public boolean eliminar(int productId);
    public boolean eliminarConjunt();

    public boolean modificarStock(Producte product);

    public Producte consultar(int productID);
    public List<Producte> consultarLlista();

}
