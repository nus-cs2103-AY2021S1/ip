package command;

import exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.UnknownCommandException;

import java.util.Arrays;

public class UnknownCommand extends Command {
    public UnknownCommand(String[] splitCommand) {
        super(splitCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new UnknownCommandException();
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof UnknownCommand) {
            UnknownCommand other = (UnknownCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}
