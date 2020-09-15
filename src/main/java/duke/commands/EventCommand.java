package duke.commands;

import duke.errors.DukeException;
import duke.errors.EventException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import duke.tasks.Event;

/**
 * Handles cases when Event is keyword
 */
public class EventCommand extends AddCommand {
    /**
     * Assigns string to a value of string
     *
     * @param input assigns string to this this.string
     * @param lengthOfKeyword assigns this to this.lengthOfKeyword
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
            exceptionThrownIfNumberOrDescriptionAbsent();
            return Event.addEventTask(tasks, ui, storage, userInput);
            //Returns string if correct input and updates tasks and file in storage if correct input by user, else
            // throws exception
        } catch (DukeException dukeException) {
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }

    /**
     * Test whether description is absent and exception is thrown if absent
     *
     * @throws DukeException thrown if description for event is absent
     */
    protected void exceptionThrownIfNumberOrDescriptionAbsent() throws DukeException {
        if (isNumberOrDescriptionAbsent()) { //tests whether the description is absent
            throw new EventException(true, false, false, false, false);
        }
    }
}

