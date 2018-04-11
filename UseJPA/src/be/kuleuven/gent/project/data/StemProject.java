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

    @Column(name = "teacher_User_LoginName", nullable = false)
    private String teacherName;

    @Column(name = "className", nullable = false)
    private String className;

    @Column(name = "description", nullable = false)
    private String description;

    public StemProject() {
        super();
    }

    public StemProject(String teacherName, String className, String description) {
        this.teacherName = teacherName;
        this.className = className;
        this.description = description;
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

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
