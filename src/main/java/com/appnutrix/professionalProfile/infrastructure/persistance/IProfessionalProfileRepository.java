package com.appnutrix.professionalProfile.infrastructure.persistance;

import com.appnutrix.professionalProfile.domain.ProfessionalProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



public interface IProfessionalProfileRepository extends JpaRepository<ProfessionalProfile, Integer> {
    @Query("Select b from ProfessionalProfile b where b.nutritionist.id = :nutritionist_id")
    public ProfessionalProfile findByNutritionist(@Param("nutritionist_id") Integer nutritionist_id);
}
