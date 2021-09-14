package com.appnutrix.diet.infrastructure.persistance;

/*import com.appnutrix.diet.domain.Diet;*/
import com.appnutrix.diet.domain.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDietRepository extends JpaRepository<Diet, Integer> {

}
