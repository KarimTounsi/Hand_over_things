package pl.coderslab.charity.donation.DTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.category.entity.Category;
import pl.coderslab.charity.institution.entity.Institution;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationDTO {

    private Long id;

    private Integer quantity;


    private List<Category> categories;


    private Institution institution;


    private String street;


    private String city;


    private String zipCode;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;


    private LocalTime pickUpTime;


    private String pickUpComment;


    private String phoneNumber;

    private boolean receiveStatus;

}
