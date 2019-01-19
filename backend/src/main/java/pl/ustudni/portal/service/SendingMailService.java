package pl.ustudni.portal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class SendingMailService {
    Logger logger = LoggerFactory.getLogger(SendingMailService.class);

    //@Autowired
    //private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendVerificationMail(String toEmail, String verificationCode) {
        Context context = new Context();
        context.setVariable("verificationCode", verificationCode);
        String body = templateEngine.process("verificationMailTemplate", context);

        // TODO: probably needs change to mime message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("verification");
        message.setText(body);
        //mailSender.send(message);

        logger.info("sending mail:\n" + body);
    }
}
