package duke.task;

import java.util.Optional;

import duke.exception.DukeException;

/**
 * This is a Todo Task
 * It keeps a description of a Task to be done.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo Task.
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the letter T for writing to hard disk file.
     */
    @Override
    public String getSaveSymbol() {
        return Task.TODO_SAVE_SYMBOL;
    }

    @Override
    public Optional<String> getFieldIdentifier() {
        return Optional.empty();
    }

    /**
     * Returns empty Optional to tell Storage to leave date segment blank.
     */
    @Override
    public Optional<String> getDate() {
        return Optional.empty();
    }

    @Override
    public Task duplicate() {
        Todo duplicateTodo = new Todo(description);
        if (this.isDone()) {
            duplicateTodo.markAsDone();
        }

        return duplicateTodo;
    }

    @Override
    public void setField(String fieldContent) throws DukeException {
        throw new DukeException("Todo should modify field content.");
    }

    @Override
    public String toString() {
        return "[" + Task.TODO_SAVE_SYMBOL + "]" + super.toString();
    }
}
