package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(new Ui().availableCommands());
    }

    public void execute (Task t, TaskList userTasks) {

    }
}
