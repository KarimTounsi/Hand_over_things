package pl.coderslab.charity.email.service;

import freemarker.template.TemplateException;
import pl.coderslab.charity.user.entity.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface EmailService {

    void sendRegisterEmail(User user) throws IOException, TemplateException, MessagingException;

    void sendResetPasswordEmail(String email) throws IOException, TemplateException, MessagingException, NoSuchAlgorithmException;

}
