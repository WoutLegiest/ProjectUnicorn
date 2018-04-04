package be.kuleuven.gent.project.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Teacher")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_User_User_LoginName", nullable = false)
    private String loginName;

    @Column(name = "School", nullable = false)
    private String school;

    public Teacher(Teacher teacher)
    {
        this.loginName =teacher.loginName;
        this.school = teacher.school;
    }

    public Teacher(){
        super();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginNaam) {
        this.loginName = loginNaam;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
