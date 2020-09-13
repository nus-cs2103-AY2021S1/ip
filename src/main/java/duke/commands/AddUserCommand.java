package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.user.User;
import duke.user.UserList;
import duke.Ui;

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
