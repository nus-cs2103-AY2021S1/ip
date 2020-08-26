package duke.command;

import duke.exception.DukeExecutionException;
import duke.storage.Storage;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;

public class TodoCommand extends Command {

    Todo todo;

    public TodoCommand(String name) {
        this.todo = new Todo(name);
    }

    @Override
    public boolean shouldExit() {
        return super.shouldExit();
    }

    @Override
    public void execute(Storage storage) throws DukeExecutionException {
        try {
            storage.add(todo);
            Ui.showTaskAddition(todo);
        } catch (IOException e) {
            throw new DukeExecutionException("Could not execute command due to IO exception.");
        }
    }
}
