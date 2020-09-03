package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an find command.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD_FIND = "find";
    private static final String MESSAGE_ADD_ACKNOWLEDGEMENT = "HmmMm let me recall... I think I've eaten the " +
            "following that match your description: ";
    private String keyword;

    /**
     * Creates an FindCommand instance
     *
     * @param keyword A string representing the keyword to be found in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches current task list for task containing keyword and displays the tasks to user.
     *
     * @param tasks Task list representing current tasks.
     * @param ui User interface interacting with user.
     * @param storage Storage Storage in charge of saving file to hard disk.
     * @throws DukeException If unable to either add task or save task file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(String.format("%s\n%s", MESSAGE_ADD_ACKNOWLEDGEMENT,
                tasks.findMatchingTasks(keyword)));
    }
}
