package pl.ustudni.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ustudni.portal.model.VerificationToken;

import java.util.List;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
    List<VerificationToken> findByUserEmail(String email);
    List<VerificationToken> findByToken(String token);
}
