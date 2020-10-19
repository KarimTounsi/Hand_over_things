package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class DonationServiceImpl implements DonationService {

    DonationRepository donationRepository;

    @Override
    public List<Donation> getAll() {
        return donationRepository.findAll();
    }

    @Override
    public Integer getSumOfQuantity() {
        return donationRepository.findAll().stream().mapToInt(Donation::getQuantity).sum();
    }

    @Override
    public Integer getListSize() {
        return donationRepository.findAll().size();
    }


}
