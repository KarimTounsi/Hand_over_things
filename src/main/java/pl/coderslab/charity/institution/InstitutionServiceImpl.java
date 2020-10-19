package pl.coderslab.charity.institution;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class InstitutionServiceImpl implements InstitutionService {

    InstitutionRepository institutionRepository;

    @Override
    public List<Institution> getAll() {
        return institutionRepository.findAll();
    }
}
