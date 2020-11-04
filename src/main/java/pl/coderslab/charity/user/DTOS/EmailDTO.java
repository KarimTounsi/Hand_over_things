package pl.coderslab.charity.user.DTOS;


import lombok.Data;
import pl.coderslab.charity.user.validation.constraints.EmailCheck;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Data
public class EmailDTO {

    @Email(message = "*Podaj prawidłowy adres e-mail")
    @NotEmpty(message = "*Proszę podać e-mail")
    @NotBlank(message = "*Rubryka nie może być pusta")
    @EmailCheck
    private String email;


}
