import java.util.ArrayList;
import java.util.Scanner;

public class Gestion_Curso {
    private ArrayList<Curso> listaCursos;

    public Gestion_Curso() {
        this.listaCursos = new ArrayList<>();
    }

    public void agregarCurso(Scanner objScan){
        System.out.println("Ingrese el nombre del nuevo curso");
        String nombre= objScan.next();
        System.out.println("Ingrese el código del nuevo curso");
        String codigo= objScan.next();
        if (this.buscarCursoPorCodigo(codigo)!=null){
            System.out.println("Ya existe un curso con este código");
        }else {
            Curso objCurso = new Curso(codigo,nombre);
            if (this.listaCursos.add(objCurso)){
                System.out.println("Curso agregado correctamente");
            }else {
                System.out.println("No se pudo agregar el curso");
            }
        }

    }

    public  void listaTodosLosCurso(){
        if (this.listaCursos.isEmpty()){
            System.out.println("No hay cursos registrados");
        }else {
            for (Curso tem : this.listaCursos){
                System.out.println(tem.toString());
            }
        }
    }
    public Curso buscarCursoPorCodigo(String codigoBuscar){
        for (Curso temporal: this.listaCursos){
            if (temporal.getCodigo().equalsIgnoreCase(codigoBuscar)){
                return temporal;
            }
        }
        return null;
    }
}
