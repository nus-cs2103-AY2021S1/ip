public class Friend {

    private String name;
    private String birthday;
    private int phoneNumber;
    private boolean isClose;

    public Friend(String name) {
        this.name = name;
        this.birthday = "01/01/2000";
        this.phoneNumber = 91234567;
        this.isClose = false;
    }

    public Friend(String name, String birthday, int phoneNumber, boolean isClose) {
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.isClose = isClose;
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public void updateNumber(int newNumber) {
        this.phoneNumber = newNumber;
    }

    public void updateRelation(boolean isClose) {
        this.isClose = isClose;
    }

    public String getName() {
        return this.name;
    }
}
