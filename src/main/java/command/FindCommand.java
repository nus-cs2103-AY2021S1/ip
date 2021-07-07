package command;

import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import exception.EmptyFindException;
import task.Task;
import ui.Ui;

/**
 * Represents a <code>Command</code> whose task is finding the tasks containing the keyword
 * and using the Ui to display the those tasks.
 * The <code>FindCommand</code> object contains an array of <code>String</code> which is an array
 * containing a command and the argument of the command (if any).
 */
public class FindCommand extends Command {

    public FindCommand(String[] splitCommand) {
        super(splitCommand);
    }

    /**
     * Finds the tasks containing the keyword and displays those tasks.
     *
     * @param tasks  Task list of the Duke.
     * @param ui Ui of the Duke.
     * @param storage Storage of the Duke.
     * @return The response text.
     * @throws DukeException If no keyword is provided in <code>splitCommand</code>.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String keyword = splitCommand[1];
            int index = 1;
            String toPrint = "";
            for (Task task : tasks.all()) {
                if (task.toString().contains(keyword)) {
                    toPrint += ui.say(index + ". " + task.toString() + "\n");
                    index++;
                }
            }
            return toPrint;
        } catch (IndexOutOfBoundsException e) { // No keyword
            throw new EmptyFindException();
        }
    }

    /**
     * Returns false to indicate not to exit the Duke.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof DeleteCommand) {
            DeleteCommand other = (DeleteCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}
