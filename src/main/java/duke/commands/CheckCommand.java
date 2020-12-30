package duke.commands;

import static duke.parser.Parser.parseDate;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;


/**
 * A class that represents Check command.
 */
public class CheckCommand {

    /**
     * Executes the Check command from user input.
     *
     * @param instruction the string of user input instruction.
     * @param tasks the tasklist that stores all the tasks.
     * @return a task list representing the tasks found.
     * @throws DukeException when the instruction format is incorrect.
     */
    public static TaskList executeCommand(String instruction, TaskList tasks) throws DukeException {
        if (instruction.substring(5).strip().equals("")) {
            throw new DukeException("The \"check\" command is not entered correctly");
        }
        String dateTimeToCheck = instruction.substring(5).strip();
        LocalDate dateToCheck = parseDate(dateTimeToCheck);
        TaskList occurings = searchTasksByTime(dateToCheck, tasks);

        return occurings;
    }

    /**
     * Returns a TaskList of tasks that meet the input date requirement.
     *
     * @param localDate the date from input that of tasks.
     * @param tasks the TaskList of all tasks.
     * @return the TaskList of tasks that meet the input date requirement.
     */
    public static TaskList searchTasksByTime(LocalDate localDate, TaskList tasks) {
        TaskList occurrings = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            boolean isMatch = false;
            Task temp = tasks.get(i);
            if (temp instanceof Deadline) {
                Deadline deadline = (Deadline) temp;
                if (deadline.getDate().equals(localDate)) {
                    isMatch = true;
                }
            }
            if (temp instanceof Event) {
                Event deadline = (Event) temp;
                if (deadline.getDate().equals(localDate)) {
                    isMatch = true;
                }
            }
            if (isMatch) {
                occurrings.add(temp);
            }
        }

        return occurrings;
    }
}
