package pl.ustudni.portal.model;

import javax.persistence.*;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="edition_id")
    private Edition edition;

    @ManyToOne
    private User male;

    @ManyToOne
    private User female;

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

    public User getMale() {
        return male;
    }

    public void setMale(User male) {
        this.male = male;
    }

    public User getFemale() {
        return female;
    }

    public void setFemale(User female) {
        this.female = female;
    }
}
