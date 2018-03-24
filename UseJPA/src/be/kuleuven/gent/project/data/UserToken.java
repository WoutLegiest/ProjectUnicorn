package be.kuleuven.gent.project.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name="UserTokens")
public class UserToken implements Serializable {

    @Id
    @Column(name = "User_Login", nullable = false, length = 64)
    private String loginNaam;

    @Column(name = "Token", nullable = false)
    private String token;

    public UserToken(UserToken userToken)
    {
        this.loginNaam = userToken.loginNaam;
        this.token = userToken.token;
    }

    public UserToken(String loginNaam, String token) {
        this.loginNaam = loginNaam;
        this.token = token;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserToken(){
        super();
    }

    public String getLoginNaam() {
        return loginNaam;
    }

    public void setLoginNaam(String loginNaam) {
        this.loginNaam = loginNaam;
    }
}
