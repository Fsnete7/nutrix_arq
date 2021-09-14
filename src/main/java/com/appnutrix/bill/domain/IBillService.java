package com.appnutrix.bill.domain;

import com.appnutrix.bill.domain.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IBillService {

    public Bill save(Bill bill) throws Exception;

    void delete(Integer id) throws Exception;

    List<Bill> getAll() throws  Exception;

    Optional<Bill> getById(Integer id) throws Exception;

    public List<Bill> findAllByPatient(Integer patient_id) throws Exception;

    public List<Bill> findBetweenDates(Date date1, Date date2) throws Exception;
}
