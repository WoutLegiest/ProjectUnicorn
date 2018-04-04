package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name = "StemProject")
public class StemProject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 16)
    private Long id;

    @Column(name = "Teacher_Group_User_User_LoginName", nullable = false)
    private String teacherName;

    @Column(name = "className", nullable = false)
    private String className;

    public StemProject(StemProject stemProject) {
        this.id = stemProject.id;
        this.teacherName = stemProject.teacherName;
        this.className = stemProject.className;
    }

    public StemProject() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherNaam) {
        this.teacherName = teacherNaam;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
