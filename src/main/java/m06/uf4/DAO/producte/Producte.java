package m06.uf4.DAO.producte;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/*
 * ID_PRODUCTE    INT (6) UNSIGNED PRIMARY KEY,
                                             DESCRIPCIO     VARCHAR (30) NOT NULL  UNIQUE,
                                             STOCKACTUAL    INT (8) UNSIGNED,
                                             STOCKMINIM     INT (8) UNSIGNED,
                                             PREU           DECIMAL(8,2) UNSIGNED);
 */
public class Producte implements Serializable {

    private PropertyChangeSupport propertySupport;

    int idProducte;
    String descripcio;
    int stockActual;
    int stockMinim;
    float preu;

    public Producte() {
        this.propertySupport = new PropertyChangeSupport(this);
    }

    public Producte(int idProducte, String descripcio, int stockActual, int stockMinim, float preu) {
        this.idProducte = idProducte;
        this.descripcio = descripcio;
        this.stockActual = stockActual;
        this.stockMinim = stockMinim;
        this.preu = preu;
        this.propertySupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertySupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertySupport.removePropertyChangeListener(listener);
    }

    public int getIdProducte() {
        return idProducte;
    }

    public void setIdProducte(int idProducte) {
        this.idProducte = idProducte;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinim() {
        return stockMinim;
    }

    public void setStockMinim(int stockMinim) {
        this.stockMinim = stockMinim;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }
}
