package Command;

import Exception.DukeException;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

import java.util.Arrays;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.goodbyeMessage();
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
            if (Arrays.equals(this.command, cur.command)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
