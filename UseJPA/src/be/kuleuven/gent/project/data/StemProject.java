package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name="StemProject")
public class StemProject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, length=16)
    private Long id;

    @Column(name = "Teacher_Group_User_User_LoginNaam", nullable = false)
    private String teacherNaam;

    public StemProject(StemProject stemProject){
        this.id=stemProject.id;
        this.teacherNaam=stemProject.teacherNaam;
    }

    public StemProject(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherNaam() {
        return teacherNaam;
    }

    public void setTeacherNaam(String teacherNaam) {
        this.teacherNaam = teacherNaam;
    }
}
