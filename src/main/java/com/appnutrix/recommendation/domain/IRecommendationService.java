package com.appnutrix.recommendation.domain;

import java.util.List;
import java.util.Optional;
import java.util.Date;

public interface IRecommendationService {
    public Recommendation save(Recommendation recommendation) throws Exception;
    void delete(Integer id) throws Exception;
    List<Recommendation> getAll() throws  Exception;
    Optional<Recommendation> getById(Integer id) throws Exception;
    public List<Recommendation> findByName(String name) throws Exception;
    public List<Recommendation> findBetweenDates(Date date1, Date date2) throws Exception;
    public List<Recommendation> findByNutritionist(Integer nutritionist_id) throws Exception;
}