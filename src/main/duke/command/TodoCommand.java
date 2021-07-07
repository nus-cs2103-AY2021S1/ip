package duke.command;

import java.io.IOException;

import duke.exception.DukeExecutionException;
import duke.storage.Storage;
import duke.task.Todo;

/**
 * Represents an request by the user to add a Todo object to the list of Tasks.
 */
public class TodoCommand extends Command {

    private final Todo todo;

    /**
     * Constructor for a TodoCommand/
     *
     * @param name The name of the Todo object to be created
     */
    public TodoCommand(String name) {
        assert !name.isBlank() : "Input is empty.";
        this.todo = new Todo(name);
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
            return String.format("Added %s.", todo.toString());
        } catch (IOException e) {
            throw new DukeExecutionException("Could not execute command due to IO exception.");
        }
    }
}
