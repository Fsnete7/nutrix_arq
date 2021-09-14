package com.appnutrix.paymentMethod.domain;

import java.util.List;
import java.util.Optional;

public interface IPaymentMethodService{
    public PaymentMethod save(PaymentMethod paymentMethod) throws Exception;
    void delete(Integer id) throws Exception;
    List<PaymentMethod> getAll() throws  Exception;
    Optional<PaymentMethod> getById(Integer id) throws Exception;
    public List<PaymentMethod> findAllByPatient(Integer patient_id) throws Exception;
}
