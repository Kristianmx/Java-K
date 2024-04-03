package Entity;



public class Medico {
    private int id;
    private String nombre;
    private String apellidos;
    private int idEspecialidad;
    private String nombreEps;
    public Medico() {
    }

    public Medico(int id, String nombre, String apellidos, int idEspecialidad, String nombreEps) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.idEspecialidad = idEspecialidad;
        this.nombreEps = nombreEps;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEps() {
        return nombreEps;
    }

    public void setNombreEps(String nombreEps) {
        this.nombreEps = nombreEps;
    }

    @Override
    public String toString() {
        return "Medico {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", idEspecialidad=" + idEspecialidad +
                '}';
    }

    public String toString(int i) {
        return "Medico {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                " Especialidad='" + nombreEps +'\''+
                '}';
    }
}
