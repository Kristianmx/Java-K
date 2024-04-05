package Entity;

public class Pasajero {
    private int id_pasajero;

    private String nombre;
    private String apellido;

    private String documento;

    public Pasajero() {
    }

    public Pasajero(int id_pasajero, String nombre, String apellido, String documento) {
        this.id_pasajero = id_pasajero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
    }

    public int getId_pasajero() {
        return id_pasajero;
    }

    public void setId_pasajero(int id_pasajero) {
        this.id_pasajero = id_pasajero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Pasajero (" +
                " id_pasajero= " + id_pasajero +
                ", / nombre= '" + nombre + '\'' +
                ", / apellido= '" + apellido + '\'' +
                ", / documento='" + documento + '\'' +
                 " )";
    }
}
