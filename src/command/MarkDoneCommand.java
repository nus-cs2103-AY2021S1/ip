package command;

import exception.DukeException;
import exception.EmptyDoneException;
import exception.NoSuchTaskException;
import main.java.*;
import task.Task;

import java.io.IOException;

public class MarkDoneCommand extends Command {

    public MarkDoneCommand(String[] splitCommand) {
        super(splitCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String oneBasedIndex = splitCommand[1];
            int zeroBasedIndex = Integer.parseInt(oneBasedIndex) - 1;

            Task toMark = tasks.get(zeroBasedIndex);
            toMark.markAsDone();
            ui.sayMarkedTask(toMark);
            storage.save(tasks);
        } catch (IOException e) {
            ui.sayException(e);
        } catch (IndexOutOfBoundsException e) { // No description
            throw new EmptyDoneException();
        } catch (NoSuchTaskException e) {
            ui.sayException(e);
        }
    }

    public boolean isExit() {
        return false;
    }
}
