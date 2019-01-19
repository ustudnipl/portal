package pl.ustudni.portal.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Edition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edition_id")
    private Long id;

    private LocalDateTime startsAt;

    private LocalDateTime canApplyUntil;

    private State state;

    public enum State {
        OPEN, CLOSE
    }
}
