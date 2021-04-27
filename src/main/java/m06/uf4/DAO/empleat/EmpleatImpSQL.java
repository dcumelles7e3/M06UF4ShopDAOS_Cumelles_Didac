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
        boolean result = false;
        String sql = "INSERT INTO EMPLEAT VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, emps.getEmplatID());
            sentencia.setString(2, emps.getCognom());
            sentencia.setString(3, emps.getOfici());
            sentencia.setInt(4, emps.getCapId());
            sentencia.setDate(5, emps.getDataAlta());
            sentencia.setInt(6, emps.getSalari());
            sentencia.setInt(7, emps.getComissio());
            sentencia.setInt(8, emps.getDepNo());
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                result = true;
                System.out.printf("Empleat %d afegit%n", emps.getEmplatID());
            }
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public int insertarLlista(List<Empleat> emps) {
        int contador = 0;
        String sql = "INSERT INTO EMPLEAT VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            for (Empleat emp : emps) {
                sentencia.setInt(1, emp.getEmplatID());
                sentencia.setString(2, emp.getCognom());
                sentencia.setString(3, emp.getOfici());
                sentencia.setInt(4, emp.getCapId());
                sentencia.setDate(5, emp.getDataAlta());
                sentencia.setInt(6, emp.getSalari());
                sentencia.setInt(7, emp.getComissio());
                sentencia.setInt(8, emp.getDepNo());
                int filas = sentencia.executeUpdate();
                contador++;
            }
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contador;
    }

    @Override
    public boolean eliminar(int empId) {
        boolean result = false;
        String sql = "DELETE FROM EMPLEAT WHERE EMPLEAT_ID = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, empId);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                result = true;
                System.out.printf("Empleat%d eliminat%n", empId);
            }
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean eliminarConjunt() {
        boolean result = false;
        String sql = "DELETE FROM EMPLEAT";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                result = true;
                System.out.println("Empleats eliminats: "+filas);
            }
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean modificar(Empleat emp) {
        PreparedStatement sentencia;
        boolean result = false;
        String sql = "UPDATE EMPLEAT SET COGNOM = ?, OFICI = ?, CAP_ID = ?, DATA_ALTA = ?, SALARI = ?, COMISSIO = ?, DEPT_NO = ? where EMPLEAT_ID = ?";
        try {
            sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(8, emp.getEmplatID());
            sentencia.setString(1, emp.getCognom());
            sentencia.setString(2, emp.getOfici());
            sentencia.setInt(3, emp.getCapId());
            sentencia.setDate(4, emp.getDataAlta());
            sentencia.setInt(5, emp.getSalari());
            sentencia.setInt(6, emp.getComissio());
            sentencia.setInt(7, emp.getDepNo());

            sentencia.executeUpdate();
            result = true;
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Empleat consultar(int empId) {
        PreparedStatement sentencia;
        Empleat emp = new Empleat();
        String sql = "SELECT EMPLEAT_ID, COGNOM, OFICI, CAP_ID, DATA_ALTA, SALARI, COMISSIO, DEPT_NO from EMPLEAT where EMPLEAT_ID = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,empId);
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                emp.setEmplatID(rs.getShort("EMPLEAT_ID"));
                emp.setCognom(rs.getString("COGNOM"));
                emp.setOfici(rs.getString("OFICI"));
                emp.setCapId(rs.getInt("CAP_ID"));
                emp.setDataAlta(rs.getDate("DATA_ALTA"));
                emp.setSalari(rs.getInt("SALARI"));
                emp.setComissio(rs.getInt("COMISSIO"));
                emp.setDepNo(rs.getInt("DEPT_NO"));
                System.out.println(emp);
            }

            rs.close();
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emp;
    }

    @Override
    public List<Empleat> consultarLlista() {
        PreparedStatement sentencia;
        List<Empleat> listaEmpleados = new ArrayList<>();
        String sql = "SELECT EMPLEAT_ID, COGNOM, OFICI, CAP_ID, DATA_ALTA, SALARI, COMISSIO, DEPT_NO from EMPLEAT";
        try {
            sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                Empleat e = new Empleat();
                e.setEmplatID(rs.getShort("EMPLEAT_ID"));
                e.setCognom(rs.getString("COGNOM"));
                e.setOfici(rs.getString("OFICI"));
                e.setCapId(rs.getInt("CAP_ID"));
                e.setDataAlta(rs.getDate("DATA_ALTA"));
                e.setSalari(rs.getInt("SALARI"));
                e.setComissio(rs.getInt("COMISSIO"));
                e.setDepNo(rs.getInt("DEPT_NO"));
                listaEmpleados.add(e);
                System.out.println(e);
            }

            rs.close();
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaEmpleados;
    }
}
