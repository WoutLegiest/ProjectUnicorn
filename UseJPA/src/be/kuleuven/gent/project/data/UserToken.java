package be.kuleuven.gent.project.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;

/**
 * Hierin worden de tokens van de gebruiker opgeslaan, deze token worden gebruikt in de applicatie.
 */
@XmlRootElement
@Entity
@Table(name = "UserTokens")
public class UserToken implements Serializable {

    @Id
    @Column(name = "user_Login", nullable = false, length = 64)
    private String loginName;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "date", nullable = false)
    private java.sql.Date date;

    public UserToken(UserToken userToken) {
        this.loginName = userToken.loginName;
        this.token = userToken.token;
        this.date = userToken.date;
    }

    public UserToken(String loginName, String token) {
        this.loginName = loginName;
        this.token = token;

    }

    public UserToken(String loginName, String token, Date date) {
        this.loginName = loginName;
        this.token = token;
        this.date = date;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder hulp = new StringBuilder();
        hulp.append("login: ");
        hulp.append(loginName);
        hulp.append(", ");
        hulp.append("token: ");
        hulp.append(token);
        hulp.append(", ");
        hulp.append("datum: ");
        hulp.append(date);
        hulp.append(". ");
        return hulp.toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserToken() {
        super();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginNaam) {
        this.loginName = loginNaam;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }
}
