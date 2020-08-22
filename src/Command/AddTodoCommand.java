package Command;

import main.java.*;
import Exception.DukeException;
import Exception.TaskException;
import java.io.IOException;
import java.util.Arrays;

public class AddTodoCommand extends Command {
    public AddTodoCommand(String[] command) {
        super(command);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task temp = new Task (command[1]);
            tasks.addTask(temp);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException();
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
        } else if (o instanceof AddTodoCommand) {
            AddTodoCommand cur = (AddTodoCommand) o;
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
