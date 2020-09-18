package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * A class that represents Done command.
 */
public class DoneCommand {

    /**
     * Executes the done command from user input.
     *
     * @param instruction the string of user input instruction.
     * @param tasks the tasklist that stores all the tasks.
     * @return a task object representing the task to be done.
     * @throws DukeException when the instruction format is incorrect.
     */
    public static Task executeCommand(String instruction, TaskList tasks) throws DukeException {
        if (instruction.substring(4).strip().equals("")) {
            throw new DukeException("The description of a done message cannot be empty.");
        }
        Integer indexDone = Integer.valueOf(instruction.substring(5).strip()) - 1;
        if (indexDone + 1 > tasks.getSize() || indexDone < 0) {
            throw new DukeException("The index of the task to be done is out of range.");
        }
        Task tempDone = tasks.get(indexDone);
        tempDone.markAsDone();
        tasks.set(indexDone, tempDone);

        return tempDone;
    }
}
