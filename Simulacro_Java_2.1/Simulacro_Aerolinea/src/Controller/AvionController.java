package Controller;

import Entity.Avion;
import Model.AvionModel;

import javax.swing.*;

public class AvionController {
    public void MenuAviones(){
        AvionModel objmodel= new AvionModel();
        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                   MENU OF PLANES:
                   1). Insert plane.
                   2). List planes.
                   3). Update plane.
                   4). Delete plane.
                   5). Search...
                   6). Return the Menu Principal.
                                   
                   Choose a option:
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
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, " Enter the Id the plane to Search"));
                    Avion objavion=(Avion) objmodel.SearchById(id);
                    JOptionPane.showMessageDialog(null, objavion.toString());

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

    public static void Create(){
    AvionModel objModel = new AvionModel();
    String modelo = JOptionPane.showInputDialog("Insert the model of plane:");
    int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Insert the capacity of plane:"));
    Avion objAvion = new Avion();
    objAvion.setModelo(modelo);
    objAvion.setCapacidad(capacidad);

    objAvion =(Avion) objModel.insert(objAvion);
    JOptionPane.showMessageDialog(null,objAvion.toString());

    }
    public static  void getAll(){
        AvionModel objModel = new AvionModel();
        String listAvion = "✈️ Plane list\n";
        for (Object i: objModel.findAll()){
            Avion objAvion = (Avion) i;
            listAvion+=objAvion.toString()+"\n";
        }
    JOptionPane.showMessageDialog(null,listAvion);
    }
    public static String getAllString(){
        AvionModel objModel = new AvionModel();
        String listAvion = "✈️ Plane list\n";
        for (Object i: objModel.findAll()){
            Avion objAvion = (Avion) i;
            listAvion+=objAvion.toString()+"\n";
        }
        return listAvion;
    }

    public static  void  update(){
        AvionModel objModel = new AvionModel();
        String listAviones =getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, listAviones+"\n Enter the Id of the plane to update:"));
        Avion objAvion = (Avion) objModel.SearchById(id);

        if (objAvion==null){
            JOptionPane.showMessageDialog(null,"Plane not found");
        }else {
            String modelo = JOptionPane.showInputDialog(null,"Enter the new model of plane:",objAvion.getModelo());
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the new capacity of plane:",objAvion.getCapacidad()));
            objAvion.setModelo(modelo);
            objAvion.setCapacidad(capacidad);

            objModel.update(objAvion);
        }

    }
    public static void delete() {
        AvionModel objModel = new AvionModel();
        String listAviones = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAviones + "\n Enter the Id the plane to delete"));

        Avion objAvion = (Avion) objModel.SearchById(idDelete);

        int Confirm = 1;
        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, "Specialty not found");
        } else {
            Confirm = JOptionPane.showConfirmDialog(null, "Are you suer want to delete the specialty?\n" + objAvion.toString());
            if (Confirm == 0) objModel.delete(objAvion);
        }
    }
}
