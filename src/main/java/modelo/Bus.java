package modelo;

public class Bus {
    private int BUSNRO;
    private String PLACA;
    private int CAPACIDAD; 

    public Bus(int BUSNRO, String PLACA, int CAPACIDAD) {
        this.BUSNRO = BUSNRO;
        this.PLACA = PLACA;
        this.CAPACIDAD = CAPACIDAD;
    }

    public int getBUSNRO() {
        return BUSNRO;
    }

    public void setBUSNRO(int BUSNRO) {
        this.BUSNRO = BUSNRO;
    }

    public String getPLACA() {
        return PLACA;
    }

    public void setPLACA(String PLACA) {
        this.PLACA = PLACA;
    }

    public int getCAPACIDAD() {
        return CAPACIDAD;
    }

    public void setCAPACIDAD(int CAPACIDAD) {
        this.CAPACIDAD = CAPACIDAD;
    }
 
}
