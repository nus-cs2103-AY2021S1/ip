package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeInputException;

/**
 * Represents a user command to find tasks containing input keywords
 */
public class FindCommand extends ComplexCommand {

    /**
     * Creates a new <code>FindCommand</code> with the given parameters.
     *
     * @param params Parameters.
     */
    public FindCommand(String params) {
        super(params);
    }

    /**
     * Filters the tasks that match input keywords, then display them to screen.
     * Displays error message without terminating software loop if parameters are invalid.
     *
     * @param ui Print-out and display manager.
     * @param taskManager <code>Task</code> manipulation manager.
     * @param saveManager Handles saving and loading.
     */
    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        try {
            String keywords = this.parseParams();
            TaskManager filteredTasks = taskManager.filter(task -> task.getName().contains(keywords));

            ui.queueMessageToDisplay("Here are the matching tasks in your list:");
            ui.queueMessageToDisplay(filteredTasks.toString());

        } catch (DukeInputException e) {
            ui.displayException(e);
        }

    }

    private String parseParams() throws DukeInputException {

        // Check if params are empty
        if (this.params.equals("")) {
            throw new DukeInputException("'find' requires parameters.\n"
                    + "Use case: list <key words>");
        }

        assert this.params.length() > 0;

        return this.params;

    }

}
