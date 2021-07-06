package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.DukeException;

/**
 * Represents tasks that need to be done but without any date or time attached to it.
 */
public class Todo extends Task {

    /**
     * Creates a new todo with the specified description.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns false since todos do not have dates associated to them.
     *
     * @return False, since todos do not have dates associated to them.
     */
    @Override
    public boolean hasDate() {
        return false;
    }


    /**
     * Returns false since todos do not have a time associated to them.
     *
     * @return False, since todos do not have a time associated to them.
     */
    @Override
    public boolean hasTime() {
        return false;
    }

    /**
     * Throws an exception since todos do not have dates associated to them.
     *
     * @throws DukeException Since todos do not have dates associated to them.
     */
    @Override
    public LocalDate getDate() throws DukeException {
        throw new DukeException("Todo tasks do not have a date associated to them.");
    }

    /**
     * Throws an exception since todos do not have times associated to them.
     *
     * @throws DukeException Since todos do not have times associated to them.
     */
    @Override
    public LocalTime getTime() throws DukeException {
        throw new DukeException("Todo tasks do not have a time associated to them.");
    }

    /**
     * Returns a string representation of this todo.
     *
     * @return String representation of this todo.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
