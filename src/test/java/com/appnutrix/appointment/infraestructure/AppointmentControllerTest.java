package com.appnutrix.appointment.infraestructure;

import com.appnutrix.appointment.api.AppointmentController;
import com.appnutrix.appointment.domain.Appointment;
import com.appnutrix.appointment.application.AppointmentServiceImpl;
import com.appnutrix.diet.domain.Diet;
import com.appnutrix.diet.domain.IDietService;
import com.appnutrix.nutritionist.domain.Nutritionist;
import com.appnutrix.patient.domain.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = AppointmentController.class)
@ActiveProfiles("test")
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentServiceImpl appointmentService;
    @MockBean
    private IDietService dietService;

    private List<Appointment> appointmentList;
    private List<Patient> patientList;
    private List<Nutritionist> nutritionistList;
    private List<Diet> dietList;

    @BeforeEach
    void setUp(){
        appointmentList = new ArrayList<>();
        patientList = new ArrayList<>();
        nutritionistList = new ArrayList<>();
        dietList = new ArrayList<>();

        patientList.add(new Patient(1, "pepitoasd1", "pepitoasd123", "Joseasd1", "Perezasd1",
                "pepitoasd1@upc.edu.pe", ParseDate("2017-07-21 17:32:28"))); //.10000
        patientList.add(new Patient(2, "pepitoasd1", "pepitoasd123", "Joseasd1", "Perezasd1",
                "pepitoasd1@upc.edu.pe", ParseDate("2017-07-21 17:32:28"))); //.10000
        nutritionistList.add(new Nutritionist(1, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28"))); //.10000
        nutritionistList.add(new Nutritionist(2, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28"))); //.10000
        dietList.add(new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28")));
        dietList.add(new Diet(2, "diet1", "description1", ParseDate("2017-07-21 17:32:28")));

        appointmentList.add(new Appointment(1, patientList.get(0), nutritionistList.get(0), dietList.get(0), ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes1"));
        appointmentList.add(new Appointment(2, patientList.get(1), nutritionistList.get(1), dietList.get(1), ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes2"));
    }

    @Test
    void findAllAppointments() throws Exception{
        given(appointmentService.getAll()).willReturn(appointmentList);
        mockMvc.perform(get("/api/appointment")).andExpect(status().isOk());
    }

    @Test
    void findAppointmentById() throws Exception{
        Integer AppointmentId = 1;
        Patient patient = new Patient(1, "pepito1", "pepito123", "Jose1", "Perez1",
                "pepito1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
        Nutritionist nutritionist = new Nutritionist(1, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        Diet diet = new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));

        Appointment appointment = new Appointment(1, patient, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes1");
        given(appointmentService.getById(AppointmentId)).willReturn(Optional.of(appointment));
        mockMvc.perform(get("/api/appointment/{id}", appointment.getId())).andExpect(status().isOk());
    }

    @Test
    void findAppointmentByAppointmentDateBetweenDates() throws Exception{
        String date1_string = "2015-07-21 17:32:28";
        String date2_string = "2022-07-21 17:32:28";
        Date date1 = ParseDate2(date1_string);
        Date date2 = ParseDate2(date2_string);
        given(appointmentService.findBetweenDates(date1, date2)).willReturn(appointmentList);
        mockMvc.perform(get("/api/appointment/searchBetweenDates").param("date1", date1_string).param("date2", date2_string)).andExpect(status().isOk());
    }

    @Test
    void findAppointmentByNutritionist() throws Exception{
        Integer NutritionistId = 1;
        Patient patient = new Patient(1, "pepito1", "pepito123", "Jose1", "Perez1",
                "pepito1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
        Nutritionist nutritionist = new Nutritionist(1, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        Diet diet = new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));

        Appointment appointment = new Appointment(1, patient, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes1");
        given(appointmentService.findByNutritionist(NutritionistId)).willReturn(appointmentList);
        mockMvc.perform(get("/api/appointment/searchAppointmentByNutritionistId/{nutritionist_id}", appointment.getNutritionist().getId())).andExpect(status().isOk());
    }

    @Test
    void findAppointmentByPatient() throws Exception{
        Integer PatientId = 1;
        Patient patient = new Patient(1, "pepito1", "pepito123", "Jose1", "Perez1",
                "pepito1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
        Nutritionist nutritionist = new Nutritionist(1, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        Diet diet = new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));

        Appointment appointment = new Appointment(1, patient, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes1");
        given(appointmentService.findByPatient(PatientId)).willReturn(appointmentList);
        mockMvc.perform(get("/api/appointment/searchAppointmentByPatientId/{patient_id}", appointment.getPatient().getId())).andExpect(status().isOk());
    }

    public static Date ParseDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception e){
        }
        return result;
    }

    public static Date ParseDate2(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception e){
        }
        return result;
    }

}