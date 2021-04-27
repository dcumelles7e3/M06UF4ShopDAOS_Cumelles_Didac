package m06.uf4.DAO.comanda;

import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ComandaImpSerializable implements ComandaDAO {
    File file;
    public ComandaImpSerializable() {
        file = new File("shopFitxers/comandes.dat");
    }

    @Override
    public boolean insertar(Comanda comanda) {
        return false;
    }

    @Override
    public int insertarLlista(List<Comanda> comandes) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (Comanda emp : comandes) {
                outputStream.writeObject(emp);
            }
            outputStream.close();
            System.out.printf("Inserides %d comandes\n",comandes.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comandes.size();
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
        List<Comanda> llista = new ArrayList<>();
        Comanda emp;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                emp = (Comanda) objectInputStream.readObject();
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
