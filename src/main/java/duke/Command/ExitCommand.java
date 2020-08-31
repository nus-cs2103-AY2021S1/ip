package duke.command;

import duke.Storage;

import duke.task.TaskList;

import duke.ui.Message;
import duke.ui.Ui;

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