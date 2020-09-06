package duke.commands;

import static duke.parser.Parser.parseDateTime;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;





/**
 * A class that represents Update command.
 */
public class UpdateCommand {

    /**
     * Executes the Update command from user input.
     *
     * @param instruction the string of user input instruction.
     * @param tasks the tasklist that stores all the tasks.
     * @return a task object representing the task to be Updated.
     * @throws DukeException when the instruction format is incorrect.
     */
    public static Task executeCommand(String instruction, TaskList tasks) throws DukeException {
        if (instruction.substring(6).strip().equals("")) {
            throw new DukeException("The description of a changing information cannot be empty.");
        }
        Integer indexDelete = Integer.valueOf(instruction.substring(7, 8).strip()) - 1;
        if (indexDelete + 1 > tasks.getSize()) {
            throw new DukeException("The index of the task to be deleted is out of range.");
        }

        Task tempDelete = tasks.get(indexDelete);

        String newDateTime = instruction.substring(9).strip();
        LocalDateTime deadlineDate = parseDateTime(newDateTime);
        if (tempDelete instanceof Deadline) {
            Deadline tempTask = (Deadline) tempDelete;
            tempTask.setBy(deadlineDate);
            tasks.set(indexDelete, tempTask);
        } else if (tempDelete instanceof Event) {
            Event tempTask = (Event) tempDelete;
            tempTask.setStartTime(deadlineDate);
            tasks.set(indexDelete, tempTask);
        } else {
            throw new DukeException("Not the correct type of task to change new date.");
        }

        return tempDelete;
    }
}
