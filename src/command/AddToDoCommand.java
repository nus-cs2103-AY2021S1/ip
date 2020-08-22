package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.EmptyTodoException;
import task.Task;
import task.ToDo;

import java.io.IOException;
import java.util.Arrays;

public class AddToDoCommand extends Command {

    public AddToDoCommand(String[] splitCommand) {
        super(splitCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String description = splitCommand[1];
            Task toAdd = new ToDo(description);
            tasks.add(toAdd);
            ui.sayAddedTask(toAdd, tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            ui.sayException(e);
        } catch (IndexOutOfBoundsException e) { // No description
            throw new EmptyTodoException();
        }
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof AddToDoCommand) {
            AddToDoCommand other = (AddToDoCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}
