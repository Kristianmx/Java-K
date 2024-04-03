import Controller.CitaController;
import Controller.EspecialidadController;
import Controller.MedicoController;
import Controller.PacienteController;
import Database.ConfigDB;
import Entity.Especialidad;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EspecialidadController objEspecialidadController = new EspecialidadController();
        MedicoController objMedicoController = new MedicoController();
        PacienteController objPacienteController = new PacienteController();
        CitaController objCitaController = new CitaController();
        String option="";
        do {
            option = JOptionPane.showInputDialog("""
                    Select some of te tables;
                    1). Table of specialties.
                    2). Table of doctors.
                    3). Table of patients.
                    4). Table of appointments.
                    5). Exit..
                    Enter the table to select:
                    """);
            switch (option) {
                case "1":
                    objEspecialidadController.MenuEspecialidad();
                    break;
                case "2":
                    objMedicoController.MenuMedico();
                    break;
                case "3":
                    objPacienteController.MenuPaciente();
                    break;
                case "4":
                    objCitaController.MenuMedico();

                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, """
                            Menu Closed.
                            Good Bye...
                            """);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option not valid...");
                    break;
            }
        }while (!option.equals("5"));


    }
}