package Controller;

import Entity.Paciente;
import Model.PacienteModel;

import javax.swing.*;

public class PacienteController {
    public void MenuPaciente(){
        PacienteModel objMOdel = new PacienteModel();
        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                   MENU of the patients:
                   1). Insert patient.
                   2). List patient.
                   3). Update patient.
                   4). Delete patient.
                   5). Search by document.
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
                    String doc = JOptionPane.showInputDialog(null, " Enter the document the patient to Search");
                      Paciente objPaciente=objMOdel.SearchByDocument(doc);
                    JOptionPane.showMessageDialog(null, objPaciente.toString());

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
    public  static void Create(){
        PacienteModel objModel = new PacienteModel();

        String name = JOptionPane.showInputDialog("Insert name");
        String lastName = JOptionPane.showInputDialog("Insert last name");
        String date = JOptionPane.showInputDialog("Insert birthdate");
        String document = JOptionPane.showInputDialog("Insert document");

        Paciente objPaciente = new Paciente();

        objPaciente.setNombre(name);
        objPaciente.setApellido(lastName);
        objPaciente.setFechaNacimiento(date);
        objPaciente.setDocumento(document);

        objPaciente =(Paciente) objModel.insert(objPaciente);
        JOptionPane.showMessageDialog(null,objPaciente.toString());

    }

    public static  void getAll(){
        PacienteModel objModel = new PacienteModel();
        String listPaciente = "♿ patient List \n";
        for (Object i : objModel.findAll()){
            Paciente objPaciente = (Paciente) i;
            listPaciente += objPaciente.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listPaciente);
    }
    public static  String getAllString(){
        PacienteModel objModel = new PacienteModel();
        String listPaciente = "♿ patient List \n";
        for (Object i : objModel.findAll()){
            Paciente objPaciente = (Paciente) i;
            listPaciente += objPaciente.toString()+"\n";
        }
        return listPaciente;
    }
    public static  void  update() {
        PacienteModel objModel = new PacienteModel();
        String listpaciente = getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(listpaciente+"\nEnter the Id of the patient"));
        Paciente objPaciente = objModel.SearchById(id);
        if (objPaciente==null){
            JOptionPane.showMessageDialog(null, "patient not found");
        }else {
            String name = JOptionPane.showInputDialog("Enter new name",objPaciente.getNombre());
            String lastName = JOptionPane.showInputDialog("Enter new last name",objPaciente.getApellido());
            String date = JOptionPane.showInputDialog("Enter new birthdate",objPaciente.getFechaNacimiento());
            String document = JOptionPane.showInputDialog("Enter new document",objPaciente.getDocumento());

            objPaciente.setNombre(name);
            objPaciente.setApellido(lastName);
            objPaciente.setFechaNacimiento(date);
            objPaciente.setDocumento(document);
            objModel.update(objPaciente);
        }
    }
    public static void delete(){
        PacienteModel objModel = new PacienteModel();
        String listpaciente = getAllString();

        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listpaciente+ "\n Enter the Id the patient to delete"));
        Paciente objPaciente = objModel.SearchById(idDeleted);

        int Confirm=1;
        if (objPaciente==null){
            JOptionPane.showMessageDialog(null, "patient no found");
        }else {
            Confirm=JOptionPane.showConfirmDialog(null, "Are you suer want to delete the patient?\n" + objPaciente.toString());
            if (Confirm==0)objModel.delete(objPaciente);
        }
    }

}
