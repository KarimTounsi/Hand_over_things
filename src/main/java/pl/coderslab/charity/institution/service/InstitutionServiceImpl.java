package pl.coderslab.charity.institution.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.institution.DTOS.InstitutionDTO;
import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.institution.exceptions.ObjectNotFoundException;
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
    public InstitutionDTO saveInstitutionFromDTO(InstitutionDTO institutionDTO) {
        Institution institution = new Institution();
        institution.setStatus(institutionDTO.isStatus());
        institution.setDescription(institutionDTO.getDescription());
        institution.setName(institutionDTO.getName());
        Institution institutionSaved = institutionRepository.save(institution);
        return institutionDTO;
    }

    @Override
    public Institution getInstitutionById(Long id) {
        return institutionRepository.findInstitutionById(id);
    }

    @Override
    public Institution update(Institution institution) {

        Institution institutionInDb = institutionRepository.findInstitutionById(institution.getId());
        institutionInDb.setName(institution.getName());
        institutionInDb.setDescription(institution.getDescription());
        Institution institutionSaved = institutionRepository.save(institutionInDb);
        return institutionSaved;
    }

    @Override
    public Institution delete(Long id) {

        Institution institutionInDb = institutionRepository.findInstitutionById(id);
        institutionInDb.setStatus(false);
        Institution institutionDeleted = institutionRepository.save(institutionInDb);
        return institutionDeleted;
    }

    @Override
    public Optional<Institution> getById(Long id) {
        return institutionRepository.findById(id);
    }


    public Institution findInstitutionById(Long id) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(id);
        if (optionalInstitution.isEmpty()) throw new ObjectNotFoundException("not.found.institution");
        return optionalInstitution.get();
    }


}
