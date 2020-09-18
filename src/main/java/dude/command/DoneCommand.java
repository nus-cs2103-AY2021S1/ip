package dude.command;

import java.io.IOException;

import dude.ui.Ui;
import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;

/**
 * The command changes the status of an incomplete task to complete.
 */

public class DoneCommand extends Command {
    private int index;

    /**
     * Constructor for DoneCommand class.
     *
     * @param action action the command is to perform.
     * @param index index of task to be marked as complete.
     */
    public DoneCommand(String action, int index) {
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
            tasks.getTask(index - 1).markAsDone();
            StringBuilder uiOutput = new StringBuilder();
            uiOutput.append("Solid bro!! I've marked this task as done:\n").append(
                    tasks.getTask(index - 1).toString() + "\n");
            ui.setMessage(uiOutput.toString());
            storage.write(tasks.getTasks());
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Sorry! The task index you wanted to complete does not exist!");
        }
    }
}
