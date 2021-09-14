package com.appnutrix.bill.application;

import com.appnutrix.bill.domain.Bill;
import com.appnutrix.bill.domain.IBillService;
import com.appnutrix.bill.infraestructure.persistance.IBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BillServiceImpl implements IBillService {

    @Autowired
    private IBillRepository billRepository;

    @Override
    @Transactional
    public Bill save(Bill bill) throws Exception {
        return billRepository.save(bill);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        billRepository.deleteById(id);
    }

    @Override
    public List<Bill> getAll() throws Exception {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> getById(Integer id) throws Exception {
        return billRepository.findById(id);
    }

    @Override
    public List<Bill> findAllByPatient(Integer patient_id) throws Exception {
        return billRepository.findAllByPatient(patient_id);
    }

    @Override
    public List<Bill> findBetweenDates(Date date1, Date date2) throws Exception{
        return billRepository.findBetweenDates(date1, date2);
    }
}
