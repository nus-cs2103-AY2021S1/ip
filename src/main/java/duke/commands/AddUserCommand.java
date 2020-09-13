package duke.commands;

import duke.support.Storage;
import duke.task.TaskList;
import duke.user.User;
import duke.user.UserList;
import duke.ui.Ui;

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
