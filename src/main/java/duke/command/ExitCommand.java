package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * The exit command flags the program to exit upon execution.
 * This terminates the chat bot.
 */
public class ExitCommand implements Command {

    public boolean isExit() {
        return true;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
        return "Bye!";
    }
}
