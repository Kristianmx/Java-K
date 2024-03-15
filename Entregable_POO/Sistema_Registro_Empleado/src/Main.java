
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner objScan = new Scanner(System.in);
        GestionEmpleado objGestion = new GestionEmpleado();
        Empleado_Temporal_Y_Permanente objTP = new Empleado_Temporal_Y_Permanente();
        int option=0;
        do {
            System.out.println("""
                Menu de Gestión de empleados:
                1). Agregar empleado.
                2). Mostrár empleados.
                3). Eliminar empleado.
                4). Salir
                
                Ingresa una opción:
                """);
            option = objScan.nextInt();

            switch (option){
                case 1:
                    objGestion.AgregarEmpleado(objTP);
                    break;
                case 2:
                    objGestion.ListarEmpleados(objTP);
                    break;
                case 3:
                    System.out.println("Ingrese el id del empleado a eliminar: ");
                    objGestion.eliminarEmpleado(objScan.nextInt(),objTP);
                    break;
                case 4:
                    System.out.println("A salido del munu...");
                    break;
                default:
                    System.out.println("Opción no validad");
                    break;
            }

        }while (option !=4);



    }
}
