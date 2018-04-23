package be.kuleuven.gent.project.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Dit is een object van een Teacher gebruiker, deze geeft nog extra attributen aan de gewone {@link #user}. De primaire sleutel
 * is hier de loginName
 */
@Entity
@Table(name="Teacher")
public class  Teacher implements Serializable {

    private static final long serialVersionUID = 8790057009352490441L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTeacher", nullable = false)
    private Long id;

    @Id
    @OneToOne
    @JoinColumn(name="loginName", nullable = false)
    private User user;

    @Column(name = "School", nullable = false)
    private String school;

    public Teacher() {
        super();
    }

    public Teacher(User user) {
        this.user = user;
        this.school = "";
    }

    public Teacher(User user, String school) {
        this.user = user;
        this.school = school;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
