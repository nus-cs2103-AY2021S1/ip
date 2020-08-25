package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    private String userInput;

    public ExitCommand(String userInput) {
        this.userInput = userInput;
    }

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    Bye. Hope to see you again soon!\n");
    }
}

