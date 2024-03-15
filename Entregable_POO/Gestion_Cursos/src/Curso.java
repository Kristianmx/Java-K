import java.util.ArrayList;
import java.util.Scanner;

public class Curso {
    private String codigo;
    private String nombre;
    private ArrayList<Estudiante> listaEstudiantes;
    private static int index = 1;

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.listaEstudiantes= new ArrayList<>() ;
    }
    public void  agregarEstudiantes(Scanner objScan) {

        objScan.nextLine();
        System.out.println("Ingresa el nombre del estudiante: ");
        String nombre = objScan.nextLine();
        System.out.println("Ingresa el email del estudiante: ");
        String email = objScan.nextLine();

        Estudiante objEstudiante = new Estudiante(index,nombre,email);
        index++;

        this.listaEstudiantes.add(objEstudiante);
        System.out.println("Estudiante agregado correctamente! 😊");
    }

    public  void listarEstudiante(){
        if(this.listaEstudiantes.isEmpty()){
            System.out.println("No hay estudiantes en el curso..");
        }else {
            System.out.println("Lista de estudiante del curso ".concat(this.nombre));
            for ( Estudiante tem : this.listaEstudiantes){
                System.out.println(tem.toString());
            }
        }
    }
    public void eliminarEstudiantes(Scanner objScan){
        this.listarEstudiante();
        System.out.println("Ingrese el id del estudiante a eliminar:");
        int idEliminar=objScan.nextInt();
        boolean eliminado =  this.listaEstudiantes.removeIf(estudiante -> estudiante.getId()== idEliminar);
        System.out.println(!eliminado
                ? "No se pudo eliminar el estudiante.."
                : "Estudiante eliminado correctamente..");
    }
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", listaEstudiantes=" + listaEstudiantes +
                '}';
    }
}
