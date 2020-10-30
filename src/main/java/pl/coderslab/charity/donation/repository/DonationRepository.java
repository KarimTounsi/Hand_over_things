package pl.coderslab.charity.donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.user.entity.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {


    List<Donation> findAllByUser(User user);
    List<Donation> findAllByUserOrderByCreatedAsc(User user);
    List<Donation> findAllByUserOrderByCreatedDesc(User user);
    List<Donation> findAllByUserOrderByReceiveStatusAsc(User user);
    List<Donation> findAllByUserOrderByReceiveStatusDesc(User user);
    List<Donation> findAllByUserAndReceiveStatusTrueOrderByPickUpDesc(User user);
    List<Donation> findAllByUserAndReceiveStatusTrueOrderByPickUpAsc(User user);

    Donation findByIdAndUser(Long id, User user);



}
