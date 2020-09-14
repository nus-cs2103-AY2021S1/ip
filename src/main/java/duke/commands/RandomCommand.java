package duke.commands;

import duke.errors.WrongInputException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;

/**
 * handles case where a random word is being input
 */
public class RandomCommand extends Command {
    /**
     * Assigns string to a value of string
     *
     * @param input assigns string to this this.string
     */
    public RandomCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * Gives wrong input exception
     *
     * @param tasks
     * @param ui to store the DukeException that is thrown since there is an error in user input
     * @param storage
     * @return String returns the string of the output that informs the action has been complete.
     * @throws WrongInputException is thrown
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WrongInputException {
        ui.setDukeException(new WrongInputException());
        throw new WrongInputException();
    }
}

