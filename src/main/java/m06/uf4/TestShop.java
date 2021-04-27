package m06.uf4;

import m06.uf4.DAO.comanda.*;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.empleat.EmpleatImpMongo;
import m06.uf4.DAO.empleat.EmpleatImpSQL;
import m06.uf4.DAO.empleat.EmpleatImpSerializable;
import m06.uf4.DAO.producte.*;
import m06.uf4.DAO.proveidor.*;
import m06.uf4.DAOFactory.DAOFactory;

import java.util.Date;
import java.util.List;

public class TestShop {
    static EmpleatDAO empSql;
    static EmpleatDAO empSer;
    static EmpleatDAO empMon;
    static ComandaDAO comSql;
    static ComandaDAO comSer;
    static ComandaDAO comMon;
    static ProducteDAO prodSql;
    static ProducteDAO prodSer;
    static ProducteDAO prodMon;
    static ProveidorDAO provSql;
    static ProveidorDAO provSer;
    static ProveidorDAO provMon;

    public static void main(String[] args) {
        empSql = DAOFactory.MYSQL //new EmpleatImpSQL();
        empSer = new EmpleatImpSerializable();
        empMon = new EmpleatImpMongo();
        comSql = new ComandaImpSQL();
        comSer = new ComandaImpSerializable();
        comMon = new ComandaImpMongo();
        prodSql = new ProducteImpSQL();
        prodSer = new ProducteImpSerializable();
        prodMon = new ProducteImpMongo();
        provSql = new ProveidorImpSQL();
        provSer = new ProveidorImpSerializable();
        provMon = new ProveidorImpMongo();

        eliminarMongo();

        //Ex 1
        System.out.println("Exercici 1");
        empSer.insertarLlista(empSql.consultarLlista());

        //Ex2
        System.out.println("Exercici 2");
        empMon.insertarLlista(empSer.consultarLlista());

        //Ex3
        System.out.println("Exercici 3");
        comSer.insertarLlista(comSql.consultarLlista());

        //Ex4
        System.out.println("Exercici 4");
        comMon.insertarLlista(comSer.consultarLlista());

        //Ex5
        System.out.println("Exercici 5");
        prodSer.insertarLlista(prodSql.consultarLlista());

        //Ex6
        System.out.println("Exercici 6");
        prodMon.insertarLlista(prodSer.consultarLlista());

        //Ex7
        System.out.println("Exercici 7");
        provSer.insertarLlista(provSql.consultarLlista());

        //Ex8
        System.out.println("Exercici 8");
        provMon.insertarLlista(provSer.consultarLlista());

        System.out.println("Exercici 9");
        ex9();

        System.out.println("Exercici 10");
        ex10();

        System.out.println("Exercici 11");
        ex11();
    }

    static void ex9() {
        for (Comanda comanda : comSql.consultarLlista()) {
            int quantitat = comanda.getQuantitat();
            float preu = prodSql.consultar(comanda.getIdProducte()).getPreu();
            comanda.setTotal(preu * quantitat);
            comSql.modificar(comanda);
        }
        System.out.println("Llista modificada");
        comSql.consultarLlista();
    }

    static void ex10() {
        Producte prod = prodSql.consultar(102130);
        Comanda comanda = new Comanda(700, prod.getIdProducte(), dataActual(), 50, 106, dataActual(), 50 * prod.getPreu());
        prod.addPropertyChangeListener(comanda);
        prod.setStockActual(3);
    }

    static void ex11() {
        Producte prod = prodSql.consultar(200376);
        System.out.println(prod);
        Comanda comanda = new Comanda(701, prod.getIdProducte(), dataActual(), 100, 107, dataActual(), 100 * prod.getPreu());
        System.out.println(comanda);
        Proveidor prov = provSql.consultar(comanda.getIdProveidor());
        System.out.println(prov);
        prod.addPropertyChangeListener(comanda);
        prod.addPropertyChangeListener(prov);
        prod.setStockActual(3);
        provSql.consultar(comanda.getIdProveidor());
    }

    static void eliminarMongo() {
        empMon.eliminarConjunt();
        comMon.eliminarConjunt();
        prodMon.eliminarConjunt();
        provMon.eliminarConjunt();
    }

    static java.sql.Date dataActual(){
        java.util.Date utilDate = new java.util.Date();
        return new java.sql.Date(utilDate.getTime());
    }
}
