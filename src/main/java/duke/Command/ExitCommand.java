package duke.command;

import duke.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_EXIT;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an <code>ExitCommand</code> Object.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.scanner.close();
        return MESSAGE_EXIT;
    }
}
