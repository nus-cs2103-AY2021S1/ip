package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

import java.util.Arrays;

/**
 * Represents a <code>Command</code> whose task is exiting the <code>Duke</code> object.
 * The <code>ExitCommand</code> object contains an array of <code>String</code> which is an array
 * containing a command and the argument of the command (if any).
 */
public class ExitCommand extends Command {

    public ExitCommand(String[] splitCommand) {
        super(splitCommand);
    }

    /**
     * Uses the <code>ui</code> to display exiting message.
     *
     * @param tasks  Task list of the Duke.
     * @param ui Ui of the Duke.
     * @param storage Storage of the Duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    /**
     * Returns true to indicate to exit the Duke.
     *
     * @return true.
     */
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ExitCommand) {
            ExitCommand other = (ExitCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}
