package pl.ustudni.portal.domain;

import javax.persistence.*;

@Entity
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EditionStatus status = EditionStatus.CREATED;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EditionStatus getStatus() {
        return status;
    }

    public void setStatus(EditionStatus status) {
        this.status = status;
    }
}
