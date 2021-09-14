package com.appnutrix.diet.domain;

import java.util.List;
import java.util.Optional;

public interface IDietService {
    public Diet save(Diet diet) throws Exception;
    void delete(Integer id) throws Exception;
    List<Diet> getAll() throws  Exception;
    Optional<Diet> getById(Integer id) throws Exception;
}