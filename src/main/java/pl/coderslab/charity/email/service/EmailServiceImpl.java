package pl.coderslab.charity.email.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import pl.coderslab.charity.user.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Override
    public void sendRegisterEmail(User user) throws IOException, TemplateException, MessagingException {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:templates/mail/templates");
        Configuration config = freeMarkerConfigurer.createConfiguration();

        Template mailTemplate = config.getTemplate("register-activation-email.ftlh");

        Map<String, Object> model = new HashMap<>();

        model.put("id", user.getId());
        model.put("email", user.getEmail());
        model.put("token", user.getToken());


        String mailBody = FreeMarkerTemplateUtils.processTemplateIntoString(mailTemplate, model);
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setFrom("karim.tounsi100@gmail.com");

        messageHelper.setSubject("Aktywuj swoje konto");
        messageHelper.setBcc(new String[]{user.getEmail()});
        messageHelper.setText(mailBody, true);
        mailSender.send(mimeMessage);
    }
}
