package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list all the tasks.
 */
public class ListCommand extends Command {
    /**
     * Creates a new list command.
     */
    public ListCommand() {
    }

    /**
     * Lists all the tasks from the task list.
     *
     * @param tasks is the task list that the command will execute with.
     * @param storage is the storage that the command will execute with.
     * @return a command response after executing the list command.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) {
        return new CommandResponse(Ui.printList(tasks), this.isExit());
    }
}
