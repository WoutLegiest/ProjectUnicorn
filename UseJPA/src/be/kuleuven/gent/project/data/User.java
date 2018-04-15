package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name="user")
@SecondaryTable(name="groupuser", pkJoinColumns=@PrimaryKeyJoinColumn(name="group_LoginName", referencedColumnName="loginName"))
public class User implements Serializable {
    private static final long serialVersionUID = 4990525852036485337L;

    //@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false, length = 16)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Id
    @Column(name = "loginName", nullable = false)
    private String login;

    @Column(table = "groupuser", name = "group_LoginName")
    private String groupLogin;

    @Column(name = "hPassword", nullable = false)
    private String hPassword;

    @Column(name = "description")
    private String descripton;

    @Column(table = "groupuser", name = "groupName")
    private String group;

    @Column(name = "eMail", nullable = false)
    private String email;

    public User(User user) {
        this.id=user.id;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getGroupLogin() {
        return groupLogin;
    }

    public void setGroupLogin(String groupLogin) {
        this.groupLogin = groupLogin;
    }

    public String getFullName(){
        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        return sb.toString();
    }
}