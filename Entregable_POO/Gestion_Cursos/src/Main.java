import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int option=0;
        Scanner objScan =new Scanner(System.in);
        Gestion_Curso obgGestion = new Gestion_Curso();

        do {
            System.out.println("""
                Menu de opciones:  
                1). Administrar Estudiantes. 
                2). Administrar Cursos.
                3). Salir"
                
                Ingrese una opción:
                """);
            option= objScan.nextInt();
            switch (option){
                case 1:
                    int option3=0;
                    do {
                        System.out.println("""
                            Menu de Estudiantes:
                            1). Agregar estudiantes a un curso.
                            2). Listar todos los estudiantes de un curso.
                            3). Eliminar estudiante de un curso.
                            4). Salir.
                            
                            Ingrese su opción:
                            """);
                        option3= objScan.nextInt();
                        switch (option3){
                            case 1:
                                obgGestion.listaTodosLosCurso();
                                System.out.println("Ingresa el código de curso donde ingresaras el nuevo estudiante:");
                                String codigo = objScan.next();

                                Curso objCurso = obgGestion.buscarCursoPorCodigo(codigo);

                                if (objCurso== null){
                                    System.out.println("El código ingresado no es válido...");
                                }else {
                                    objCurso.agregarEstudiantes(objScan);
                                }
                                break;
                            case 2:
                                obgGestion.listaTodosLosCurso();
                                System.out.println("Ingresa el código de curso donde ingresaras el nuevo estudiante:");
                                codigo = objScan.next();

                                objCurso = obgGestion.buscarCursoPorCodigo(codigo);

                                if (objCurso== null){
                                    System.out.println("El código ingresado no es válido...");
                                }else {
                                    objCurso.listarEstudiante();
                                }
                                break;
                            case 3:

                                break;
                            default:
                                System.out.println("opción no valida...");
                                break;
                        }
                    }while (option3 !=4);

                    break;
                case 2:
                    int option2=0;
                    do {
                        System.out.println("""
                            Menu de Cursos:
                            1). Agregar curso.
                            2). Listar cursos.
                            3). Buscar por Código.
                            4). Salir.
                            
                            Ingresar Opción:
                            """);
                        option2=objScan.nextInt();
                        switch (option2){
                            case 1:
                                obgGestion.agregarCurso(objScan);
                                break;

                            case 2:
                                obgGestion.listaTodosLosCurso();
                                break;
                            case 3:
                                System.out.println("Ingresa el codigo del curso a buscar:");
                                String codigo = objScan.next();
                                Curso objCurso = obgGestion.buscarCursoPorCodigo(codigo);
                                if (objCurso==null ) {
                                    System.out.println("No existe ningún curso con este código");
                                }else {
                                    System.out.println(objCurso.toString());
                                }
                                break;
                            default:
                                System.out.println("Opción no valida");
                                break;
                        }
                    }while (option2 != 4);
                    break;
                case 3:

                    break;
                default:

                    break;
            }
        }while (option!=3);


    }
}