package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name="User")
@SecondaryTable(name = "GroupUser", pkJoinColumns=@PrimaryKeyJoinColumn(name="group_LoginName", referencedColumnName="LoginName"))
public class User implements Serializable {
    private static final long serialVersionUID = 4990525852036485337L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false, length = 16)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "loginName", nullable = false)
    private String login;

    @Column(name = "hPassword", nullable = false)
    private String hPassword;

    @Column(name = "description")
    private String descripton;

    //@ManyToMany
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinTable(
//			name="Person_Group",
//			joinColumns={@JoinColumn(name="login", referencedColumnName="login")},
//			inverseJoinColumns={@JoinColumn(name="idGroup", referencedColumnName="idGroup")})

    @Column(table = "GroupUser", name = "groupName")
    private String group;

    @Column(name = "eMail", nullable = false)
    private String email;

    public User(User user)
    {
        this.id=user.id;
        this.name = user.name;
        this.login = user.login;
        this.hPassword = user.hPassword;
        this.email = user.email;
        this.descripton = user.descripton;
    }

    public User(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String gethPassword() {
        return hPassword;
    }

    public void sethPassword(String hPassword) {
        this.hPassword = hPassword;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }
}