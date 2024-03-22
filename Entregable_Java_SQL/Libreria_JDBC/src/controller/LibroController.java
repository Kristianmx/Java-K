package controller;

import database.ConfigLibreria;
import entity.Autor;
import entity.Libro;
import model.AutorModel;
import model.LibroModel;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class LibroController {
    public static  void getAll(){
        LibroModel objModelLibro = new LibroModel();
        String listAutores= "🖊️ Books List \n" ;
        for (Object i : objModelLibro.findAll()){
            Libro objLibro =  (Libro) i;
            listAutores += objLibro.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listAutores);
    }

    public static  String getAllString(){
        LibroModel objModelLibro = new LibroModel();
        String listAutores= "🖊️ Books List \n" ;
        for (Object i : objModelLibro.findAll()){
            Libro objLibro =  (Libro) i;
            listAutores += objLibro.toString()+"\n";
        }
        return listAutores;
    }

    public  static  void create(){
        LibroModel objModelLibro = new LibroModel();
        AutorController objConautor = new AutorController();
            String listAutor = objConautor.getAllString();
        String titulo= JOptionPane.showInputDialog(null,"Insert title");
        String anio = JOptionPane.showInputDialog(null, "Insert year");
        double precio= Double.parseDouble( JOptionPane.showInputDialog(null,"Insert price"));
        int idAutor =Integer.parseInt( JOptionPane.showInputDialog(null, listAutor+"Insert id the author"));

        Libro objLibro = new Libro();

        objLibro.setTitulo(titulo);
        objLibro.setAnio_public(anio);
        objLibro.setPrecio(precio);
        objLibro.setIdAutor(idAutor);

        objLibro =(Libro)  objModelLibro.insert(objLibro);

        JOptionPane.showMessageDialog(null, objLibro.toString());
    }

    public static void delete(){
        LibroModel objLibroModel = new LibroModel();
        String listLibro = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(null,listLibro + "\n Enter the Id the book to delete"));

        Libro objLibro = objLibroModel.SearchById(idDelete);
        int confirm=1;
        if (objLibro == null){
            JOptionPane.showMessageDialog(null,"author not found");
        }else {
            confirm= JOptionPane.showConfirmDialog(null, "Are you suer want to delete the author? \n" + objLibro.toString());
            if (confirm==0)objLibroModel.delete(objLibro);
        }
    }

    public  static  void update(){
        LibroModel objmodelLibro = new LibroModel();

        AutorController objConautor = new AutorController();
        String listAutor = objConautor.getAllString();

        String listlibro=getAllString();
        int id =Integer.parseInt(JOptionPane.showInputDialog(null,listlibro+
                "\nEnter the Id of the Books"));
        Libro objLibro = objmodelLibro.SearchById(id);
        if (objLibro==null){
            JOptionPane.showMessageDialog(null, "Author not found");
        }else {
            String titulo = JOptionPane.showInputDialog(null,"Enter new title:",objLibro.getTitulo());
            String anio = JOptionPane.showInputDialog(null,"Enter new year",objLibro.getAnio_public());
            double precio = Double.parseDouble( JOptionPane.showInputDialog(null,"Enter new price",objLibro.getPrecio()));
            int idAutor = Integer.parseInt( JOptionPane.showInputDialog(null,listAutor + "Enter new author",objLibro.getIdAutor()));

            objLibro.setTitulo(titulo);
            objLibro.setAnio_public(anio);
            objLibro.setPrecio(precio);
            objLibro.setIdAutor(idAutor);
            objmodelLibro.update(objLibro);

        }
    }
    public void TableBook(){
        String aOption="";
        AutorModel objmodel = new AutorModel();
        do {
            aOption=JOptionPane.showInputDialog(null, """
                                Select one option:
                                1). List All Books.
                                2). Insert New Book.
                                3). Update a Book.
                                4). Delete a Book.
                                5). get Book by Id.
                                6). get Book by Title.
                                7). get Book by Author Id.
                                8). Back to the menu
                                
                                Choose an option:
                                """);
            switch (aOption){
                case "1":
                    getAll();
                    break;
                case "2":
                    create();
                    break;
                case "3":
                    update();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    searchByID();
                    break;
                case "6":
                    searchByTitulo();
                    break;
                case "7":
                    searchByAutor();
                    break;
                case "8":
                    JOptionPane.showMessageDialog(null,"Return the principal menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option not valid..");
                    break;
            }

        }while (!aOption.equals("8"));
    }

    public static void searchByTitulo(){
        Connection objConnection = ConfigLibreria.openConnection();
        LibroModel objLibroModel = new LibroModel();

        String idSearch = JOptionPane.showInputDialog(" Enter the title the book8 to Search");
        String Result="Search Result: \n ";
        List<Libro> listLibro = objLibroModel.SearchByTitu(idSearch);
        for (Libro i: listLibro){
            Result+= i.toString()+ "\n ";
        }
        JOptionPane.showMessageDialog(null,Result);
    }
    public static void searchByID(){
        Connection objConnection = ConfigLibreria.openConnection();
        LibroModel objLibroModel = new LibroModel();

        int idSearch = Integer.parseInt( JOptionPane.showInputDialog(" Enter the ID the Book to Search"));
        String Result="Search Result: \n ";
        List<Libro> listLibro = objLibroModel.SearchByIdLIst(idSearch);
        for (Libro i: listLibro){
            Result+= i.toString()+ "\n ";
        }
        JOptionPane.showMessageDialog(null,Result);
    }
    public static void searchByAutor(){
        Connection objConnection = ConfigLibreria.openConnection();
        LibroModel objLibroModel = new LibroModel();

        int idSearch =Integer.parseInt( JOptionPane.showInputDialog(" Enter the author the book to Search"));
        String Result="Search Result: \n ";
        List<Libro> listLibro = objLibroModel.SearchByIdAutor(idSearch);
        for (Libro i: listLibro){
            Result+= i.toString()+ "\n ";
        }
        JOptionPane.showMessageDialog(null,Result);
    }


}
