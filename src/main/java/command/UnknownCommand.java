package command;


import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.CommandException;
import exception.DukeException;

/**
 * Represents all command that is not known in the Command list.
 */
public class UnknownCommand extends Command {
    public UnknownCommand(String[] command) {
        super(command);
    }

    /**
     * Print that the command is not known.
     * @param tasks the list of task saved.
     * @param ui deals with interaction with the user.
     * @param storage deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException will always happen as the command is not known.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new CommandException();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof UnknownCommand) {
            UnknownCommand cur = (UnknownCommand) o;
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
