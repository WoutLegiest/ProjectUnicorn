package be.kuleuven.gent.project.Support;

/**
 * Een lichtere versie van de gebruiker, gebruikt in de applicatie
 */
public class UserLight {
    private long id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String group;

    public UserLight() {
    }

    public UserLight(long id, String firstName, String lastName, String login, String email, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.group= group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
