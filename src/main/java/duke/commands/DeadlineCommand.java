package duke.commands;

import duke.errors.DeadlineException;
import duke.errors.DukeException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import duke.tasks.Deadline;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles when input is deadline
 */
public class DeadlineCommand extends AddCommand {
    /**
     * assigns string to a value of string and initialize Deadline Command
     *
     * @param input assigns string to this this.string
     */
    public DeadlineCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * to add deadline into a task list in TaskList
     *
     * @param tasks to change the taskList if necessary
     * @param ui to store the DukeException that may be thrown if there is an error in user input
     * @param storage to change the file in the if necessary
     * @return String returns the string of the output that informs the action is successful
     * @throws DukeException whenever there is an error, where the time adn or date is absent or in wrong format, no
     * description
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isNumberOrDescriptionAbsent()) {
            ui.setDukeException(new DeadlineException(true, false, false));
            throw new DeadlineException(true, false, false); //Since description is absent
        }
        try {
            return Deadline.addDeadlineTask(tasks, ui, storage, userInput);
        } catch (DukeException dukeException) {
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }


}
