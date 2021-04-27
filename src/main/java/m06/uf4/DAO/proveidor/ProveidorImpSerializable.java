package m06.uf4.DAO.proveidor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProveidorImpSerializable implements ProveidorDAO {
    File file;
    public ProveidorImpSerializable() {
        file = new File("shopFitxers/proveidors.dat");
    }

    @Override
    public boolean insertar(Proveidor prov) {
        return false;
    }

    @Override
    public int insertarLlista(List<Proveidor> provs) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (Proveidor prov : provs) {
                outputStream.writeObject(prov);
            }
            outputStream.close();
            System.out.printf("Inserits %d proveidors\n",provs.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return provs.size();
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
        List<Proveidor> llista = new ArrayList<>();
        Proveidor emp;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                emp = (Proveidor) objectInputStream.readObject();
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

    @Override
    public Proveidor consultarPerIdProducte(int productID) {
        return null;
    }
}
