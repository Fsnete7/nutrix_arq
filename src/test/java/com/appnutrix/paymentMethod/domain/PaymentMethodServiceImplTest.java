package com.appnutrix.paymentMethod.domain;

import com.appnutrix.patient.domain.Patient;
import com.appnutrix.paymentMethod.application.PaymentMethodServiceImpl;
import com.appnutrix.paymentMethod.infrastructure.persistance.IPaymentMethodRepository;
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
public class PaymentMethodServiceImplTest {
    @Mock
    private IPaymentMethodRepository paymentMethodRepository;
    @InjectMocks
    private PaymentMethodServiceImpl paymentMethodService;

    @Test
    public void saveTest(){
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1",
                "Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        PaymentMethod paymentMethod = new PaymentMethod(1, patient, "Visa", 123456, 25, 2022, 175,
                "Pepito", "Capo", "Lima","Somewhere", "Somewhere2",
                "Lima123", "Peru",123456789);

        given(paymentMethodRepository.save(paymentMethod)).willReturn(paymentMethod);

        PaymentMethod savePaymentMethod = null;

        try{
            savePaymentMethod = paymentMethodService.save(paymentMethod);
        }catch (Exception e){
        }

        assertThat(savePaymentMethod).isNotNull();
        verify(paymentMethodRepository).save(any(PaymentMethod.class));
    }

    @Test
    void findByIdTest() throws Exception {
        Integer id = 1;
        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1",
                "Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));
        PaymentMethod paymentMethod = new PaymentMethod(1, patient, "Visa", 123456, 25, 2022, 175,
                "Pepito", "Capo", "Lima","Somewhere", "Somewhere2",
                "Lima123", "Peru",123456789);

        given(paymentMethodRepository.findById(id)).willReturn(Optional.of(paymentMethod));

        Optional<PaymentMethod> expected = paymentMethodService.getById(id);

        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {

        List<PaymentMethod> paymentMethodList;

        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1",
                "Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));

        paymentMethodList = new ArrayList<>();
        paymentMethodList.add(new PaymentMethod(1, patient, "Visa", 123456, 25, 2022, 175,
                "Pepito", "Capo", "Lima","Somewhere", "Somewhere2",
                "Lima123", "Peru",123456789));
        paymentMethodList.add(new PaymentMethod(1, patient, "Visa", 123456, 25, 2022, 175,
                "Pepito", "Capo", "Lima","Somewhere", "Somewhere2",
                "Lima123", "Peru",123456789));

        given(paymentMethodRepository.findAll()).willReturn(paymentMethodList);
        List<PaymentMethod> expected = paymentMethodService.getAll();
        assertEquals(expected, paymentMethodList);
    }

    @Test
    void findAllByPatient() throws Exception {
        Integer patientId = 1;
        List<PaymentMethod> paymentMethodList;

        Patient patient = new Patient(1,"JosueCuentas1","123","Josue1",
                "Cuentas1","josue1.c1012gmail.com",ParseDate("2017-07-21 17:32:28"));

        paymentMethodList = new ArrayList<>();
        paymentMethodList.add(new PaymentMethod(1, patient, "Visa", 123456, 25, 2022, 175,
                "Pepito", "Capo", "Lima","Somewhere", "Somewhere2",
                "Lima123", "Peru",123456789));
        paymentMethodList.add(new PaymentMethod(1, patient, "Visa", 123456, 25, 2022, 175,
                "Pepito", "Capo", "Lima","Somewhere", "Somewhere2",
                "Lima123", "Peru",123456789));

        given(paymentMethodRepository.findAllByPatient(patientId)).willReturn(paymentMethodList);
        List<PaymentMethod> expected = paymentMethodService.findAllByPatient(patientId);
        assertThat(expected).isNotNull();
    }

    @Test
    void deleteTest() throws Exception {
        Integer id = 1;
        paymentMethodService.delete(1);
        verify(paymentMethodRepository, times(1)).deleteById(id);
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