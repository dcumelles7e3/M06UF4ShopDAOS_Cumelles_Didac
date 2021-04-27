package m06.uf4.DAO.empleat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleatImpSerializable implements EmpleatDAO {
    File file;
    public EmpleatImpSerializable() {
        file = new File("shopFitxers/empleats.dat");
    }

    @Override
    public boolean insertar(Empleat emp) {
        System.out.println("No implementat");
        return false;
    }

    @Override
    public int insertarLlista(List<Empleat> emps) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (Empleat emp : emps) {
                outputStream.writeObject(emp);
            }
            System.out.printf("Inserits %d empleats\n",emps.size());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emps.size();
    }

    @Override
    public boolean eliminar(int empId) {
        System.out.println("No implementat");
        return false;
    }

    @Override
    public boolean eliminarConjunt() {
        System.out.println("No implementat");
        return true;
    }

    @Override
    public boolean modificar(Empleat emp) {
        System.out.println("No implementat");
        return false;
    }

    @Override
    public Empleat consultar(int empId) {
        System.out.println("No implementat");
        return null;
    }

    @Override
    public List<Empleat> consultarLlista() {
        List<Empleat> llista = new ArrayList<>();
        Empleat emp;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                emp = (Empleat) objectInputStream.readObject();
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
