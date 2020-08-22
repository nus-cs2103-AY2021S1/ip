package Command;

import main.java.*;
import Exception.DukeException;
import Exception.FindException;

import java.util.Arrays;


public class FindCommand extends Command {

    public FindCommand(String[] command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String toBeSearched = command[1];
            tasks.find(toBeSearched);
        } catch (IndexOutOfBoundsException e) {
            throw new FindException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof DoneCommand) {
            FindCommand cur = (FindCommand) o;
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
