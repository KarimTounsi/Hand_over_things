package pl.coderslab.charity.institution;

import java.util.List;
import java.util.Optional;

public interface InstitutionService {

    List<Institution> getAll();
    List<Institution> getAllByStatus(boolean status);
    Institution saveInstitution(Institution institution);
    Optional<Institution> getById(Long id);
    void deleteInstitution(Institution institution);
}
