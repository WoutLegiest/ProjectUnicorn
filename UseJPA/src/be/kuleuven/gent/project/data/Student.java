package be.kuleuven.gent.project.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_LoginName", nullable = false)
    private String loginName;


    public Student(Student student)
    {
        this.loginName =student.loginName;
    }

    public Student(){
        super();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginNaam) {
        this.loginName = loginNaam;
    }


}

