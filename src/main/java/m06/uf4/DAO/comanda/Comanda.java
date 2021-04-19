package m06.uf4.DAO.comanda;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.sql.Date;


/*
    ID_COMANDA          SMALLINT(4) UNSIGNED PRIMARY KEY,
                                             ID_PRODUCTE 	     INT (6) UNSIGNED,
                                             DATA_COMANDA        DATE,
                                             QUANTITAT           INT (8) UNSIGNED,
                                             ID_PROV          INT (6) UNSIGNED NOT NULL,
                                             DATA_TRAMESA        DATE,
                                             TOTAL               DECIMAL(8,2) UNSIGNED);
 */
public class Comanda implements Serializable, PropertyChangeListener {

    int idComanda;
    int idProducte;
    Date dataComanda;
    int quantitat;
    int idProveidor;
    Date dataTramesa;
    float total;

    public Comanda() {
    }

    public Comanda(int idComanda, int idProducte, Date dataComanda, int quantitat, int idProveidor, Date dataTramesa, float total) {
        this.idComanda = idComanda;
        this.idProducte = idProducte;
        this.dataComanda = dataComanda;
        this.quantitat = quantitat;
        this.idProveidor = idProveidor;
        this.dataTramesa = dataTramesa;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "idComanda=" + idComanda +
                ", idProducte=" + idProducte +
                ", dataComanda=" + dataComanda +
                ", quantitat=" + quantitat +
                ", idProveidor=" + idProveidor +
                ", dataTramesa=" + dataTramesa +
                ", total=" + total +
                '}';
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public int getIdProducte() {
        return idProducte;
    }

    public void setIdProducte(int idProducte) {
        this.idProducte = idProducte;
    }

    public Date getDataComanda() {
        return dataComanda;
    }

    public void setDataComanda(Date dataComanda) {
        this.dataComanda = dataComanda;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public int getIdProveidor() {
        return idProveidor;
    }

    public void setIdProveidor(int idProveidor) {
        this.idProveidor = idProveidor;
    }

    public Date getDataTramesa() {
        return dataTramesa;
    }

    public void setDataTramesa(Date dataTramesa) {
        this.dataTramesa = dataTramesa;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("[COMANDA] - Event capturat, fent insert de comanda");
        ComandaDAO mysql = new ComandaImpSQL();
        mysql.insertar(this);
    }
}
