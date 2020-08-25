package duke.command;

import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskListHandler handler, Storage storage) {
        System.out.println("Bye bye! Hope to see you again soon!");
        Ui.stopRunning();
    }
}
