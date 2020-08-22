package Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

import Exception.DukeException;
import Exception.DeleteOutOfBoundException;
import Exception.DeleteUnknownException;
import java.io.IOException;
import java.util.Arrays;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Integer toBeDeleted= Integer.valueOf(command[1]);
            tasks.delete(toBeDeleted);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
            }
        } catch (DeleteOutOfBoundException e) {
            throw e;
        } catch (NumberFormatException e) {
            throw new DeleteUnknownException();
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteUnknownException();
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
        } else if (o instanceof DeleteCommand) {
            DeleteCommand cur = (DeleteCommand) o;
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
