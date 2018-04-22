package be.kuleuven.gent.project.Support;

public class UserLightStudent extends UserLight {
    private String school;
    private String className;
    private int groupNr;

    public UserLightStudent(String school, String className, int groupNr) {
        this.school = school;
        this.className = className;
        this.groupNr = groupNr;
    }

    public UserLightStudent(long id, String firstName, String lastName, String login, String email, String group, String school, String className, int groupNr) {
        super(id, firstName, lastName, login, email, group);
        this.school = school;
        this.className = className;
        this.groupNr = groupNr;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getGroupNr() {
        return groupNr;
    }

    public void setGroupNr(int groupNr) {
        this.groupNr = groupNr;
    }
}
