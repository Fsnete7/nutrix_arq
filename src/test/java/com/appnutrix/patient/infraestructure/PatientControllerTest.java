package com.appnutrix.patient.infraestructure;

import com.appnutrix.patient.api.PatientController;
import com.appnutrix.patient.infraestructure.persistance.IPatientFavoriteRecipesRepository;
import com.appnutrix.patient.domain.Patient;
import com.appnutrix.patient.application.PatientServiceImpl;
import com.appnutrix.recipe.application.RecipeServiceImpl;
import com.appnutrix.recipe.domain.IRecipeService;
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

@WebMvcTest(controllers = PatientController.class)
@ActiveProfiles("test")
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientServiceImpl patientService;
    @MockBean
    private RecipeServiceImpl recipeService;
    @MockBean
    private IPatientFavoriteRecipesRepository patientFavoriteRecipesRepository;


    private List<Patient> patientList;

    @BeforeEach
    void setUp()
    {
        patientList = new ArrayList<>();
        patientList.add(new Patient(1,"JosueCuentas1","123","Josue1",
                "Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28")));
        patientList.add(new Patient(2,"JosueCuentas2","123","Josue2",
                "Cuentas2","josue2.c1012gmail.com",ParseDate("2017-07-21 17:32:28")));
        patientList.add(new Patient(3,"JosueCuentas3","123","Josue3",
                "Cuentas3","josue3.c1012gmail.com",ParseDate("2017-07-21 17:32:28")));
    }

    @Test
    void findAllPatients() throws Exception {
        given(patientService.getAll()).willReturn(patientList);
        mockMvc.perform(get("/api/patients")).andExpect(status().isOk());
    }

    @Test
    void findPatientById() throws Exception {
        Integer patientId = 1;
        Patient patient = new Patient(1,"JosueCuentas","123","Josue",
                "Cuentas","josue.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        given(patientService.getById(patientId)).willReturn(Optional.of(patient));

        mockMvc.perform(get("/api/patients/{id}", patient.getId())).andExpect(status().isOk());
    }

    @Test
    void findByLastName() throws Exception {
        String lastname = "Cuentas1";
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1"
                ,"Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        given(patientService.findByLastName(lastname)).willReturn(patientList);
        mockMvc.perform(get("/api/patients/searchByLastName/{lastname}", patient.getLastName())).andExpect(status().isOk());
    }

    @Test
    void findByFirstName() throws Exception
    {
        String firstname = "Josue1";
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1"
                ,"Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        given(patientService.findByFirstName(firstname)).willReturn(patientList);
        mockMvc.perform(get("/api/patients/searchByFirstName/{firstname}", patient.getFirstName())).andExpect(status().isOk());
    }

    @Test
    void findByFirstNameAndLastName() throws Exception
    {
        String firstname = "Josue1";
        String lastname = "Cuentas1";
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1"
                ,"Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        given(patientService.findByFirstNameAndLastName(firstname, lastname)).willReturn(patientList);
        mockMvc.perform(get("/api/patients/searchByFirstNameAndLastName/{firstname}/{lastname}", patient.getFirstName(), patient.getLastName())).andExpect(status().isOk());
    }

    @Test
    void findPatientByUsername() throws Exception {
        String username = "JosueCuentas1";
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue"
                ,"Cuentas","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        given(patientService.findByUsername(username)).willReturn(patient);

        mockMvc.perform(get("/api/patients/searchByUsername/{username}", patient.getUsername())).andExpect(status().isOk());
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
}