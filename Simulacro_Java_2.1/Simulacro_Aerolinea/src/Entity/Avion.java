package Entity;

public class Avion {
    private int id;
    private String modelo;
    private int capacidad;

    public Avion() {
    }

    public Avion(int id, String modelo, int capacidad) {
        this.id = id;
        this.modelo = modelo;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Avion( " +
                " Id= " + id +
                ", / Modelo= '" + modelo + '\'' +
                ", / Capacidad= " + capacidad +
                " ).";
    }
}
