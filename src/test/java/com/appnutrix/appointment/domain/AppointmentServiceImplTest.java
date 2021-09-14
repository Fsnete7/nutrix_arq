package com.appnutrix.appointment.domain;

import com.appnutrix.appointment.application.AppointmentServiceImpl;
import com.appnutrix.appointment.infraestructure.persistance.IAppointmentRepository;
import com.appnutrix.patient.domain.Patient;
import com.appnutrix.diet.domain.Diet;
import com.appnutrix.nutritionist.domain.Nutritionist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceImplTest {

    @Mock
    private IAppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Test
    public void saveTest(){
        Patient patient = new Patient(1, "pepito1", "pepito123", "Jose1", "Perez1",
                "pepito1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
        Nutritionist nutritionist = new Nutritionist(1, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        Diet diet = new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));

        Appointment appointment = new Appointment(1, patient, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes1");
        given(appointmentRepository.save(appointment)).willReturn(appointment);
        Appointment savedAppointment = null;
        try{
            savedAppointment = appointmentService.save(appointment);
        }catch (Exception e){
        }

        assertThat(savedAppointment).isNotNull();
        verify(appointmentRepository).save(any(Appointment.class));
    }

    @Test
    void deleteTest() throws Exception{
        Integer id = 1;
        appointmentService.delete(id);
        verify(appointmentRepository, times(1)).deleteById(id);
    }

    @Test
    void findByIdTest() throws Exception {
        Integer id = 1;
        Patient patient = new Patient(1, "pepito1", "pepito123", "Jose1", "Perez1",
                "pepito1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
        Nutritionist nutritionist = new Nutritionist(1, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        Diet diet = new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));

        Appointment appointment = new Appointment(1, patient, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes1");
        given(appointmentRepository.findById(id)).willReturn(Optional.of(appointment));

        Optional<Appointment> expected = appointmentService.getById(id);

        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        Patient patient = new Patient(1, "pepito1", "pepito123", "Jose1", "Perez1",
                "pepito1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
        Nutritionist nutritionist = new Nutritionist(1, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        Diet diet = new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));

        Appointment appointment = new Appointment(1, patient, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes1");
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);

        given(appointmentRepository.findAll()).willReturn(appointments);

        List<Appointment> expected = appointmentService.getAll();
        assertEquals(expected, appointments);
    }

    @Test
    void findBetweenDatesTest() throws Exception{
        String date1_string = "2015-07-21 17:32:28";
        String date2_string = "2022-07-21 17:32:28";
        Date date1 = ParseDate2(date1_string);
        Date date2 = ParseDate2(date2_string);
        Patient patient = new Patient(1, "pepito1", "pepito123", "Jose1", "Perez1",
                "pepito1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
        Nutritionist nutritionist = new Nutritionist(1, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        Diet diet = new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));

        Appointment appointment = new Appointment(1, patient, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes1");
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        given(appointmentRepository.findBetweenDates(date1, date2)).willReturn(appointments);
        List<Appointment> expected = appointmentService.findBetweenDates(date1, date2);

        assertThat(expected).isNotNull();
    }

    @Test
    void findByPatientTest() throws Exception{
        Patient patient = new Patient(1, "pepito1", "pepito123", "Jose1", "Perez1",
                "pepito1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
        Nutritionist nutritionist = new Nutritionist(1, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        Diet diet = new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));

        Appointment appointment = new Appointment(1, patient, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes1");
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        given(appointmentRepository.findByPatient(patient.getId())).willReturn(appointments);
        List<Appointment> expected = appointmentService.findByPatient(patient.getId());

        assertThat(expected).isNotNull();
    }

    @Test
    void findByNutritionistTest() throws Exception{
        Patient patient = new Patient(1, "pepito1", "pepito123", "Jose1", "Perez1",
                "pepito1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
        Nutritionist nutritionist = new Nutritionist(1, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        Diet diet = new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));

        Appointment appointment = new Appointment(1, patient, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes1");
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        given(appointmentRepository.findByNutritionist(nutritionist.getId())).willReturn(appointments);
        List<Appointment> expected = appointmentService.findByNutritionist(nutritionist.getId());

        assertThat(expected).isNotNull();
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