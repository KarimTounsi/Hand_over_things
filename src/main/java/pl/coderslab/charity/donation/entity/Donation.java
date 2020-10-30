package pl.coderslab.charity.donation.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.category.entity.Category;
import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.user.entity.User;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Transactional
@Getter
@Setter
@Table(name = "donations")
public class Donation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Long id;

    @Column(name = "created")
    private LocalDateTime created;

    @ManyToOne
    private User user;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToMany
    @JoinTable(name = "donation_categories")
    private List<Category> categories;

    @OneToOne
    private Institution institution;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "pick_up_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @Column(name = "pick_up_time")
    private LocalTime pickUpTime;

    @Column(name = "pick_up_comment")
    private String pickUpComment;

    @Column(name = "phone_Number")
    private String phoneNumber;


    @Column(name = "receive_status")
    private boolean receiveStatus;

    @Column(name = "pick_up")
    private LocalDateTime pickUp;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        pickUp = LocalDateTime.now();
    }

}
