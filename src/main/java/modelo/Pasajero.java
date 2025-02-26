package modelo;


public class Pasajero {
    private String BOLNRO;
    private String VIANRO;
    private String NOMPASA;
    private int NROASIE;
    private String TIPO;
    private double PAGO;
    
    public Pasajero(String BOLNRO, String VIANRO, String NOMPASA, int NROASIE, String TIPO, double PAGO) {
        this.BOLNRO = BOLNRO;
        this.VIANRO = VIANRO;
        this.NOMPASA = NOMPASA;
        this.NROASIE = NROASIE;
        this.TIPO = TIPO;
        this.PAGO = PAGO;
    }

    public String getBOLNRO() {
        return BOLNRO;
    }

    public void setBOLNRO(String BOLNRO) {
        this.BOLNRO = BOLNRO;
    }

    public String getVIANRO() {
        return VIANRO;
    }

    public void setVIANRO(String VIANRO) {
        this.VIANRO = VIANRO;
    }

    public String getNOMPASA() {
        return NOMPASA;
    }

    public void setNOMPASA(String NOMPASA) {
        this.NOMPASA = NOMPASA;
    }

    public int getNROASIE() {
        return NROASIE;
    }

    public void setNROASIE(int NROASIE) {
        this.NROASIE = NROASIE;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public double getPAGO() {
        return PAGO;
    }

    public void setPAGO(double PAGO) {
        this.PAGO = PAGO;
    }
    
}
