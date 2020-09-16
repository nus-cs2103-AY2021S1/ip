package dude.command;

import java.io.IOException;

import dude.ui.Ui;
import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;

/**
 * DeleteCommand deletes a task from the tasklist.
 */

public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand class.
     *
     * @param action action the command is to perform.
     * @param index index of task to be deleted.
     */
    public DeleteCommand(String action, int index) {
        super(action);
        this.index = index;
    }

    /**
     * Adds the task to the TaskList, stores the data and displays the resulting output.
     *
     * @param tasks TaskList containing all the current tasks.
     * @param ui Ui class to display output.
     * @param storage Storage class to store tasks.
     * @throws CommandException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        try {
            StringBuilder uiOutput = new StringBuilder();
            uiOutput.append("Understood. I've removed this task:\n  ").append(
                    tasks.getTask(index - 1).toString()).append("Now you have ").append(
                    tasks.getCount() - 1).append(" tasks in the list.");
            tasks.deleteTask(index - 1);
            ui.setMessage(uiOutput.toString());
            storage.write(tasks.getTasks());
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Sorry! The index to be removed does not exist!");
        }
    }
}
