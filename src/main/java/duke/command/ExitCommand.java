package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command, exiting the program.
     *
     * @param taskList A TaskList instance.
     * @param ui A Ui instance.
     * @param storage A Storage instance.
     * @throws DukeException if the tasks cannot be saved.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.save(taskList.getTasks());
        ui.showPrompt("Bye. Hope to see you again soon!");
    }

    /**
     * Returns true to indicate that the program should exit.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
