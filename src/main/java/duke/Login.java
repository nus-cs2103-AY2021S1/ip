package duke;

import duke.user.User;
import duke.user.UserList;

public class Login {
    private static boolean isLogined = false;
    private static User user;

    public static void login(String username, String password) {
        User currentUser = new User(username, password);
        User validUser = UserList.check(currentUser);
        if (validUser != null) {
            isLogined = true;
            user = validUser;
        }
    }

    public static User getUser() {
        return user;
    }

    public static boolean isLogined() {
        return isLogined;
    }

}

