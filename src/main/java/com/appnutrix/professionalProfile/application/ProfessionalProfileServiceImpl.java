package com.appnutrix.professionalProfile.application;


import com.appnutrix.professionalProfile.domain.IProfessionalProfileService;
import com.appnutrix.professionalProfile.domain.ProfessionalProfile;
import com.appnutrix.professionalProfile.infrastructure.persistance.IProfessionalProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProfessionalProfileServiceImpl implements IProfessionalProfileService {

    @Autowired
    private IProfessionalProfileRepository professionalProfileRepository;

    @Override
    @Transactional
    public ProfessionalProfile save(ProfessionalProfile professionalprofile) throws Exception {
        return professionalProfileRepository.save(professionalprofile);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        professionalProfileRepository.deleteById(id);

    }

    @Override
    public List<ProfessionalProfile> getAll() throws Exception {
        return professionalProfileRepository.findAll();
    }

    @Override
    public Optional<ProfessionalProfile> getById(Integer id) throws Exception {
        return professionalProfileRepository.findById(id);
    }

    @Override
    public ProfessionalProfile findByNutritionist(Integer nutritionist_id) throws Exception{
        return professionalProfileRepository.findByNutritionist(nutritionist_id);
    }
}
