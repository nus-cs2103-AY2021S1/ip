package duke.commands;

import duke.errors.DeadlineException;
import duke.errors.DukeException;
import duke.errors.EventException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import duke.tasks.Event;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles cases when Event is keyword
 */
public class EventCommand extends AddCommand {
    /**
     * Assigns string to a value of string
     *
     * @param input assigns string to this this.string
     */
    public EventCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * Adds Event task or handle exceptions
     *
     * @param tasks to change the taskList if necessary when no error
     * @param ui to store the DukeException that may be thrown if there is an error in user input
     * @param storage to change the file in the if necessary when no error
     * @return String returns the string of the output that informs the action has been complete.
     * @throws DukeException if there no description after Event no time or time is wrong format
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (isNumberOrDescriptionAbsent()) {
                throw new EventException(true, false, false, false, false);
            }
            return Event.addEventTask(tasks, ui, storage, userInput);
            //Returns string if correct input and updates tasks and file in storage if correct input by user, else
            // throws exception
        } catch (DukeException dukeException) {
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }

}

