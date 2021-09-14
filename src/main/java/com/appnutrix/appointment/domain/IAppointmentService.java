package com.appnutrix.appointment.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IAppointmentService {
    public Appointment save(Appointment appointment) throws Exception;
    void delete(Integer id) throws Exception;
    List<Appointment> getAll() throws  Exception;
    Optional<Appointment> getById(Integer id) throws Exception;
    public List<Appointment> findBetweenDates(Date date1, Date date2) throws Exception;
    public List<Appointment> findByPatient(Integer patient_id) throws Exception;
    public List<Appointment> findByNutritionist(Integer nutritionist_id) throws Exception;
}
