package com.appnutrix.paymentMethod.application;
import com.appnutrix.paymentMethod.domain.IPaymentMethodService;
import com.appnutrix.paymentMethod.domain.PaymentMethod;
import com.appnutrix.paymentMethod.infrastructure.persistance.IPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaymentMethodServiceImpl implements IPaymentMethodService {

    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;

    @Override
    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) throws Exception {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        paymentMethodRepository.deleteById(id);
    }

    @Override
    public List<PaymentMethod> getAll() throws Exception {
        return paymentMethodRepository.findAll();
    }

    @Override
    public Optional<PaymentMethod> getById(Integer id) throws Exception {
        return paymentMethodRepository.findById(id);
    }

    @Override
    public List<PaymentMethod> findAllByPatient(Integer patient_id) throws Exception{
        return paymentMethodRepository.findAllByPatient(patient_id);
    }
}
