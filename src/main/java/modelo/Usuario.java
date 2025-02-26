package modelo;

public class Usuario {
    private int id;
    private String nombre;
    private String usuario;
    private String password;
    private String rol;

    public Usuario(int id, String nombre, String usuario, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password  = password ;
        this.rol = rol;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUsuario() { return usuario; }
    public String getContraseña() { return password ; }
    public String getRol() { return rol; }
}
