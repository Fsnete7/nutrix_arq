package com.appnutrix.bill.infraestructure;

import com.appnutrix.bill.api.BillController;
import com.appnutrix.bill.domain.Bill;
import com.appnutrix.bill.application.BillServiceImpl;
import com.appnutrix.patient.domain.Patient;
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

@WebMvcTest(controllers = BillController.class)
@ActiveProfiles("test")
public class BillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BillServiceImpl billService;

    private List<Bill> billList;

    @BeforeEach
    void setUp(){
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1",
                "Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        billList = new ArrayList<>();
        billList.add(new Bill(1, patient, ParseDate("2017-07-21"), 0.155, 1234567891));
        billList.add(new Bill(2, patient, ParseDate("2017-07-21"), 0.555, 1234567891));
        billList.add(new Bill(3, patient, ParseDate("2017-07-21"), 5.155, 1234567891));
    }

    @Test
    void findAllBill() throws Exception {
        given(billService.getAll()).willReturn(billList);
        mockMvc.perform(get("/api/Bills")).andExpect(status().isOk());
    }

    @Test
    void findBillById() throws Exception{
        Integer billId = 1;
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1",
                "Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        Bill bill = new Bill(1, patient, ParseDate("2017-07-21"), 0.155, 1234567891);
        given(billService.getById(billId)).willReturn(Optional.of(bill));
        mockMvc.perform(get("/api/Bills/{id}", bill.getId())).andExpect(status().isOk());
    }

    @Test
    void findBillByPatient() throws Exception {
        Integer patientId = 1;
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1",
                "Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        Bill bill = new Bill(1, patient, ParseDate("2017-07-21"), 0.155, 1234567891);
        given(billService.findAllByPatient(patientId)).willReturn(billList);
        mockMvc.perform(get("/api/Bills/searchBillByPatientId/{patient_id}", bill.getPatient().getId())).andExpect(status().isOk());
    }

    @Test
    void findBillByBillDateBetweenDates() throws Exception{
        String date1_string = "2015-07-21 17:32:28";
        String date2_string = "2022-07-21 17:32:28";
        Date date1 = ParseDate2(date1_string);
        Date date2 = ParseDate2(date2_string);
        given(billService.findBetweenDates(date1, date2)).willReturn(billList);
        mockMvc.perform(get("/api/Bills/searchBetweenDates").param("date1", date1_string).param("date2", date2_string)).andExpect(status().isOk());
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
