package pl.ustudni.portal.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="edition_id")
    private Edition edition;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private LocalDateTime appliedAt;

    private Boolean contactExchange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public Boolean getContactExchange() {
        return contactExchange;
    }

    public void setContactExchange(Boolean contactExchange) {
        this.contactExchange = contactExchange;
    }
}
