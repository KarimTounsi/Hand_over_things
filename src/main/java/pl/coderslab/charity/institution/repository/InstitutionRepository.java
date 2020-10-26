package pl.coderslab.charity.institution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.institution.entity.Institution;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    List<Institution> findAllByStatusOrderById(boolean status);

}
