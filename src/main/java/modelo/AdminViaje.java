package modelo;

import java.sql.Time;
import java.util.Date;

public class AdminViaje {
    private int id;
    private int idBus;
    private int idRuta;
    private String idChofer;
    private Time horaSalida;
    private Date fecha;
    private Time horaLlegada;

    // Constructor y getters/setters
    public AdminViaje(int id, int idBus, int idRuta, String idChofer, Time horaSalida, Date fecha, Time horaLlegada) {
        this.id = id;
        this.idBus = idBus;
        this.idRuta = idRuta;
        this.idChofer = idChofer;
        this.horaSalida = horaSalida;
        this.fecha = fecha;
        this.horaLlegada = horaLlegada;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdBus() { return idBus; }
    public void setIdBus(int idBus) { this.idBus = idBus; }

    public int getIdRuta() { return idRuta; }
    public void setIdRuta(int idRuta) { this.idRuta = idRuta; }

    public String getIdChofer() { return idChofer; }
    public void setIdChofer(String idChofer) { this.idChofer = idChofer; }

    public Time getHoraSalida() { return horaSalida; }
    public void setHoraSalida(Time horaSalida) { this.horaSalida = horaSalida; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Time getHoraLlegada() { return horaLlegada; }
    public void setHoraLlegada(Time horaLlegada) { this.horaLlegada = horaLlegada; }
}
