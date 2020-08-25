package Duke.Commands;
import Duke.Errors.DukeException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

/**
 * handles case when exit is the keyword
 */
public class ExitCommand extends Command {
    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public ExitCommand(String string) {
        super(string);
    }

    /**
     * prints bye message
     * @param tasks no change made
     * @param ui
     * @param storage no change made
     * @throws DukeException not thrown
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("  Bye. Hope to see you again soon!");
    }

    /**
     * returns true to exit program
     * @return true to exit program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
