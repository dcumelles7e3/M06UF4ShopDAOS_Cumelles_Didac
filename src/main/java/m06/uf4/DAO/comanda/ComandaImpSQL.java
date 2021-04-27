package m06.uf4.DAO.comanda;

import m06.uf4.DAOFactory.DAOFactorySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComandaImpSQL implements ComandaDAO{
    Connection conexion;

    public ComandaImpSQL() {
        conexion = DAOFactorySQL.crearConexion();
    }

    @Override
    public boolean insertar(Comanda comanda) {
        boolean result = false;
        String sql = "INSERT INTO COMANDA VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, comanda.getIdComanda());
            sentencia.setInt(2, comanda.getIdProducte());
            sentencia.setDate(3, comanda.getDataComanda());
            sentencia.setInt(4, comanda.getQuantitat());
            sentencia.setInt(5, comanda.getIdProveidor());
            sentencia.setDate(6, comanda.getDataTramesa());
            sentencia.setFloat(7, comanda.getTotal());
            int filas = sentencia.executeUpdate();
            result = true;

            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insertarLlista(List<Comanda> comandes) {
        int contador = 0;
        String sql = "INSERT INTO COMANDA VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            for (Comanda emp : comandes) {
                sentencia.setInt(1, emp.getIdComanda());
                sentencia.setInt(2, emp.getIdProducte());
                sentencia.setDate(3, emp.getDataComanda());
                sentencia.setInt(4, emp.getQuantitat());
                sentencia.setInt(5, emp.getIdProveidor());
                sentencia.setDate(6, emp.getDataTramesa());
                sentencia.setFloat(7, emp.getTotal());
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
    public boolean eliminar(int comandaId) {
        boolean result = false;
        String sql = "DELETE FROM COMANDA WHERE ID_COMANDA = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, comandaId);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                result = true;
                System.out.printf("Comanda %d eliminada %n", comandaId);
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
        String sql = "DELETE FROM COMANDA";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                result = true;
                System.out.println("Comandes eliminats: " + filas);
            }
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean modificar(Comanda comanda) {
        PreparedStatement sentencia;
        boolean result = false;
        String sql = "UPDATE COMANDA SET ID_PRODUCTE = ?, DATA_COMANDA = ?, QUANTITAT = ?, ID_PROV = ?, DATA_TRAMESA = ?, TOTAL = ? where ID_COMANDA = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, comanda.getIdProducte());
            sentencia.setDate(2, comanda.getDataComanda());
            sentencia.setInt(3, comanda.getQuantitat());
            sentencia.setInt(4, comanda.getIdProveidor());
            sentencia.setDate(5, comanda.getDataTramesa());
            sentencia.setFloat(6, comanda.getTotal());
            sentencia.setInt(7, comanda.getIdComanda());
            sentencia.executeUpdate();
            result = true;
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Comanda consultar(int comandaId) {
        PreparedStatement sentencia;
        Comanda c = new Comanda();
        String sql = "SELECT ID_COMANDA, ID_PRODUCTE, DATA_COMANDA, QUANTITAT, ID_PROV, DATA_TRAMESA, TOTAL from COMANDA WHERE ID_COMANDA = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, comandaId);
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                c.setIdComanda(rs.getShort("ID_COMANDA"));
                c.setIdProducte(rs.getInt("ID_PRODUCTE"));
                c.setDataComanda(rs.getDate("DATA_COMANDA"));
                c.setQuantitat(rs.getInt("QUANTITAT"));
                c.setIdProveidor(rs.getInt("ID_PROV"));
                c.setDataTramesa(rs.getDate("DATA_TRAMESA"));
                c.setTotal(rs.getFloat("TOTAL"));
            }
            rs.close();
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Comanda> consultarLlistaPerProducte(int productID) {
        PreparedStatement sentencia;
        List<Comanda> listaComandas = new ArrayList<>();
        String sql = "SELECT ID_COMANDA, ID_PRODUCTE, DATA_COMANDA, QUANTITAT, ID_PROV, DATA_TRAMESA, TOTAL from COMANDA WHERE ID_PRODUCTE = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, productID);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                Comanda c = new Comanda();
                c.setIdComanda(rs.getShort("ID_COMANDA"));
                c.setIdProducte(rs.getInt("ID_PRODUCTE"));
                c.setDataComanda(rs.getDate("DATA_COMANDA"));
                c.setQuantitat(rs.getInt("QUANTITAT"));
                c.setIdProveidor(rs.getInt("ID_PROV"));
                c.setDataTramesa(rs.getDate("DATA_TRAMESA"));
                c.setTotal(rs.getFloat("TOTAL"));
                System.out.println(c);
                listaComandas.add(c);
            }

            rs.close();
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaComandas;
    }

    @Override
    public List<Comanda> consultarLlista() {
        PreparedStatement sentencia;
        List<Comanda> llistaComandes = new ArrayList<>();
        String sql = "SELECT ID_COMANDA, ID_PRODUCTE, DATA_COMANDA, QUANTITAT, ID_PROV, DATA_TRAMESA, TOTAL from COMANDA";
        try {
            sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                Comanda c = new Comanda();
                c.setIdComanda(rs.getShort("ID_COMANDA"));
                c.setIdProducte(rs.getInt("ID_PRODUCTE"));
                c.setDataComanda(rs.getDate("DATA_COMANDA"));
                c.setQuantitat(rs.getInt("QUANTITAT"));
                c.setIdProveidor(rs.getInt("ID_PROV"));
                c.setDataTramesa(rs.getDate("DATA_TRAMESA"));
                c.setTotal(rs.getFloat("TOTAL"));
                System.out.println(c);
                llistaComandes.add(c);
            }

            rs.close();
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return llistaComandes;
    }
}
