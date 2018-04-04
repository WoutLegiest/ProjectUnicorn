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
    private String loginName;

    @Column(name = "Token", nullable = false)
    private String token;

    public UserToken(UserToken userToken)
    {
        this.loginName = userToken.loginName;
        this.token = userToken.token;
    }

    public UserToken(String loginName, String token) {
        this.loginName = loginName;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginNaam) {
        this.loginName = loginNaam;
    }
}
