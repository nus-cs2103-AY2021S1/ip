package duke.command;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class UserCommand {

    protected String userInput;

    public Boolean isExit = false;

    public UserCommand(String userInput) {
        this.userInput = userInput;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    public boolean isExit() {
        return isExit();
    }
}
