package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class CloseCommand extends Command {
    public static final String COMMAND = "close";

    public CloseCommand() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.exit(0); // close the program
        return "";
    }
}
