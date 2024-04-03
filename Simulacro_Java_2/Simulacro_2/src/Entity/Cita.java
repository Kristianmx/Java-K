package Entity;

import java.sql.Time;

public class Cita {
    private int id;
    private int id_paciente;
    private int id_medico;
    private String fecha;
    private Time hora;
    private String horas;
    private String motivo;

    public Cita() {
    }

    public Cita(int id, int id_paciente, int id_medico, String fecha, Time hora,String horas, String motivo) {
        this.id = id;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.horas=horas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Cita {" +
                " id=" + id +
                ", id_paciente=" + id_paciente +
                ", id_medico=" + id_medico +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}
