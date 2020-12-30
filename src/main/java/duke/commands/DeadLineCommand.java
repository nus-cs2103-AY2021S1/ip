package duke.commands;

import static duke.parser.Parser.parseDateTime;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.TaskList;





/**
 * A class that represents DeadLine command.
 */
public class DeadLineCommand {

    /**
     * Executes the DeadLine command from user input.
     *
     * @param instruction the string of user input instruction.
     * @param tasks the tasklist that stores all the tasks.
     * @throws DukeException when the instruction format is incorrect.
     */
    public static void executeCommand(String instruction, TaskList tasks) throws DukeException {
        if (instruction.substring(8).strip().equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String[] tempDeadline = instruction.substring(8).strip().split("/by");
        if (tempDeadline.length < 2) {
            throw new DukeException("The date and time of the deadline cannot be empty.");
        }

        String deadlineDescription = tempDeadline[0].strip();
        String deadlineDateTime = tempDeadline[1].strip();
        LocalDateTime deadlineDate = parseDateTime(deadlineDateTime);
        tasks.add(new Deadline(deadlineDescription, deadlineDate));
    }
}
