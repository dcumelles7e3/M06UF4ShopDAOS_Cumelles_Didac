package m06.uf4.DAO.producte;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProducteImpSerializable implements ProducteDAO {
    File file;
    public ProducteImpSerializable() {
        file = new File("shopFitxers/productes.dat");
    }

    @Override
    public boolean insertar(Producte product) {
        return false;
    }

    @Override
    public int insertarLlista(List<Producte> llistaProductes) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (Producte prod : llistaProductes) {
                outputStream.writeObject(prod);
            }
            outputStream.close();
            System.out.printf("Inserits %d productes\n",llistaProductes.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return llistaProductes.size();
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
        List<Producte> llista = new ArrayList<>();
        Producte emp;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                emp = (Producte) objectInputStream.readObject();
                llista.add(emp);
            }
        } catch (Exception e) {
            try {
                objectInputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("Fi del fitxer");
        }

        return llista;
    }
}
