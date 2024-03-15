
import java.util.ArrayList;
import java.util.Scanner;

public class Empleado_Temporal_Y_Permanente  {
    private ArrayList<Empleado> listaTemporales;
    private ArrayList<Empleado> listaPermanetes;

    public Empleado_Temporal_Y_Permanente() {
        this.listaTemporales= new ArrayList<>();
        this.listaPermanetes=new ArrayList<>();
    }

    public void agregarEmpleado(String tem, Empleado empleado){
        if (tem.equalsIgnoreCase("temporal")){
            this.listaTemporales.add(empleado);
            System.out.println("Empleado agregado correctamente..");
        } else if(tem.equalsIgnoreCase("permanente"))  {
            this.listaPermanetes.add(empleado);
            System.out.println();
            System.out.println("Empleado agregado correctamente..");
        }else {
            System.out.println("""
                    No se pudo agregar el empleado, 
                        error al digitar el tipo de empleado...
                    """);
        }
    }
    public void EliminarEmpleado( int idEli){
        Scanner objScan = new Scanner(System.in);
        System.out.println(idEli);
        System.out.println("""
                Digite el numero de la lista de donde se va a eliminar:
                1). Empleados Temporales.
                2). Empleados Permanentes.
                """);
        int opt = objScan.nextInt();
        try {
            if (opt==1){

                this.listaTemporales.removeIf(empleado -> empleado.getIdEmpleado()==idEli);
                System.out.println("El empleado temporal fue eliminado correctamente...");

            }else if (opt==2){

                this.listaPermanetes.removeIf(empleado -> empleado.getIdEmpleado()==idEli);
                System.out.println("El empleado permanente fue eliminado correctamente...");

            }else{
                System.out.println("lista no existente..");
            }
        }catch (Exception error){
            System.out.println(error);
        }
    }
    public void mostrarListado(){
        Scanner objScan = new Scanner(System.in);
        String opt="0";
        do {
            System.out.println("""
                Digite el numero de la lista a mostrar
                1). Empleados Temporales.
                2). Empleados Permanentes.
                3). Salir del listado.
                """);
            opt = objScan.next();
            switch (opt){
                case "1":
                    if (this.listaTemporales.isEmpty()){
                        System.out.println("La lista no cuenta con ningún empleado");
                    }

                    else {
                        for (Empleado tem : this.listaTemporales) {
                            System.out.println(tem.toString());
                        }
                    }
                    break;
                case "2":
                    if (this.listaPermanetes.isEmpty()){
                        System.out.println("La lista no cuenta con ningún empleado");
                    }
                    else {
                        for (Empleado tem : this.listaPermanetes) {
                            System.out.println(tem.toString());
                        }
                    }
                    break;
                case "3":
                    System.out.println("Saliendo del listado...");
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opción no validad");
                    break;
            }
        }while (!opt.equals("3"));

    }

}
