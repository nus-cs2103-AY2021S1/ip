package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        Ui.addMessage("Bye. Hope to see you again soon!");
        Ui.sendMessages();
        System.exit(0);
    }
}
