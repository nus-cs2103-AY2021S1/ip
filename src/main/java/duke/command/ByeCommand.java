package duke.command;

import duke.Storage;
import duke.taskListHandler;
import duke.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(taskListHandler handler, Storage storage) {
        System.out.println("Bye bye! Hope to see you again soon!");
        Ui.stopRunning();
    }
}
