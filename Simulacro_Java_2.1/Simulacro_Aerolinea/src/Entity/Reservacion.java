package Entity;

public class Reservacion {

    private int idReserva;
    private int idPasajero;
    private int idVuelo;
    private String fechaReserva;
    private String asiento;
    private String  destino;
    public Reservacion() {
    }

    public Reservacion(int idReserva, int idPasajero, int idVuelo, String fechaReserva, String asiento) {
        this.idReserva = idReserva;
        this.idPasajero = idPasajero;
        this.idVuelo = idVuelo;
        this.fechaReserva = fechaReserva;
        this.asiento = asiento;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Reservacion (" +
                " idReserva= " + idReserva +
                " / idPasajero= " + idPasajero +
                " / idVuelo= "  + idVuelo +
                " / fechaReserva= '" + fechaReserva + '\'' +
                " / asiento= '" + asiento + '\'' +
                " ).";
    }
    public String toString(int a) {
        return "Reservacion (" +
                " idReserva= " + idReserva +
                " / Pasajero= " + idPasajero +
                " / Vuelo= " + idVuelo +
                " / destino= "  + destino +
                " / fechaReserva= '" + fechaReserva + '\'' +
                " / asiento= '" + asiento + '\'' +
                " ).";
    }

}
