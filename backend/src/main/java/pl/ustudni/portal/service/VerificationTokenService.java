package pl.ustudni.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.ustudni.portal.dao.UserRepository;
import pl.ustudni.portal.dao.VerificationTokenRepository;
import pl.ustudni.portal.exception.TokenExpiredException;
import pl.ustudni.portal.exception.TokenInvalidException;
import pl.ustudni.portal.exception.UserNotFoundException;
import pl.ustudni.portal.model.User;
import pl.ustudni.portal.model.VerificationToken;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationTokenService {
    private UserRepository userRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private SendingMailService sendingMailService;

    @Autowired
    public VerificationTokenService(@Qualifier("userRepository") UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, SendingMailService sendingMailService){
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.sendingMailService = sendingMailService;
    }

    public void createVerification(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException();
//            user = new User();
//            user.setEmail(email);
//            userRepository.save(user);
        }

        List<VerificationToken> verificationTokens = verificationTokenRepository.findByUserEmail(email);
        VerificationToken verificationToken;
        if (verificationTokens.isEmpty()) {
            verificationToken = new VerificationToken();
            verificationToken.setUser(user);
            verificationTokenRepository.save(verificationToken);
        } else {
            verificationToken = verificationTokens.get(0);
        }

        sendingMailService.sendVerificationMail(email, verificationToken.getToken());
    }

    public void verifyEmail(String token) {
        List<VerificationToken> verificationTokens = verificationTokenRepository.findByToken(token);

        if (verificationTokens.isEmpty())
            throw new TokenInvalidException();

        VerificationToken verificationToken = verificationTokens.get(0);

        if (verificationToken.hasExpired())
            throw new TokenExpiredException();

        verificationToken.setConfirmedDateTime(LocalDateTime.now());
        verificationToken.setStatus(VerificationToken.STATUS_VERIFIED);
        verificationToken.getUser().setActive(true);
        verificationTokenRepository.save(verificationToken);
    }
}
