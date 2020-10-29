package pl.coderslab.charity.donation.controllers.restController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.donation.service.DonationService;

import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import java.security.Principal;
import java.util.List;

import java.util.Optional;


@RestController
@RequestMapping("/user/api/donations")
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

}
