package be.kuleuven.gent.project;

public class UserLight {
    private long id;
    private String name;
    private String login;
    private String email;
    private String group;
    private String token;

    public UserLight(long id, String name, String login, String email, String group) {
        this.id = id;
        this.name = name;
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
