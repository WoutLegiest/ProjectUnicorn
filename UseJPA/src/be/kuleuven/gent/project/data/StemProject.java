package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Dit StemProject object is gemaakt om een Stem Project aan te maken. Dit bevat algemene info over het project en de
 * ID van dit project is dan te vinden in de STEMMeasurement objecten. Anders dan de professionele objecten bevatten deze projecten
 * ook een klasnaam en een leekracht.
 */
@XmlRootElement
@Entity
@Table(name = "StemProject")
public class StemProject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 16)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_LoginName", nullable = false)
    private Teacher teacher;

    @Column(name = "className", nullable = false)
    private String className;

    @Column(name = "description", nullable = false)
    private String description;

    public StemProject() {
        super();
    }

    public StemProject(Teacher teacher, String className, String description) {
        this.teacher = teacher;
        this.className = className;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
