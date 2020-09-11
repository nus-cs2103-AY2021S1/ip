package duke.user;

import duke.Storage;

import java.util.ArrayList;

public class UserList {
    public static User check(User currentUser) {
        Storage storage = new Storage("F:/XIA-LIYI/ip/src/main/data/userInfo.txt");
        ArrayList<User> users = storage.readUsersInfo();
        for (User user: users) {
            if (currentUser.isEqual(user)) {
                return user;
            }
        }
        return null;
    }
}
