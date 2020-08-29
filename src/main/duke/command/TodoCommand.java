package duke.command;

import java.io.IOException;

import duke.exception.DukeExecutionException;
import duke.storage.Storage;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents an request by the user to add a Todo object to the list of Tasks.
 */
public class TodoCommand extends Command {

    private Todo todo;

    /**
     * Constructor for a TodoCommand/
     *
     * @param name The name of the Todo object to be created
     */
    public TodoCommand(String name) {
        this.todo = new Todo(name);
    }

    @Override
    public boolean shouldExit() {
        return super.shouldExit();
    }

    /**
     * Attempts to add the Todo object to the storage.
     *
     * @param storage The Storage object to take in the new Todo object
     * @throws DukeExecutionException if an IOException occurs
     */
    @Override
    public String execute(Storage storage) throws DukeExecutionException {
        try {
            storage.add(todo);
            return todo.toString();
        } catch (IOException e) {
            throw new DukeExecutionException("Could not execute command due to IO exception.");
        }
    }
}
