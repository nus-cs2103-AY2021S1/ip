package duke.commands;

import duke.support.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.user.User;
import duke.user.UserList;

/**
 * Adds a user to the user list.
 */
public class AddUserCommand extends Command {
    private User user;

    /**
     * Creates a {@code AddUserCommand} with given user.
     */
    public AddUserCommand(User user) {
        this.user = user;
    }

    /**
     * Adds the {@code user} to the user list.
     */
    public String run(TaskList taskList, Storage storage) {
        UserList.addUser(user);
        return Ui.addUser();
    }
}
