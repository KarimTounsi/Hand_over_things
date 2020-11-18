package pl.coderslab.charity.institution.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.institution.DTOS.InstitutionDTO;
import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.institution.repository.InstitutionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class InstitutionServiceImpl implements InstitutionService {

    InstitutionRepository institutionRepository;

    @Override
    public List<Institution> getAll() {
        return institutionRepository.findAll();
    }

    @Override
    public List<Institution> getAllByStatus(boolean status) {
        return institutionRepository.findAllByStatusOrderById(status);
    }

    @Override
    public Institution saveInstitution(Institution institution) {
        return institutionRepository.save(institution);
    }

    @Override
    public Institution saveInstitutionFromDTO(InstitutionDTO institutionDTO) {
        Institution institution = new Institution();
        institution.setStatus(institutionDTO.isStatus());
        institution.setDescription(institutionDTO.getDescription());
        institution.setName(institutionDTO.getName());
        return institutionRepository.save(institution);
    }

    @Override
    public Institution getInstitutionById(Long id) {
        return institutionRepository.findInstitutionById(id);
    }

    @Override
    public Optional<Institution> getById(Long id) {
        return institutionRepository.findById(id);
    }


}
