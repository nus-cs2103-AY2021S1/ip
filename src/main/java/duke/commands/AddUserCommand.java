package duke.commands;

import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.user.User;
import duke.user.UserList;

public class AddUserCommand extends Command {
    private User user;

    public AddUserCommand(User user) {
        this.user = user;
    }

    public String run(TaskList taskList, Storage storage) {
        UserList.addUser(user);
        return Ui.addUser();
    }
}
