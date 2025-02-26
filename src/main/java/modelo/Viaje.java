package modelo;

import java.sql.Date;
import java.sql.Time;

public class Viaje {
    private String VIANRO;
    private int BUSNRO;
    private String RUTCOD;
    private String IDCOD;
    private Time VIAHRS;
    private Date VIAFCH;
    private double COSVIA;

    public Viaje(String VIANRO, int BUSNRO, String RUTCOD, String IDCOD, Time VIAHRS, Date VIAFCH, double COSVIA) {
        this.VIANRO = VIANRO;
        this.BUSNRO = BUSNRO;
        this.RUTCOD = RUTCOD;
        this.IDCOD = IDCOD;
        this.VIAHRS = VIAHRS;
        this.VIAFCH = VIAFCH;
        this.COSVIA = COSVIA;
    }

    public String getVIANRO() {
        return VIANRO;
    }

    public void setVINRO(String VIANRO) {
        this.VIANRO = VIANRO;
    }

    public int getBUSNRO() {
        return BUSNRO;
    }

    public void setBUSNRO(int BUSNRO) {
        this.BUSNRO = BUSNRO;
    }

    public String getRUTCOD() {
        return RUTCOD;
    }

    public void setRUTCOD(String RUTCOD) {
        this.RUTCOD = RUTCOD;
    }

    public String getIDCOD() {
        return IDCOD;
    }

    public void setIDCOD(String IDCOD) {
        this.IDCOD = IDCOD;
    }

    public Time getVIAHRS() {
        return VIAHRS;
    }

    public void setVIAHRS(Time VIAHRS) {
        this.VIAHRS = VIAHRS;
    }

    public Date getVIAFCH() {
        return VIAFCH;
    }

    public void setVIAFCH(Date VIAFCH) {
        this.VIAFCH = VIAFCH;
    }

    public double getCOSVIA() {
        return COSVIA;
    }

    public void setCOSVIA(double COSVIA) {
        this.COSVIA = COSVIA;
    }
  
}
