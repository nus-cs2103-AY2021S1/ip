package duke.commands;

import duke.errors.DukeException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;

/**
 * Handles case when exit is the keyword
 */
public class ExitCommand extends Command {
    /**
     * assigns string to a value of string
     * @param input assigns string to this this.string
     * @param lengthOfKeyword assigns this to this.lengthOfKeyword
     */
    public ExitCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * Gives the exit message
     *
     * @return String of exit message.
     */
    private String exitMessage() {
        return "  Bye. Hope to see you again soon!";
    }
    /**
     * Prints bye message
     *
     * @param tasks no change made
     * @param ui
     * @param storage no change made
     * @return String returns the string of the output that informs the exit action has been complete.
     * @throws DukeException not thrown here
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return exitMessage();
    }

    /**
     * Returns true to exit program
     *
     * @return true to exit program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
