package m06.uf4.DAO.empleat;

import m06.uf4.DAOFactory.DAOFactorySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// EMPLEAT_ID, COGNOM, OFICI, CAP, DATA_ALTA, SALARI, COMISSIO, DEPT_NO

public class EmpleatImpSQL implements EmpleatDAO {
    Connection conexion;

    public EmpleatImpSQL() { conexion = DAOFactorySQL.crearConexion();
    }

    @Override
    public boolean insertar(Empleat emps) {
        return false;
    }

    @Override
    public int insertarLlista(List<Empleat> emps) {
        return 0;
    }

    @Override
    public boolean eliminar(int empId) {
        return false;
    }

    @Override
    public boolean eliminarConjunt() {
        return false;
    }

    @Override
    public boolean modificar(Empleat emp) {
        return false;
    }

    @Override
    public Empleat consultar(int empId) {
        return null;
    }

    @Override
    public List<Empleat> consultarLlista() {
        return null;
    }
}
