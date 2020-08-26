package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeInputException;
import duke.task.Task;

/**
 * Represents a user command to delete a class from Duke.
 */
public class DeleteCommand extends ComplexCommand {

    /**
     * Creates a <code>DeleteCommand</code> with the given parameters.
     *
     * @param params Parameters.
     */
    public DeleteCommand(String params) {
        super(params);
    }

    /**
     * Deletes <code>Task</code> based on given index and displays information about it.
     * Displays an error message without terminating software loop if parameters are invalid.
     *
     * @param ui Print-out and display manager.
     * @param taskManager <code>Task</code> manipulation manager.
     * @param saveManager Handles saving and loading.
     */
    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        try {
            int index = this.parseParams(taskManager.size());
            Task temp = taskManager.removeTask(index-1);

            ui.display("Alright. I've removed this task:");
            ui.display("\t" + temp.toString());
            ui.display("Now you have " + taskManager.size() + " tasks in the list.");
        } catch (DukeInputException e) {
            ui.displayException(e);
        }

    }

    private int parseParams(int taskManagerSize) throws DukeInputException {
        if (this.params.equals("")) {
            throw new DukeInputException("'delete' requires parameters.\n"
                    + "Use case: delete <task number>");
        }
        int i;
        try {
            i = Integer.parseInt(params);
        } catch (NumberFormatException e) {
            throw new DukeInputException("Please input number instead of <"
                    + this.params + "> after a 'delete' command!");
        }

        if (i < 1 || i > taskManagerSize) {
            throw new DukeInputException("Index input out of range");
        }
        return i;
    }

}
