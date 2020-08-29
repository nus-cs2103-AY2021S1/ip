package command;

import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.FindException;


public class FindCommand extends Command {

    public FindCommand(String[] command) {
        super(command);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String toBeSearched = commands[1];
            String s = tasks.find(toBeSearched);
            return s;
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
