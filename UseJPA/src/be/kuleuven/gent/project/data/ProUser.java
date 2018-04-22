package be.kuleuven.gent.project.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Dit is een object van een ProUser gebruiker, deze geeft nog extra attributen aan de gewone {@link #user}. De primaire sleutel
 * is hier de loginName
 */
@Entity
@Table(name="ProUser")
public class ProUser implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProUser", nullable = false)
    private Long id;

    @Id
    @OneToOne
    @JoinColumn(name="loginName", nullable = false)
    private User user;

    @Column(name = "Company", nullable = false)
    private String company;

    public ProUser() {
        super();
    }

    public ProUser(User user) {
        this.user = user;
        this.company = "";
    }

    public ProUser(User user, String company) {
        this.user = user;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
