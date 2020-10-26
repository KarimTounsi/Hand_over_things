package pl.coderslab.charity.institution.service;

import pl.coderslab.charity.institution.DTOS.InstitutionDTO;
import pl.coderslab.charity.institution.entity.Institution;

import java.util.List;
import java.util.Optional;

public interface InstitutionService {

    List<Institution> getAll();

    List<Institution> getAllByStatus(boolean status);

    Institution saveInstitution(Institution institution);

    Optional<Institution> getById(Long id);

    Institution saveInstitutionFromDTO(InstitutionDTO institutionDTO);

}
