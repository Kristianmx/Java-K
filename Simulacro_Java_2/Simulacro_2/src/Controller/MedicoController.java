package Controller;

import Entity.Medico;
import Model.MedicoModel;
import javax.swing.*;


public class MedicoController {
    public void MenuMedico(){
        MedicoModel objMOdel = new MedicoModel();
        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                   MENU OF DOCTORS:
                   1). Insert doctor.
                   2). List doctor.
                   3). Update doctor.
                   4). Delete doctor.
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
                    String id = JOptionPane.showInputDialog(null, " Enter the Id the specialty to Search");
                    Medico objMedico = objMOdel.SearchByEspecialidad(id);
                    JOptionPane.showMessageDialog(null, objMedico.toString(1));

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
        MedicoModel objModel = new MedicoModel();
        String listEspe = EspecialidadController.getAllString();
        String name = JOptionPane.showInputDialog("Insert name");
        String lastName = JOptionPane.showInputDialog("Insert last name");
        int Especialidad = Integer.parseInt( JOptionPane.showInputDialog(listEspe+  "\nInsert Id of the speciality"));

        Medico objMedico = new Medico();

        objMedico.setNombre(name);
        objMedico.setApellidos(lastName);
        objMedico.setIdEspecialidad(Especialidad);

        objMedico = (Medico) objModel.insert(objMedico);
        JOptionPane.showMessageDialog(null,objMedico.toString());
    }
    public static  void getAll(){
        MedicoModel objModel = new MedicoModel();
        String listMedico = "♿ Doctor List \n";
        for (Object i : objModel.findAll()){
            Medico objMedico = (Medico) i;
            listMedico+=objMedico.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listMedico);
    }
    public static String getAllString(){
        MedicoModel objModel = new MedicoModel();
        String listMedico = "♿ Doctor List \n";
        for (Object i : objModel.findAll()){
            Medico objMedico = (Medico) i;
            listMedico += objMedico.toString()+"\n";
        }
        return listMedico;
    }
    public static  void  update(){
        MedicoModel objModel = new MedicoModel();
        String list = getAllString();
        String listESpe= EspecialidadController.getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(list+ " \nEnter the Id of the doctor"));
        Medico objMedico = objModel.SearchById(id);
        if (objMedico==null){
            JOptionPane.showMessageDialog(null,"Doctor not found");
        }else {
            String name = JOptionPane.showInputDialog("Enter new name",objMedico.getNombre());
            String lastName = JOptionPane.showInputDialog("Enter new last name",objMedico.getApellidos());
            int Especialidad =Integer.parseInt(JOptionPane.showInputDialog(listESpe+ "\nEnter the Id of new speciality",objMedico.getIdEspecialidad()));
            objMedico.setNombre(name);
            objMedico.setApellidos(lastName);
            objMedico.setIdEspecialidad(Especialidad);
            objModel.update(objMedico);
        }
    }
    public static void delete(){
        MedicoModel objModel = new MedicoModel();
        String list = getAllString();

        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(list +"\n Enter the Id the doctor to delete"));
        Medico objMedico = objModel.SearchById(idDeleted);

        int Confirm=1;
        if (objMedico==null){
            JOptionPane.showMessageDialog(null,"doctor no found");
        }else {
            Confirm=JOptionPane.showConfirmDialog(null, "Are you suer want to delete the patient?\n" + objMedico.toString());
            if (Confirm==0)objModel.delete(objMedico);
        }
    }
}
