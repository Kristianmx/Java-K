package Controller;

import Entity.Cita;
import Entity.Medico;
import Entity.Paciente;
import Model.CitaModel;
import Model.MedicoModel;
import Model.PacienteModel;

import javax.swing.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;


public class CitaController{
    public void MenuMedico(){
       // MedicoModel objMOdel = new MedicoModel();
        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                   MENU OF APPOINTMENT:
                   1). Insert appointment.
                   2). List appointments.
                   3). Update appointment.
                   4). Delete appointment.
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
                    getAllDate();
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
        CitaModel objModel = new CitaModel();
        PacienteModel objPacienteModel = new PacienteModel();
        MedicoModel objMedicoModel = new MedicoModel();
        Cita objCita =new Cita();
        List<Object> listPaciente = objPacienteModel.findAll();
        List<Object> listDoctor = objMedicoModel.findAll();

        String[] choices1 = new String[listPaciente.size()];
        String[] choices2 = new String[listDoctor.size()];

        int index1= 0;
        int index2= 0;
        for (Object i : listPaciente){
            Paciente paciente = (Paciente) i;
            choices1[index1] = paciente.getNombre()+" "+paciente.getApellido();
            index1++;
        }


        String input1  =(String) JOptionPane.showInputDialog( null,"\n select a patient... ","patients available",JOptionPane.QUESTION_MESSAGE,null,choices1,choices1[0]);

        int Id_paciente=0;
        for (Object i: listPaciente){
            Paciente paciente = (Paciente) i;
            String nombre_completo = paciente.getNombre() + " "+paciente.getApellido();
            if (nombre_completo.equals(input1)){
                Id_paciente=paciente.getId();
            }
        }

        for (Object i : listDoctor){
            Medico medico = (Medico) i;
            choices2[index2] = medico.getNombre()+" "+medico.getApellidos();
            index2++;
        }


        String input2  =(String) JOptionPane.showInputDialog( null,"\n select a doctor... ","doctors available",JOptionPane.QUESTION_MESSAGE,null,choices2,choices2[0]);

        int Id_medico=0;
        for (Object i: listDoctor){
            Medico medico = (Medico) i;
            String nombre_completo = medico.getNombre()+" "+medico.getApellidos();
            if (nombre_completo.equals(input2)){
                Id_medico=medico.getId();
            }
        }

        Time hora = Time.valueOf( LocalTime.parse( JOptionPane.showInputDialog("Insert the time of the appointment (HH:MM):","HH:mm:ss")));

        String fecha = JOptionPane.showInputDialog("Insert the date of the appointment (YYYY-MM-DD):");
        String motivo = JOptionPane.showInputDialog("Insert the reason: ");
        objCita.setId_paciente(Id_paciente);
        objCita.setId_medico(Id_medico);
        objCita.setHora(hora);
        objCita.setFecha(fecha);
        objCita.setMotivo(motivo);
        objCita = (Cita) objModel.insert(objCita);
        JOptionPane.showMessageDialog(null, objCita.toString());
    }
    public static  void getAll(){
        CitaModel objModel = new CitaModel();
        String listcita ="📅 Appointment List \n";
        for (Object it:objModel.findAll()){
            Cita objCita = (Cita) it;
            listcita+=objCita.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listcita);

    }
    public static  void getAllDate(){
        CitaModel objModel = new CitaModel();
         String date = JOptionPane.showInputDialog("Enter date the Appointment");
         List<Cita> lista = objModel.SearchByDate(date);
        String listcita ="📅 Appointment List \n";
        for (Object it:lista){
            Cita objCita = (Cita) it;
            listcita+=objCita.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listcita);

    }
    public static String getAllString(){
        CitaModel objModel = new CitaModel();
        String listcita ="📅 Appointment List \n";
        for (Object it:objModel.findAll()){
            Cita objCita = (Cita) it;
            listcita+=objCita.toString();
        }
        return listcita;
    }
    public static  void  update(){
        CitaModel objModel = new CitaModel();
        PacienteModel objPacienteModel = new PacienteModel();
        MedicoModel objMedicoModel = new MedicoModel();

        List<Object> listPaciente = objPacienteModel.findAll();
        List<Object> listDoctor = objMedicoModel.findAll();
        String list = getAllString();
        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(list +"\n Enter the Id of the Appointment"));

        Cita objCita = objModel.SearchById(idUpdated);
        Paciente objPaciente =objPacienteModel.SearchById(objCita.getId_paciente());
        Medico objMedico = objMedicoModel.SearchById(objCita.getId_medico());
        if (objCita==null){
            JOptionPane.showMessageDialog(null,"Appointment not found");
        }else {

            String[] choices1 = new String[listPaciente.size()];
            String[] choices2 = new String[listDoctor.size()];

            int index1 = 0;
            int index2 = 0;
            for (Object i : listPaciente) {
                Paciente paciente = (Paciente) i;
                choices1[index1] = paciente.getNombre() + " " + paciente.getApellido();
                index1++;
            }

            String nombreCompletoPaciente= objPaciente.getNombre()+" "+objPaciente.getApellido();
            String nombreCompletoDoctor= objMedico.getNombre()+ " "+objMedico.getApellidos();

            String input1 = (String) JOptionPane.showInputDialog(null,"\n select a patient... ",  "registered patient \n"+nombreCompletoPaciente+ "patients available" , JOptionPane.QUESTION_MESSAGE, null, choices1, choices1[0]);

            int Id_paciente = 0;
            for (Object i : listPaciente) {
                Paciente paciente = (Paciente) i;
                String nombre_completo = paciente.getNombre() + " " + paciente.getApellido();
                if (nombre_completo.equals(input1)) {
                    Id_paciente = paciente.getId();
                }
            }

            for (Object i : listDoctor) {
                Medico medico = (Medico) i;
                choices2[index2] = medico.getNombre() + " " + medico.getApellidos();
                index2++;
            }


            String input2 = (String) JOptionPane.showInputDialog(null, "\n select a doctor... ", "registered doctors \n"+nombreCompletoDoctor+"\ndoctors available", JOptionPane.QUESTION_MESSAGE, null, choices2, choices2[0]);

            int Id_medico = 0;
            for (Object i : listDoctor) {
                Medico medico = (Medico) i;
                String nombre_completo = medico.getNombre() + " " + medico.getApellidos();
                if (nombre_completo.equals(input2)) {
                    Id_medico = medico.getId();
                }
            }
            Time hora = Time.valueOf( LocalTime.parse( JOptionPane.showInputDialog("Insert the time of the appointment (HH:MM):",objCita.getHora())));

            String fecha = JOptionPane.showInputDialog("Insert the date of the appointment (YYYY-MM-DD):",objCita.getFecha());
            String motivo = JOptionPane.showInputDialog("Insert the reason: ",objCita.getMotivo());
            objCita.setId_paciente(Id_paciente);
            objCita.setId_medico(Id_medico);
            objCita.setHora(hora);
            objCita.setFecha(fecha);
            objCita.setMotivo(motivo);
            objModel.update(objCita);
        }
    }
    public static void delete(){
        CitaModel objModel = new CitaModel();
        String list = getAllString();
        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(list + "\n Enter the Id the appointment to delete"));
        Cita objCita = objModel.SearchById(idDeleted);
        int Confirm=1;
        if (objCita==null){
            JOptionPane.showMessageDialog(null,"doctor no found");
        }else {
            Confirm=JOptionPane.showConfirmDialog(null, "Are you suer want to delete the patient?\n" + objCita.toString());
            if (Confirm==0)objModel.delete(objCita);
        }
    }
}
