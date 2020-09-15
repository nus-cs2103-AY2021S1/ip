package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a exit command to be executed by Duke.
 * Exits the application.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        Ui.addMessage("Bye. Hope to see you again soon!");
    }
}
