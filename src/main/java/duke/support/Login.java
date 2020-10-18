package duke.support;

import duke.user.User;
import duke.user.UserList;

/**
 * Represents log in record.
 */
public class Login {
    private static boolean isLogined = false;
    private static User user;

    /**
     * Logs in to the system with given user name and password. Creates a new user with input
     * and checks whether it is a valid user in {@code UserList}.
     *
     * @param username A String of username.
     * @param password A String of password.
     */
    public static void login(String username, String password) {
        User currentUser = new User(username, password);
        User validUser = UserList.check(currentUser);
        if (validUser != null) {
            isLogined = true;
            user = validUser;
        }
    }

    /**
     * Gets the User.
     *
     * @return {@code user}.
     */
    public static User getUser() {
        return user;
    }

    /**
     * Gets the current log information.
     *
     * @return {@code isLogined}.
     */
    public static boolean isLogined() {
        return isLogined;
    }

}

