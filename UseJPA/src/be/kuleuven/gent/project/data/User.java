package be.kuleuven.gent.project.data;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="User")
@SecondaryTable(name = "GroupUser", pkJoinColumns=@PrimaryKeyJoinColumn(name="Group_LoginNaam", referencedColumnName="LoginNaam"))
@NamedQueries({
        @NamedQuery(
        name="findUserById",query = "SELECT u FROM User u WHERE u.login=:LoginNaam")})
public class User implements Serializable {
    private static final long serialVersionUID = 4990525852036485337L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false, length = 16)
    private Long id;

    @Column(name = "Naam", nullable = false)
    private String name;

    @Column(name = "LoginNaam", nullable = false)
    private String login;

    @Column(name = "hPassword", nullable = false)
    private String hPassword;

    //@ManyToMany
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinTable(
//			name="Person_Group",
//			joinColumns={@JoinColumn(name="login", referencedColumnName="login")},
//			inverseJoinColumns={@JoinColumn(name="idGroup", referencedColumnName="idGroup")})

    @Column(table = "GroupUser", name = "GroupName")
    private String group;

    @Column(name = "EMail", nullable = false)
    private String Email;

    public User(User user)
    {
        this.id=user.id;
        this.name = user.name;
        this.login = user.login;
        this.hPassword = user.hPassword;
        this.Email = user.Email;
    }

    public User(String name, String login, String hPassword, String email, Long id) {
        this.name = name;
        this.login = login;
        this.hPassword = hPassword;
        Email = email;
        this.id=id;

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
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}