package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.institution.Institution;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {





}
