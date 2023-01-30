package command;

import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;


/**
 * Represents a command to exit the loop.
 */
public class ExitCommand extends Command {
    public ExitCommand(String[] command) {
        super(command);
    }

    /**
     * Exit the program.
     * @param tasks the list of task saved.
     * @param ui deals with interaction with the user.
     * @param storage deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.goodbyeMessage();
    }

    /**
     * Indicates to exit the loop.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ExitCommand) {
            ExitCommand cur = (ExitCommand) o;
            if (Arrays.equals(this.commands, cur.commands)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
