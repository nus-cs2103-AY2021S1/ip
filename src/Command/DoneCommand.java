package Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;


import Exception.DukeException;
import Exception.DoneOutOfBoundException;
import Exception.DoneUnknownException;

import java.io.IOException;
import java.util.Arrays;

public class DoneCommand extends Command {

    public DoneCommand(String[] command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Integer toBeChanged = Integer.valueOf(command[1]);
            tasks.changeIsDone(toBeChanged);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
            }
        } catch (DoneOutOfBoundException e) {
            throw e;
        } catch (NumberFormatException e) {
            throw new DoneUnknownException();
        } catch (IndexOutOfBoundsException e) {
            throw new DoneUnknownException();
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
            DoneCommand cur = (DoneCommand) o;
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
