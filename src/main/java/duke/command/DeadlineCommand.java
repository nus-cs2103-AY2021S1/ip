package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeInputException;
import duke.task.Deadline;

/**
 * Represents a user command to add a new <code>Deadline</code> to Duke.
 */
public class DeadlineCommand extends ComplexCommand {

    /**
     * Creates a <code>Deadline</code> using the given parameters.
     *
     * @param params Parameters.
     */
    public DeadlineCommand(String params) {
        super(params);
    }

    /**
     * Creates a new <code>Deadline</code> and adds it to <code>taskManager</code>.
     * Parses the command and displays an error message without terminating software loop if parameters invalid.
     *
     * @param ui Print-out and display manager.
     * @param taskManager <code>Task</code> manipulation manager.
     * @param saveManager Handles saving and loading.
     */
    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        try {
            // Attempt to parse parameters and create new Deadline
            String[] splitParams = this.parseParams();
            Deadline newDeadline = new Deadline(splitParams[0], splitParams[1]);

            // Store new deadline in taskManager
            taskManager.storeTask(newDeadline);

        } catch (DukeInputException e) {
            ui.displayException(e);
        }
    }

    private String[] parseParams() throws DukeInputException {

        // Check if parameters are empty
        if (this.params.equals("")) {
            throw new DukeInputException("'deadline' requires parameters.\n"
                    + "Use case: deadline <name> /by <deadline>");
        }

        // Check if required second parameter is given
        String[] splitParams = this.params.split("/by ", 2);
        if (splitParams.length != 2) {
            throw new DukeInputException("<" + this.params + "> is not valid for the 'deadline' command.\n"
                    + "Please add a /by deadline to the task.");
        }

        return splitParams;
    }

}
