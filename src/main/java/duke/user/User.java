package duke.user;

public class User {
    private String username;
    private String userPassword;
    private String nickname;

    public User(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
        this.nickname = null;
    }

    public User(String username, String userPassword, String nickname) {
        this.username = username;
        this.userPassword = userPassword;
        this.nickname = nickname;
    }

    public String getUsername() {
        return this.username;
    }
    public String getUserPassword() {
        return this.userPassword;
    }
    public String getNickname() {
        return this.nickname;
    }

    public boolean isEqual(User user) {
        if (user.getUsername().equals(username) && user.getUserPassword().equals(userPassword)) {
            return true;
        }
        return false;
    }
}
