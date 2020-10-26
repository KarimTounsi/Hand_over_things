package pl.coderslab.charity.user.DTOS;


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.coderslab.charity.user.validation.constraints.PasswordCheck2;
import pl.coderslab.charity.user.validation.constraints.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserEmailDTO {

    private Long id;

    @Email(message = "*Podaj prawidłowy adres e-mail")
    @NotEmpty(message = "*Proszę podać e-mail")
    @NotBlank(message = "*Rubryka nie może być pusta")
    @UniqueEmail
    private String email;

}
