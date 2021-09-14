package com.appnutrix.professionalProfile.domain;

import java.util.List;
import java.util.Optional;

public interface IProfessionalProfileService {
    public ProfessionalProfile save(ProfessionalProfile professionalProfile) throws Exception;
    void delete(Integer id) throws Exception;
    List<ProfessionalProfile> getAll() throws  Exception;
    Optional<ProfessionalProfile> getById(Integer id) throws Exception;
    public ProfessionalProfile findByNutritionist(Integer nutritionist_id) throws Exception;
}


