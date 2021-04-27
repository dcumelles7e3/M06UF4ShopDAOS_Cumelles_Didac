package m06.uf4.DAO.producte;

import m06.uf4.DAO.proveidor.Proveidor;
import m06.uf4.DAO.proveidor.ProveidorDAO;
import m06.uf4.DAOFactory.DAOFactorySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducteImpSQL implements ProducteDAO {
    Connection conexion;

    public ProducteImpSQL() { conexion = DAOFactorySQL.crearConexion();
    }


    @Override
    public boolean insertar(Producte product) {
        boolean result = false;
        String sql = "INSERT INTO PRODUCTE VALUES(?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, product.getIdProducte());
            sentencia.setString(2, product.getDescripcio());
            sentencia.setInt(3, product.getStockActual());
            sentencia.setInt(4, product.getStockMinim());
            sentencia.setFloat(5, product.getPreu());
            sentencia.executeUpdate();
            result = true;
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insertarLlista(List<Producte> llistaProductes) {
        int contador = 0;
        String sql = "INSERT INTO PRODUCTE VALUES(?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            for (Producte emp : llistaProductes) {
                sentencia.setInt(1, emp.getIdProducte());
                sentencia.setString(2, emp.getDescripcio());
                sentencia.setInt(3, emp.getStockActual());
                sentencia.setInt(4, emp.getStockMinim());
                sentencia.setFloat(5, emp.getPreu());
                sentencia.executeUpdate();
                contador++;
            }
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contador;
    }

    @Override
    public boolean eliminar(int productId) {
        boolean result = false;
        String sql = "DELETE FROM PRODUCTE WHERE ID_PRODUCTE = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, productId);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                result = true;
                System.out.printf("Producte%d eliminat%n", productId);
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
        String sql = "DELETE FROM PRODUCTE";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                result = true;
                System.out.println("Productes eliminats: " + filas);
            }
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean modificarStock(Producte product) {
        PreparedStatement sentencia;
        boolean result = false;
        String sql = "UPDATE PRODUCTE SET STOCKACTUAL = ? where ID_PRODUCTE = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, product.getStockActual());
            sentencia.setInt(2, product.getIdProducte());
            sentencia.executeUpdate();
            result = true;
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Producte consultar(int productID) {
        PreparedStatement sentencia;
        Producte c = new Producte();
        String sql = "SELECT ID_PRODUCTE, DESCRIPCIO, STOCKACTUAL, STOCKMINIM, PREU from PRODUCTE where ID_PRODUCTE = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,productID);
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                c.setIdProducte(rs.getInt("ID_PRODUCTE"));
                c.setDescripcio(rs.getString("DESCRIPCIO"));
                c.setStockActual(rs.getInt("STOCKACTUAL"));
                c.setStockMinim(rs.getInt("STOCKMINIM"));
                c.setPreu(rs.getFloat("PREU"));
            }

            rs.close();
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public List<Producte> consultarLlista() {
        PreparedStatement sentencia;
        List<Producte> listaProductes = new ArrayList<>();
        String sql = "SELECT ID_PRODUCTE, DESCRIPCIO, STOCKACTUAL, STOCKMINIM, PREU from PRODUCTE";
        try {
            sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                Producte c = new Producte();
                c.setIdProducte(rs.getInt("ID_PRODUCTE"));
                c.setDescripcio(rs.getString("DESCRIPCIO"));
                c.setStockActual(rs.getInt("STOCKACTUAL"));
                c.setStockMinim(rs.getInt("STOCKMINIM"));
                c.setPreu(rs.getFloat("PREU"));
                System.out.println(c);
                listaProductes.add(c);
            }

            rs.close();
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaProductes;
    }
}
