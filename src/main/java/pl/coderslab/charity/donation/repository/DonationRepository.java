package pl.coderslab.charity.donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.donation.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {





}
