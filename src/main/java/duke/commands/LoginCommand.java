package duke.commands;

import duke.support.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class LoginCommand extends Command{
    public LoginCommand() {
    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        return Ui.login();
    }

}
