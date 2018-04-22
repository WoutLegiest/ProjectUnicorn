package be.kuleuven.gent.project.data;

import javax.persistence.*;
import java.io.Serializable;

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
    private String klas;

    @Column(name = "groupNr", nullable = false)
    private int grNr;

    public Student(){
        super();
    }

    public Student(User user, String school, String klas, int grNr) {
        this.user = user;
        this.school = school;
        this.klas = klas;
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

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        this.klas = klas;
    }

    public int getGrNr() {
        return grNr;
    }

    public void setGrNr(int grNr) {
        this.grNr = grNr;
    }
}

