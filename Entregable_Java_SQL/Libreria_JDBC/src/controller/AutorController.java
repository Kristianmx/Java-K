package controller;

import entity.Autor;
import entity.Libro;
import model.AutorModel;

import javax.swing.*;
import java.util.List;

public class AutorController {
    public static  void getAll(){
        AutorModel objAutorModel = new AutorModel();
        String listAutores= "🖊️ Autor List \n" ;
        for (Object i : objAutorModel.findAll()){
            Autor objAutor = (Autor) i;
            listAutores += objAutor.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listAutores);
    }

    public static  String getAllString(){
        AutorModel objAutorModel = new AutorModel();
        String listAutores= "🖊️ Autor List \n" ;
        for (Object i : objAutorModel.findAll()){
            Autor objAutor = (Autor) i;
            listAutores += objAutor.toString()+"\n";
        }
        return listAutores;
    }

    public  static  void create(){
        AutorModel objAutorModel = new AutorModel();

        String name= JOptionPane.showInputDialog(null,"Insert name");
        String nationality = JOptionPane.showInputDialog(null, "Insert nationality");

        Autor objAutor = new Autor();

        objAutor.setNombre(name);
        objAutor.setNacionalidad(nationality);

        objAutor =(Autor)  objAutorModel.insert(objAutor);

        JOptionPane.showMessageDialog(null, objAutor.toString());
    }

    public static void delete(){
        AutorModel objAutorModel = new AutorModel();
        String listAutor = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(null,listAutor + "\n Enter the Id the author to delete"));

        Autor objAutor = objAutorModel.SearchById(idDelete);
        int confirm=1;
        if (objAutor == null){
            JOptionPane.showMessageDialog(null,"author not found");
        }else {
            confirm= JOptionPane.showConfirmDialog(null, "Are you suer want to delete the author? \n" + objAutor.toString());
            if (confirm==0)objAutorModel.delete(objAutor);
        }
    }

    public  static  void update(){
        AutorModel objmodel = new AutorModel();

        String listAutor=getAllString();
        int id =Integer.parseInt(JOptionPane.showInputDialog(null,listAutor+
                "\nEnter the Id of the author"));
        Autor objAutor = objmodel.SearchById(id);
        if (objAutor==null){
            JOptionPane.showMessageDialog(null, "Author not found");
        }else {
            String nombre = JOptionPane.showInputDialog(null,"Enter new name:",objAutor.getNombre());
            String nacionalidad = JOptionPane.showInputDialog(null,"Enter new nationality",objAutor.getNacionalidad());
            objAutor.setNombre(nombre);
            objAutor.setNacionalidad(nacionalidad);

            objmodel.update(objAutor);

        }
    }

    public void TableAuthor(){

        String aOption="";
        AutorModel objmodel = new AutorModel();
        do {
            aOption=JOptionPane.showInputDialog(null, """
                                Select one option:
                                1). List authors.
                                2). Insert author.
                                3). Update author.
                                4). Delete author.
                                5). get author by Id.
                                6). Search Advance.
                                7). Return the Menu Principal
                                
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

                    int id = Integer.parseInt(JOptionPane.showInputDialog(null," Enter the Id the author to Search"));
                     Autor objAutor =                   objmodel.SearchById(id);
                     JOptionPane.showMessageDialog(null,objAutor.toString());
                    break;
                case "6":
                    SearchAuthorAdvance();
                    break;
                case "7":
                    JOptionPane.showMessageDialog(null,"Return the principal menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option not valid..");
                    break;
            }

        }while (!aOption.equals("7"));
}
    public static void SearchAuthorAdvance(){
        AutorModel objAuthorModel = new AutorModel();
        String name = JOptionPane.showInputDialog(null,"Insert Author name to search:");
       String listAutores = "Search Result: \n ";
        for (Object i : objAuthorModel.SearchAdvance(name)){
            Libro objAutor = (Libro) i;
            listAutores += objAutor.toStringAdvance()+"\n";

        }
        JOptionPane.showMessageDialog(null,listAutores);

    }

}