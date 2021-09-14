package com.appnutrix.patient.domain;

import com.appnutrix.patient.application.PatientServiceImpl;
import com.appnutrix.patient.infraestructure.persistance.IPatientRepository;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {
    @Mock
    private IPatientRepository patientRepository;
    @InjectMocks
    private PatientServiceImpl patientService;
    @Test
    public void saveTest()
    {
        Patient patient = new Patient(1,"JosueCuentas","123","Josue"
                ,"Cuentas","josue.c1012gmail.com",ParseDate("2021-05-05"));
        given(patientRepository.save(patient)).willReturn(patient);

        Patient savedPatient = null;
        try{
            savedPatient = patientService.save(patient);
        }catch (Exception e)
        {

        }

        assertThat(savedPatient).isNotNull();
        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    void findByIdTest() throws Exception
    {
        Integer patientId = 1;
        Patient patient = new Patient(1,"JosueCuentas","123","Josue"
                ,"Cuentas","josue.c1012gmail.com",ParseDate("2021-05-05"));
        given(patientService.getById(patientId)).willReturn(Optional.of(patient));

        Optional<Patient> expected = null;
        expected = patientService.getById(patientId);
        assertThat(expected).isNotNull();
    }

    @Test
    void findByUsernameTest() throws Exception
    {
        String username = "JosueCuentas";
        Patient patient = new Patient(1,"JosueCuentas","123","Josue"
                ,"Cuentas","josue.c1012gmail.com",ParseDate("2021-05-05"));
        given(patientService.findByUsername(username)).willReturn(patient);

        Patient expected = null;
        expected = patientService.findByUsername(username);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception
    {
        List<Patient> list = new ArrayList<>();
        list.add(new Patient(1,"JosueCuentas","123","Josue"
                ,"Cuentas","josue.c1012gmail.com",ParseDate("2021-05-05")));
        list.add(new Patient(2,"JosueCuentas","123","Josue"
                ,"Cuentas","josue.c1012gmail.com",ParseDate("2021-05-05")));
        list.add(new Patient(3,"JosueCuentas","123","Josue"
                ,"Cuentas","josue.c1012gmail.com",ParseDate("2021-05-05")));

        given(patientRepository.findAll()).willReturn(list);
        List<Patient> expected = patientService.getAll();
        assertEquals(expected, list);
    }

    public static Date ParseDate(String date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy,MM,dd");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception e){
        }
        return result;
    }

    @Test
    void findByFirstnameTest() throws Exception {
        String FirstName = "Josue1";
        List<Patient> patientList;

        patientList = new ArrayList<>();
        patientList.add(new Patient(1,"JosueCuentas1","123","Josue1"
                ,"Cuentas1","josue1.c1012gmail.com",ParseDate("2021-05-05")));
        patientList.add(new Patient(2,"JosueCuentas2","123","Josue2"
                ,"Cuentas2","josue2.c1012gmail.com",ParseDate("2021-05-05")));
        patientList.add(new Patient(3,"JosueCuentas3","123","Josue3"
                ,"Cuentas3","josue3.c1012gmail.com",ParseDate("2021-05-05")));

        given(patientRepository.findByFirstName(FirstName)).willReturn(patientList);
        List<Patient> expected = patientService.findByFirstName(FirstName);
        assertThat(expected).isNotNull();
    }

    @Test
    void findByLastnameTest() throws Exception {
        String lastname = "Cuentas1";
        List<Patient> patientList;

        patientList = new ArrayList<>();
        patientList.add(new Patient(1,"JosueCuentas1","123","Josue1"
                ,"Cuentas1","josue1.c1012gmail.com",ParseDate("2021-05-05")));
        patientList.add(new Patient(2,"JosueCuentas2","123","Josue2"
                ,"Cuentas2","josue2.c1012gmail.com",ParseDate("2021-05-05")));
        patientList.add(new Patient(3,"JosueCuentas3","123","Josue3"
                ,"Cuentas3","josue3.c1012gmail.com",ParseDate("2021-05-05")));

        given(patientRepository.findByLastName(lastname)).willReturn(patientList);
        List<Patient> expected = patientService.findByLastName(lastname);
        assertThat(expected).isNotNull();
    }

    @Test
    void findByFirstnameAndLastnameTest() throws Exception {
        String firstname = "Josue1";
        String lastname = "Cuentas1";
        List<Patient> patientList;

        patientList = new ArrayList<>();
        patientList.add(new Patient(1,"JosueCuentas1","123","Josue1"
                ,"Cuentas1","josue1.c1012gmail.com",ParseDate("2021-05-05")));
        patientList.add(new Patient(2,"JosueCuentas2","123","Josue2"
                ,"Cuentas2","josue2.c1012gmail.com",ParseDate("2021-05-05")));
        patientList.add(new Patient(3,"JosueCuentas3","123","Josue3"
                ,"Cuentas3","josue3.c1012gmail.com",ParseDate("2021-05-05")));

        given(patientRepository.findByFirstNameAndLastName(firstname, lastname)).willReturn(patientList);
        List<Patient> expected = patientService.findByFirstNameAndLastName(firstname, lastname);
        assertThat(expected).isNotNull();
    }

    @Test
    void deleteTest() throws Exception {
        Integer id = 1;
        patientService.delete(id);
        verify(patientRepository, times(1)).deleteById(id);
    }
}
