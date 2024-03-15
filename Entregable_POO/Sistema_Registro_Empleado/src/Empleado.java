
public class Empleado extends Persona{
    private  int idEmpleado;
    private double salario;

    public Empleado(String nombre, int edad, int idEmpleado, double salario) {
        super(nombre, edad);
        this.idEmpleado = idEmpleado;
        this.salario = salario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public double getSalario() {
        return salario;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.toString() + "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", salario=" + salario +
                '}';
    }
}
