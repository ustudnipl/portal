package pl.ustudni.portal.service;

import org.springframework.stereotype.Service;

@Service
public class SendingMailService {

    public boolean sendVerificationMail(String toEmail, String verificationCode) {
        return false;
    }

    private boolean sendMail(String toEmail, String subject, String body) {
        return false;
    }
}
