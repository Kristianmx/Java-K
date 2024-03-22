import controller.AutorController;
import controller.LibroController;
import database.ConfigLibreria;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    // Paso 1: Crear las tablas en la base de datos
        // Nombre de la base: Librería.

    // Paso 2: Implementar Operaciones CRUD para Autores.
        AutorController objControllerAutor = new AutorController();
        LibroController objControllerLibro = new LibroController();
        String option = "";
        do {
            option = JOptionPane.showInputDialog(null, """
                    Select some of the tables:
                    1). Table of authors.
                    2). Table of books.
                    3). Exit of the select
                    Enter the table to select:
                    """);
            switch (option) {
                case "1":
                  objControllerAutor.TableAuthor();
                    break;
                case "2":
                    objControllerLibro.TableBook();
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null,"Saliendo del menu...");
                    JOptionPane.showMessageDialog(null, """
                            Menu Closed.
                            Good Bye...
                            """);
                    break;
            }
        }while (!option.equals("3"));

    }


}