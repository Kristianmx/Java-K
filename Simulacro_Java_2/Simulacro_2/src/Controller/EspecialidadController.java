package Controller;

import Entity.Especialidad;
import Model.EspecialidadModel;

import javax.swing.*;

public class EspecialidadController {
    // Menu de especialidades
    public void MenuEspecialidad(){
        EspecialidadModel objMOdel = new EspecialidadModel();
        String option;
       do {
           option = JOptionPane.showInputDialog(null, """
                   MENU DE ESPECIALIDADES:
                   1). Insert Specialty.
                   2). List Specialty.
                   3). Update Specialty.
                   4). Delete Specialty.
                   5). Search...
                   6). Return the Menu Principal.
                                   
                   Choose na option:
                   """);
           switch (option) {
               case "1":
                   Create();
                   break;
               case "2":
                   getAll();
                   break;
               case "3":
                   update();
                   break;
               case "4":
                   delete();
                   break;
               case "5":
                   int id = Integer.parseInt(JOptionPane.showInputDialog(null, " Enter the Id the specialty to Search"));
                   Especialidad objEspecialidad = objMOdel.SearchById(id);
                   JOptionPane.showMessageDialog(null, objEspecialidad.toString());

                   break;
               case "6":
                   JOptionPane.showMessageDialog(null, "Return the principal menu");
                   break;
               default:
                   JOptionPane.showMessageDialog(null, "Option not valid...");
                   break;
           }
       }while (!option.equals("6"));
    }

    // Insertar nueva especialidad
    public static void Create(){
        EspecialidadModel objModel = new EspecialidadModel();

        String name = JOptionPane.showInputDialog("Insert name");

        String Description = JOptionPane.showInputDialog("Insert the Description");

        Especialidad objEspecialidad = new Especialidad();

        objEspecialidad.setNombre(name);
        objEspecialidad.setDescripcion(Description);

        objEspecialidad = (Especialidad) objModel.insert(objEspecialidad);

        JOptionPane.showMessageDialog(null, objEspecialidad.toString());
    }

    public static  void getAll(){
        EspecialidadModel objModel = new EspecialidadModel();
        String listEspecialidades ="🩺 Specialty List \n";
        for (Object i : objModel.findAll()){
            Especialidad objEspecialidad = (Especialidad) i;
            listEspecialidades += objEspecialidad.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listEspecialidades);
    }
    public static String getAllString(){
        EspecialidadModel objModel = new EspecialidadModel();
        String listEspecialidades ="🩺 Specialty List \n";
        for (Object i : objModel.findAll()){
            Especialidad objEspecialidad = (Especialidad) i;
            listEspecialidades += objEspecialidad.toString()+"\n";
        }
        return listEspecialidades;
    }

    public static  void  update(){
        EspecialidadModel objModel = new EspecialidadModel();
        String listEspecialidades = getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog( listEspecialidades+"\nEnter the Id of the specialty"));
        Especialidad objEspecialdad = objModel.SearchById(id);
        if (objEspecialdad==null){
            JOptionPane.showMessageDialog(null, "Specialty not found");
        }else {
            String name = JOptionPane.showInputDialog(null, "Enter new name",objEspecialdad.getNombre());
            String description = JOptionPane.showInputDialog(null,"Enter new description",  objEspecialdad.getDescripcion());

                objEspecialdad.setNombre(name);
                objEspecialdad.setDescripcion(description);

            objModel.update(objEspecialdad);
        }
    }

    public static void delete(){
        EspecialidadModel objModel = new EspecialidadModel();
        String listEspecialidad = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidad+"\n Enter the Id the Specialty to delete"));

        Especialidad objEspecialidad = objModel.SearchById(idDelete);

        int Confirm =1;
        if (objEspecialidad==null) {
            JOptionPane.showMessageDialog(null, "Specialty no found");
        }else {
            Confirm = JOptionPane.showConfirmDialog(null, "Are you suer want to delete the specialty?\n" + objEspecialidad.toString() );
            if (Confirm==0)objModel.delete(objEspecialidad);

        }
    }

}

