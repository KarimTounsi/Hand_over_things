package pl.coderslab.charity.donation.controllers.restController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.donation.DTOS.DonationDTO;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.donation.service.DonationService;

import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import java.security.Principal;
import java.util.List;

import java.util.Optional;


@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
@Slf4j
public class DonationsController {


    private final DonationService donationService;
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<Donation>> findAllDonations(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        List<Donation> donations = donationService.getAllByUser(user);

        return ResponseEntity.ok(donations);
    }

    @GetMapping("/sortByCreatedDesc")
    public ResponseEntity<List<Donation>> findAllDonationsSortedByCreatedDesc(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        List<Donation> donations = donationService.getAllByUserOrderByCreatedDesc(user);
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/sortByCreatedAsc")
    public ResponseEntity<List<Donation>> findAllDonationsSortedByCreatedAsc(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        List<Donation> donations = donationService.getAllByUserOrderByCreatedAsc(user);
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/sortByReceiveStatusAsc")
    public ResponseEntity<List<Donation>> findAllDonationsSortedByStatusAsc(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        List<Donation> donations = donationService.getAllByUserOrderByReceiveStatusAsc(user);
        return ResponseEntity.ok(donations);
    }
    @GetMapping("/sortByReceiveStatusDesc")
    public ResponseEntity<List<Donation>> findAllDonationsSortedByStatusDesc(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        List<Donation> donations = donationService.getAllByUserOrderByReceiveStatusDesc(user);
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/sortByPickUpDesc")
    public ResponseEntity<List<Donation>> findAllDonationsSortedByPickUpDesc(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        List<Donation> donations = donationService.getAllByUserOrderByPickUpDesc(user);
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/sortByPickUpAsc")
    public ResponseEntity<List<Donation>> findAllDonationsSortedByPickUpAsc(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        List<Donation> donations = donationService.getAllByUserOrderByPickUpAsc(user);
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<Donation> donationOptional = donationService.getById(id);
        if (donationOptional.isPresent()) {
            Donation donation = donationOptional.get();
            return ResponseEntity.ok(donation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity updateOne(@RequestBody DonationDTO donationDTO, Principal principal) {
       if (donationService.getByIdAndUser(donationDTO.getId(),userService.getUserByEmail(principal.getName())) !=null){
           Optional<Donation> optionalDonation = donationService.getById(donationDTO.getId());
           if (optionalDonation.isPresent() ) {
               Donation donation = optionalDonation.get();
               donationService.updateDonation(donation);
           }
       }
        return ResponseEntity.noContent().build();
    }


}
