package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;

import duke.task.TaskList;
import duke.task.Task;

import duke.Ui;
import duke.Storage;

/**
 * Represents a command to search for tasks using a keyword.
 */
public class FindByKeywordCommand extends Command {

    private final String[] parsedCommand;

    /**
     * Creates and initialises a new FindByKeywordCommand.
     *
     * @param parsedCommand String array that contains the search keyword input.
     */
    public FindByKeywordCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Performs the operation of searching for all the tasks in the user's list
     * of tasks that contains the keyword provided for the search.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @throws DukeException If no tasks could be found due to invalid keyword provided.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            try {
                String keyword = this.parsedCommand[1].trim().toLowerCase();
                int index = 1;
                ui.printReply("Search Results:");
                for (Task task : tasks.getTaskList()) {
                    if (task.getDescription().toLowerCase().contains(keyword)) {
                        String results = String.format("%d. %s", index, task);
                        ui.printReply(results);
                        index++;
                    }
                }
                if (index == 1) {
                    ui.printReply("No tasks found! Please search using a different keyword!");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                String err = "No keyword for the search was entered. Please enter a keyword!";
                throw new InvalidFunctionException(err);
            }

    }

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return False since the DukeBot session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
