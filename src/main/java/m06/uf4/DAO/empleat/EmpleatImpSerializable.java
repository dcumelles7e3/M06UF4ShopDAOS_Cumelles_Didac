package m06.uf4.DAO.empleat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleatImpSerializable implements EmpleatDAO {
    File file;
    public EmpleatImpSerializable() {
        file = new File("empleats.dat");
    }

    @Override
    public boolean insertar(Empleat emp) {
        System.out.println("No implementat");
        return false;
    }

    @Override
    public int insertarLlista(List<Empleat> emps) {
        //IMPLEMENTAR
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
        //IMPLEMENTAR

        return llista;
    }
}
