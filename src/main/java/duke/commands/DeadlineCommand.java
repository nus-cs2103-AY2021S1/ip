package duke.commands;

import duke.errors.DeadlineException;
import duke.errors.DukeException;
import duke.errors.EventException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import duke.tasks.Deadline;

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
        try {
            exceptionThrownIfNumberOrDescriptionAbsent();
            return Deadline.addDeadlineTask(tasks, ui, storage, userInput);
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
        if(isNumberOrDescriptionAbsent()) {
            throw new DeadlineException(true, false, false); //Since description is absent
        }
    }

}
