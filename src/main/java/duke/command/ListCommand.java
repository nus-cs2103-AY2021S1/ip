package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a list command. A ListCommand object represents a command
 * to list all tasks stored in the task list. These tasks can be either a todo, deadline or event.
 */
public class ListCommand extends Command {

    /**
     * Creates a new ListCommand.
     *
     * @param userStrings Tokenized array form of the input command string.
     */
    public ListCommand(String[] userStrings) {
        super(userStrings);
    }

    /**
     * Prints the contents of the task list.
     *
     * @param tasks The task list to operate on.
     * @param ui The user-interaction object responsible for all system printing and user-interaction.
     * @param storage Represents the logic needed to write to an user-specified file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
