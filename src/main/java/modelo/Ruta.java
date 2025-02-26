package modelo;

public class Ruta {
    private String RUTCOD;
    private String ORIGEN = "CIX";
    private String RUTNOM;
    private double RUTCOS;

    public Ruta(String RUTCOD, String RUTNOM, double RUTCOS) {
        this.RUTCOD = RUTCOD;
        this.ORIGEN = ORIGEN;
        this.RUTNOM = RUTNOM;
        this.RUTCOS = RUTCOS;
    }

    public String getRUTCOD() {
        return RUTCOD;
    }

    public void setRUTCOD(String RUTCOD) {
        this.RUTCOD = RUTCOD;
    }

    public String getORIGEN() {
        return ORIGEN;
    }

    public void setORIGEN(String ORIGEN) {
        this.ORIGEN = ORIGEN;
    }

    public String getRUTNOM() {
        return RUTNOM;
    }

    public void setRUTNOM(String RUTNOM) {
        this.RUTNOM = RUTNOM;
    }

    public double getRUTCOS() {
        return RUTCOS;
    }

    public void setRUTCOS(double RUTCOS) {
        this.RUTCOS = RUTCOS;
    }
 
}

