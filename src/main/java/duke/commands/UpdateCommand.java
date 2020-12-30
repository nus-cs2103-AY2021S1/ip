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
        String[] tempUpdateSplit = instruction.substring(6).strip().split("/to");
        if (tempUpdateSplit.length < 2) {
            throw new DukeException("The updated date cannot be empty");
        }
        Integer indexUpdate = Integer.valueOf(tempUpdateSplit[0].strip()) - 1;
        if (indexUpdate > tasks.getSize() - 1 || indexUpdate < 0) {
            throw new DukeException("The index of the task to be updated is out of range.");
        }
        Task tempUpdate = tasks.get(indexUpdate);
        String newDateTime = tempUpdateSplit[1].strip();
        LocalDateTime deadlineDate = parseDateTime(newDateTime);
        if (tempUpdate instanceof Deadline) {
            Deadline tempTask = (Deadline) tempUpdate;
            tempTask.setBy(deadlineDate);
            tasks.set(indexUpdate, tempTask);
        } else if (tempUpdate instanceof Event) {
            Event tempTask = (Event) tempUpdate;
            tempTask.setStartTime(deadlineDate);
            tasks.set(indexUpdate, tempTask);
        } else {
            throw new DukeException("Not the correct type of task to change new date.");
        }
        return tempUpdate;
    }
}
