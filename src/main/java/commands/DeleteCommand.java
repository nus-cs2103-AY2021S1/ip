package commands;

import duke.Storage;
import duke.Ui;
import exceptions.InvalidFileException;
import exceptions.InvalidTaskNumberException;
import exceptions.TaskNotFoundException;
import tasks.TaskList;

/**
 * Represents an instruction from the user to delete a task from the list
 */

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the task from the TaskList
     * @param tasks The current TaskList
     * @param ui The Ui object in use
     * @param storage The Storage object in use
     * @throws TaskNotFoundException If input task number is not found in the list
     * @throws InvalidTaskNumberException If the user enters a non-integer argument
     * @throws InvalidFileException If the file to be written to cannot not found
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws TaskNotFoundException,
            InvalidTaskNumberException, InvalidFileException {
        ui.printDelTask(tasks, index);
        deleteTask(index, tasks);
        storage.writeToFile("data.txt", tasks.writeString());
    }

    /**
     * Deletes an input task from the list
     * @param idx Index of the task indicated to be deleted
     * @throws TaskNotFoundException If input task number is not found in the list
     * @throws InvalidTaskNumberException If user enters a non-integer argument
     */
    public void deleteTask(int idx, TaskList tasks) throws TaskNotFoundException, InvalidTaskNumberException {
        try {
            tasks.getTasks().remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException("Sorry, I couldn't find that task.");
        } catch (NumberFormatException ex) {
            throw new InvalidTaskNumberException("Please enter a valid number!");
        }
    }
}
