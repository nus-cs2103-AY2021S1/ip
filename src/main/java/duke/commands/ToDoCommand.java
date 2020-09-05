package duke.commands;

import duke.exception.DukeException;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * A class that represents ToDo command.
 */
public class ToDoCommand {

    /**
     * Executes the ToDo command from user input.
     *
     * @param instruction the string of user input instruction.
     * @param tasks the tasklist that stores all the tasks.
     * @throws DukeException when the instruction format is incorrect.
     */
    public static void executeCommand(String instruction, TaskList tasks) throws DukeException {
        if (instruction.substring(4).strip().equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        tasks.add(new ToDo(instruction.substring(4).strip()));
    }
}
