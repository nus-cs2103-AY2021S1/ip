package duke.Command;

import duke.Storage;

import duke.Task.TaskList;

import duke.Ui.Message;
import duke.Ui.Ui;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.scanner.close();
        return Message.MESSAGE_EXIT;
    }
}