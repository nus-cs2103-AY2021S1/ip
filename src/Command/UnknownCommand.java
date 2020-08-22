package Command;

import main.java.*;
import Exception.DukeException;
import Exception.CommandException;

import java.util.Arrays;

public class UnknownCommand extends Command {
    public UnknownCommand(String[] command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
            if(Arrays.equals(this.command, cur.command)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
