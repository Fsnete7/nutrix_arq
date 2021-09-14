package com.appnutrix.paymentMethod.infraestructure;

import com.appnutrix.patient.domain.Patient;
import com.appnutrix.paymentMethod.domain.PaymentMethod;
import com.appnutrix.paymentMethod.application.PaymentMethodServiceImpl;
import com.appnutrix.paymentMethod.api.PaymentMethodController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentMethodController.class)
@ActiveProfiles("test")
public class PaymentMethodControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentMethodServiceImpl paymentMethodService;

    private List<PaymentMethod> paymentMethodList;

    @BeforeEach
    void setUp(){
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1",
                "Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        paymentMethodList = new ArrayList<>();
        paymentMethodList.add(new PaymentMethod(1, patient, "Visa", 123456, 25, 2022, 175,
                "Pepito", "Capo", "Lima","Somewhere", "Somewhere2",
                "Lima123", "Peru",123456789));
        paymentMethodList.add(new PaymentMethod(2, patient, "Visa", 123456, 25, 2022, 175,
                "Pepito", "Capo", "Lima","Somewhere", "Somewhere2",
                "Lima123", "Peru",123456789));
        paymentMethodList.add(new PaymentMethod(3, patient, "Visa", 123456, 25, 2022, 175,
                "Pepito", "Capo", "Lima","Somewhere", "Somewhere2",
                "Lima123", "Peru",123456789));
    }

    @Test
    void findAllPaymentMethod() throws Exception {
        given(paymentMethodService.getAll()).willReturn(paymentMethodList);
        mockMvc.perform(get("/api/PaymentMethods")).andExpect(status().isOk());
    }

    @Test
    void findPaymentMethodById() throws Exception{
        Integer paymentMethodId = 1;
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1",
                "Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        PaymentMethod paymentMethod = new PaymentMethod(1, patient, "Visa", 123456, 25, 2022, 175,
                "Pepito", "Capo", "Lima","Somewhere", "Somewhere2",
                "Lima123", "Peru",123456789);
        given(paymentMethodService.getById(paymentMethodId)).willReturn(Optional.of(paymentMethod));
        mockMvc.perform(get("/api/PaymentMethods/{id}", paymentMethod.getId())).andExpect(status().isOk());
    }

    @Test
    void findPaymentMethodByPatient() throws Exception {
        Integer patientId = 1;
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1",
                "Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        PaymentMethod paymentMethod = new PaymentMethod(1, patient, "Visa", 123456, 25, 2022, 175,
                "Pepito", "Capo", "Lima","Somewhere", "Somewhere2",
                "Lima123", "Peru",123456789);
        given(paymentMethodService.findAllByPatient(patientId)).willReturn(paymentMethodList);
        mockMvc.perform(get("/api/PaymentMethods/searchPaymentMethodByPatientId/{patient_id}", paymentMethod.getPatient().getId())).andExpect(status().isOk());
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
