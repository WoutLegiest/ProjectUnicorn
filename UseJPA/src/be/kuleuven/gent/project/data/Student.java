package be.kuleuven.gent.project.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Dit is een object van een Student gebruiker, deze geeft nog extra attributen aan de gewone {@link #user}. De primaire sleutel
 * is hier de loginName
 */
@Entity
@Table(name="Student")
public class Student implements Serializable {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStudent", nullable = false)
    private long idStudent;

    @Id
    @OneToOne
    @JoinColumn(name="user_LoginName", nullable = false)
    private User user;

    @Column(name = "school", nullable = false)
    private String school;

    @Column(name = "className", nullable = false)
    private String className;

    @Column(name = "groupNr", nullable = false)
    private int grNr;

    public Student(){
        super();
    }

    public Student(User user, String school, String className, int grNr) {
        this.user = user;
        this.school = school;
        this.className = className;
        this.grNr = grNr;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String klas) {
        this.className = klas;
    }

    public int getGrNr() {
        return grNr;
    }

    public void setGrNr(int grNr) {
        this.grNr = grNr;
    }
}

