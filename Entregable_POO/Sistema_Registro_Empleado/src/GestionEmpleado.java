import java.util.Scanner;

public class GestionEmpleado {
    ;
    private int index=1;



    public void AgregarEmpleado(Empleado_Temporal_Y_Permanente objTP){
        Scanner objScan = new Scanner(System.in);
        System.out.println("Ingrese el nombre del empleado:");
        String nombre = objScan.next();

        System.out.println("Ingrese la edad del empleado:");
        int edad = objScan.nextInt();

        System.out.println("Ingrese el salario del empleado:");
        double salario = objScan.nextDouble();

        Empleado nuevoEmpleado = new Empleado(nombre,edad,index,salario);
        index++;
        System.out.println(nuevoEmpleado.toString());
        System.out.println("""
                A que tipo de empleado pertenece:
                * Temporal
                * Permanente
                Ingrese el tipo:
                """);
        String tipo = objScan.next();

        objTP.agregarEmpleado(tipo,nuevoEmpleado);
    }

    public void eliminarEmpleado(int id,  Empleado_Temporal_Y_Permanente objTP){
        objTP.EliminarEmpleado(id);
    }

    public void ListarEmpleados(Empleado_Temporal_Y_Permanente objTP){
        objTP.mostrarListado();
    }

}
