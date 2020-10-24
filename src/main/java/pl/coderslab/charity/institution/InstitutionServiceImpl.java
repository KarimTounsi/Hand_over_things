package pl.coderslab.charity.institution;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        return institutionRepository.findAllByStatus(status);
    }

    @Override
    public Institution saveInstitution(Institution institution) {
        return institutionRepository.save(institution);
    }

    @Override
    public Optional<Institution> getById(Long id) {
        return institutionRepository.findById(id);
    }

    @Override
    public void deleteInstitution(Institution institution) {
        institutionRepository.delete(institution);
    }
}
