package com.appnutrix.appointment.infraestructure.persistance;

import com.appnutrix.appointment.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("Select b from Appointment b where b.appointmentDate BETWEEN :date1 AND :date2")
    public List<Appointment> findBetweenDates(@Param("date1") Date date1,
                                              @Param("date2") Date date2);

    @Query("Select b from Appointment b where b.patient.id = :patient_id")
    public List<Appointment> findByPatient(@Param("patient_id") Integer patient_id);

    @Query("Select b from Appointment b where b.nutritionist.id = :nutritionist_id")
    public List<Appointment> findByNutritionist(@Param("nutritionist_id") Integer nutritionist_id);
}
