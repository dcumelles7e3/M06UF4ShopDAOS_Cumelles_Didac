package m06.uf4.DAO.proveidor;

import m06.uf4.DAOFactory.DAOFactorySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveidorImpSQL implements ProveidorDAO{
    Connection conexion;

    public ProveidorImpSQL() { conexion = DAOFactorySQL.crearConexion();
    }

    @Override
    public boolean insertar(Proveidor prov) {
        boolean result = false;
        String sql = "INSERT INTO PROV VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, prov.getIdProveidor());
            sentencia.setString(2, prov.getNom());
            sentencia.setString(3, prov.getAdreca());
            sentencia.setString(4, prov.getCiutat());
            sentencia.setString(5, prov.getEstat());
            sentencia.setString(6, prov.getCodiPostal());
            sentencia.setInt(7, prov.getArea());
            sentencia.setString(8, prov.getTelefon());
            sentencia.setInt(9, prov.getIdProducte());
            sentencia.setInt(10, prov.getQuantitat());
            sentencia.setFloat(11, prov.getLimitCredit());
            sentencia.setString(12, prov.getObservacions());
            int filas = sentencia.executeUpdate();
            result = true;
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insertarLlista(List<Proveidor> provs) {
        int contador = 0;
        String sql = "INSERT INTO PROV VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            for (Proveidor prov : provs) {
                sentencia.setInt(1, prov.getIdProveidor());
                sentencia.setString(2, prov.getNom());
                sentencia.setString(3, prov.getAdreca());
                sentencia.setString(4, prov.getCiutat());
                sentencia.setString(5, prov.getEstat());
                sentencia.setString(6, prov.getCodiPostal());
                sentencia.setInt(7, prov.getArea());
                sentencia.setString(8, prov.getTelefon());
                sentencia.setInt(9, prov.getIdProducte());
                sentencia.setInt(10, prov.getQuantitat());
                sentencia.setFloat(11, prov.getLimitCredit());
                sentencia.setString(12, prov.getObservacions());
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
    public boolean eliminar(int IdProveidor) {
        boolean result = false;
        String sql = "DELETE FROM PROV WHERE ID_PROV = ?";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,IdProveidor);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                result = true;
                System.out.println("Proveidors eliminats: " + filas);
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
        String sql = "DELETE FROM PROV";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                result = true;
                System.out.println("Proveidors eliminats: " + filas);
            }
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean modificarQuantitat(Proveidor prov) {
        PreparedStatement sentencia;
        boolean result = false;
        String sql = "UPDATE PROV SET QUANTITAT = ? where ID_PROV = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, prov.getQuantitat());
            sentencia.setInt(2, prov.getIdProveidor());
            sentencia.executeUpdate();
            result = true;
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Proveidor consultar(int IdProveidor) {
        PreparedStatement sentencia;
        Proveidor prov = new Proveidor();
        String sql = "SELECT ID_PROV, NOM, ADRECA, CIUTAT, ESTAT, CODI_POSTAL, AREA, TELEFON, ID_PRODUCTE, QUANTITAT, LIMIT_CREDIT, OBSERVACIONS from PROV WHERE ID_PROV = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,IdProveidor);
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                prov.setIdProveidor(rs.getInt("ID_PROV"));
                prov.setNom(rs.getString("NOM"));
                prov.setAdreca(rs.getString("ADRECA"));
                prov.setCiutat(rs.getString("CIUTAT"));
                prov.setEstat(rs.getString("ESTAT"));
                prov.setCodiPostal(rs.getString("CODI_POSTAL"));
                prov.setArea(rs.getInt("AREA"));
                prov.setTelefon(rs.getString("TELEFON"));
                prov.setIdProducte(rs.getInt("ID_PRODUCTE"));
                prov.setQuantitat(rs.getInt("QUANTITAT"));
                prov.setLimitCredit(rs.getFloat("LIMIT_CREDIT"));
                prov.setObservacions(rs.getString("OBSERVACIONS"));
            }

            rs.close();
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prov;
    }

    @Override
    public List<Proveidor> consultarLlista() {
        PreparedStatement sentencia;
        List<Proveidor> listaProveidors = new ArrayList<>();
        String sql = "SELECT ID_PROV, NOM, ADRECA, CIUTAT, ESTAT, CODI_POSTAL, AREA, TELEFON, ID_PRODUCTE, QUANTITAT, LIMIT_CREDIT, OBSERVACIONS from PROV";
        try {
            sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                Proveidor c = new Proveidor();
                c.setIdProveidor(rs.getInt("ID_PROV"));
                c.setNom(rs.getString("NOM"));
                c.setAdreca(rs.getString("ADRECA"));
                c.setCiutat(rs.getString("CIUTAT"));
                c.setEstat(rs.getString("ESTAT"));
                c.setCodiPostal(rs.getString("CODI_POSTAL"));
                c.setArea(rs.getInt("AREA"));
                c.setTelefon(rs.getString("TELEFON"));
                c.setIdProducte(rs.getInt("ID_PRODUCTE"));
                c.setQuantitat(rs.getInt("QUANTITAT"));
                c.setLimitCredit(rs.getFloat("LIMIT_CREDIT"));
                c.setObservacions(rs.getString("OBSERVACIONS"));
                System.out.println(c);
                listaProveidors.add(c);
            }

            rs.close();
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaProveidors;
    }

    @Override
    public Proveidor consultarPerIdProducte(int productID) {
        PreparedStatement sentencia;
        Proveidor prov = new Proveidor();
        String sql = "SELECT ID_PROV, NOM, ADRECA, CIUTAT, ESTAT, CODI_POSTAL, AREA, TELEFON, ID_PRODUCTE, QUANTITAT, LIMIT_CREDIT, OBSERVACIONS from PROV WHERE ID_PRODUCTE = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,productID);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                prov.setIdProveidor(rs.getInt("ID_PROV"));
                prov.setNom(rs.getString("NOM"));
                prov.setAdreca(rs.getString("ADRECA"));
                prov.setCiutat(rs.getString("CIUTAT"));
                prov.setEstat(rs.getString("ESTAT"));
                prov.setCodiPostal(rs.getString("CODI_POSTAL"));
                prov.setArea(rs.getInt("AREA"));
                prov.setTelefon(rs.getString("TELEFON"));
                prov.setIdProducte(rs.getInt("ID_PRODUCTE"));
                prov.setQuantitat(rs.getInt("QUANTITAT"));
                prov.setLimitCredit(rs.getFloat("LIMIT_CREDIT"));
                prov.setObservacions(rs.getString("OBSERVACIONS"));
            }
            rs.close();
            sentencia.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prov;
    }
}
