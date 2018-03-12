package be.kuleuven.gent.project.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Group_User_User_LoginNaam", nullable = false)
    private String loginNaam;


    public Student(Student student)
    {
        this.loginNaam=student.loginNaam;
    }

    public Student(){
        super();
    }

    public String getLoginNaam() {
        return loginNaam;
    }

    public void setLoginNaam(String loginNaam) {
        this.loginNaam = loginNaam;
    }


}

