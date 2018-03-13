package be.kuleuven.gent.project;

public class UserLight {
    private long id;
    private String naam;
    private String Login;
    private String email;
    private String group;
    private String token;

    public UserLight(long id, String naam, String login, String email, String group) {
        this.id = id;
        this.naam = naam;
        this.Login = login;
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

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
