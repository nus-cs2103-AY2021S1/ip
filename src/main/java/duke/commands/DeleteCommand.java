package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * A class that represents Delete command.
 */
public class DeleteCommand {

    /**
     * Executes the delete command from user input.
     *
     * @param instruction the string of user input instruction.
     * @param tasks the tasklist that stores all the tasks.
     * @return a task object representing the task to be deleted.
     * @throws DukeException when the instruction format is incorrect.
     */
    public static Task executeCommand(String instruction, TaskList tasks) throws DukeException {
        if (instruction.substring(6).strip().equals("")) {
            throw new DukeException("The description of a delete message cannot be empty.");
        }
        Integer indexDelete = Integer.valueOf(instruction.substring(7).strip()) - 1;
        if (indexDelete + 1 > tasks.getSize() || indexDelete < 0) {
            throw new DukeException("The index of the task to be deleted is out of range.");
        }
        Task tempDelete = tasks.get(indexDelete);
        tasks.remove((int) indexDelete);

        return tempDelete;
    }
}
