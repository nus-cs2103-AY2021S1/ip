package commands;

import duke.Storage;
import duke.Ui;
import exceptions.InvalidFileException;
import exceptions.InvalidTaskNumberException;
import exceptions.TaskNotFoundException;
import tasks.TaskList;

/**
 * Represents an instruction from the user to mark a task as done
 */

public class DoneCommand extends Command {
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the task as done
     * @param tasks The current TaskList
     * @param ui The Ui object in use
     * @param storage The Storage object in use
     * @throws TaskNotFoundException If input task number is not found in the list
     * @throws InvalidTaskNumberException If the user enters a non-integer argument
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws TaskNotFoundException,
            InvalidTaskNumberException {
        ui.printDoneTask();
        doneTask(index, tasks);
    }

    /**
     * Marks an input task as done
     * @param idx Index of the task which the user wishes to mark as done
     * @throws TaskNotFoundException If input task number is not found in the list
     * @throws InvalidTaskNumberException If user enters a non-integer input
     */

    public void doneTask(int idx, TaskList tasks) throws TaskNotFoundException, InvalidTaskNumberException {
        try {
            tasks.getTasks().get(idx - 1).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException("Sorry, I couldn't find that task.");
        } catch (NumberFormatException ex) {
            throw new InvalidTaskNumberException("Please enter a valid number!");
        }
    }
}
