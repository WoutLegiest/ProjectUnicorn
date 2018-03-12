package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name="StemExperiment")
public class StemExperiment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="StemProject_id", nullable=false, length=16)
    private Long id;

    @Column(name = "Student_Group_User_User_LoginNaam", nullable = false)
    private String userLogin;

    public StemExperiment(StemExperiment stemExperiment){
        this.id=stemExperiment.id;
        this.userLogin=stemExperiment.userLogin;
    }

    public StemExperiment(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userID) {
        this.userLogin = userID;
    }
}
