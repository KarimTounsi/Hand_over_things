package pl.coderslab.charity.user.DTOS;


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import pl.coderslab.charity.user.validation.constraints.PasswordCheck;
import pl.coderslab.charity.user.validation.constraints.ResetPasswordCheck;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@ResetPasswordCheck
public class PasswordDTO {

    private Long id;
    @Length(min = 8, message = "*Twoje hasło musi mieć co najmniej 8 znaków")
    @NotEmpty(message = "*Proszę podać swoje hasło")
    @NotBlank(message = "*Rubryka nie może być pusta")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", flags = Pattern.Flag.UNICODE_CASE, message = "Hasło musi zawierać wielką literę, małą literę, cyfrę i znak specjalny")
    private String password;

    @NotBlank(message = "*Rubryka nie może być pusta")
    private String passwordCheck;

    private String token;
}
