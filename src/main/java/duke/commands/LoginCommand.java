package duke.commands;

import duke.Login;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class LoginCommand extends Command{
    public LoginCommand() {
    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        return Ui.login();
    }

}
