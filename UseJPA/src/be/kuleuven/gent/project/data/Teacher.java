package be.kuleuven.gent.project.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Teacher")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Group_User_User_LoginNaam", nullable = false)
    private String loginNaam;

    @Column(name = "School", nullable = false)
    private String school;

    public Teacher(Teacher teacher)
    {
        this.loginNaam=teacher.loginNaam;
        this.school = teacher.school;
    }

    public Teacher(){
        super();
    }

    public String getLoginNaam() {
        return loginNaam;
    }

    public void setLoginNaam(String loginNaam) {
        this.loginNaam = loginNaam;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
