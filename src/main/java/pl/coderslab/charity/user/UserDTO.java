package pl.coderslab.charity.user;


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@PasswordCheck
public class UserDTO {


    @Email(message = "*Podaj prawidłowy adres e-mail")
    @NotEmpty(message = "*Proszę podać e-mail")
    @NotBlank(message = "*Rubryka nie może być pusta")
    @UniqueEmail
    private String email;

    @Length(min = 8, message = "*Twoje hasło musi mieć co najmniej 8 znaków")
    @NotEmpty(message = "*Proszę podać swoje hasło")
    @NotBlank(message = "*Rubryka nie może być pusta")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", flags = Pattern.Flag.UNICODE_CASE, message = "Hasło musi zawierać wielką literę, małą literę, cyfrę i znak specjalny")
    private String password;

    @NotBlank(message = "*Rubryka nie może być pusta")
    private String passwordCheck;


}
