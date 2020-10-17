package duke.user;

/**
 * Represents a user.
 */
public class User {
    private String username;
    private String userPassword;
    private String nickname;

    /**
     * Creates a {@code User} with given username and password.
     *
     * @param username A String of username.
     * @param userPassword A String of password.
     */
    public User(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
        this.nickname = null;
    }

    /**
     * Creates a {@code User} with given username, password and nickname.
     *
     * @param username A String of username.
     * @param userPassword A String of password.
     * @param nickname A String of nickname.
     **/
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

    /**
     * Checks whether the user is same as itself.
     * @param user The user checked.
     * @return true if their usernames and passwords are same.
     */
    public boolean isEqual(User user) {
        if (user.getUsername().equals(username) && user.getUserPassword().equals(userPassword)) {
            return true;
        }
        return false;
    }
}
