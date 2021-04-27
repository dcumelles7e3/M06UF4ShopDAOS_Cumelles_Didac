package m06.uf4.DAO.proveidor;

import m06.uf4.DAO.comanda.ComandaDAO;
import m06.uf4.DAO.comanda.ComandaImpSQL;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

/*
    ID_PROV      int(6) unsigned        not null, primary key,
    NOM          varchar(45)            not null,
    ADRECA       varchar(40)            not null,
    CIUTAT       varchar(30)            not null,
    ESTAT        varchar(2)             null,
    CODI_POSTAL  varchar(9)             not null,
    AREA         smallint(3)            null,
    TELEFON      varchar(9)             null,
    ID_PRODUCTE  int(6) unsigned        null,
    QUANTITAT    int(8) unsigned        null,
    LIMIT_CREDIT decimal(9, 2) unsigned null,
    OBSERVACIONS text                   null
     */
public class Proveidor implements Serializable, PropertyChangeListener{

    int idProveidor;
    String nom;
    String adreca;
    String ciutat;
    String estat;
    String codiPostal;
    int area;
    String telefon;
    int idProducte;
    int quantitat;
    float limitCredit;
    String observacions;

    public Proveidor() {
    }

    public Proveidor(int idProveidor, String nom, String adreca, String ciutat, String estat, String codiPostal, int area, String telefon, int idProducte, int quantitat, float limitCredit, String observacions) {
        this.idProveidor = idProveidor;
        this.nom = nom;
        this.adreca = adreca;
        this.ciutat = ciutat;
        this.estat = estat;
        this.codiPostal = codiPostal;
        this.area = area;
        this.telefon = telefon;
        this.idProducte = idProducte;
        this.quantitat = quantitat;
        this.limitCredit = limitCredit;
        this.observacions = observacions;
    }

    @Override
    public String toString() {
        return "Proveidor{" +
                "idProveidor=" + idProveidor +
                ", nom='" + nom + '\'' +
                ", adreca='" + adreca + '\'' +
                ", ciutat='" + ciutat + '\'' +
                ", estat='" + estat + '\'' +
                ", codiPostal='" + codiPostal + '\'' +
                ", area=" + area +
                ", telefon='" + telefon + '\'' +
                ", idProducte=" + idProducte +
                ", quantitat=" + quantitat +
                ", limitCredit=" + limitCredit +
                ", observacions='" + observacions + '\'' +
                '}';
    }

    public int getIdProveidor() {
        return idProveidor;
    }

    public void setIdProveidor(int idProveidor) {
        this.idProveidor = idProveidor;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal(String codiPostal) {
        this.codiPostal = codiPostal;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getIdProducte() {
        return idProducte;
    }

    public void setIdProducte(int idProducte) {
        this.idProducte = idProducte;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public float getLimitCredit() {
        return limitCredit;
    }

    public void setLimitCredit(float limitCredit) {
        this.limitCredit = limitCredit;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        System.out.println("[PROVEIDOR] - Event capturat, fent insert de proveidor");
        ProveidorDAO mysql = new ProveidorImpSQL();
        System.out.println("Quantitat anterior: "+quantitat);
        quantitat = quantitat + (int) propertyChangeEvent.getNewValue();
        System.out.println("Quantitat actual: "+quantitat);
        mysql.modificarQuantitat(this);
    }
}
