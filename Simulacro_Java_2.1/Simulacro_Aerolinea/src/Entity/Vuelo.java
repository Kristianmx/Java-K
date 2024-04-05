package Entity;

public class Vuelo {
    private int idVuelo;
    private String destino;
    private String fechaSalida;
    private String horaSalida;
    private int idAvion;

    public Vuelo() {
    }

    public Vuelo(int idVuelo, String destino, String fechaSalida, String horaSalida, int idAvion) {
        this.idVuelo = idVuelo;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.idAvion = idAvion;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    @Override
    public String toString() {
        return "Vuelo ( " +
                " idVuelo= " + idVuelo +
                " / destino= '" + destino + '\'' +
                " / fechaSalida= '" + fechaSalida + '\'' +
                " / horaSalida= '" + horaSalida + '\'' +
                " / idAvión= " + idAvion +
                " ).";
    }
}
