package duke.task;

import java.util.Optional;

import duke.exception.DukeException;

/**
 * This class is a representation of a Task object.
 */
public abstract class Task {

    public static final String CROSS_SYMBOL = "x";
    public static final String EMPTY_SYMBOL = " ";
    public static final String TODO_SAVE_SYMBOL = "T";
    public static final String DEADLINE_SAVE_SYMBOL = "D";
    public static final String EVENT_SAVE_SYMBOL = "E";
    public static final String DEADLINE_FIELD_IDENTIFIER = "/by";
    public static final String EVENT_FIELD_IDENTIFIER = "/at";
    public static final String IS_DONE = "1";
    public static final String NOT_DONE = "0";

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * @param description Description of the given Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status of a Task: done or not done.
     * Returns a symbol: a 'x' for a completed Task, blank space for an incomplete Task.
     */
    public String getStatusIcon() {
        return (isDone ? CROSS_SYMBOL : EMPTY_SYMBOL); // tick: "\u2713" & cross: "\u2718" not used by windows
    }

    /**
     * Changes the status of a Task from !isDone to isDone.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the Task isDone.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns 1 if Task isDone, 0 otherwise.
     */
    public String isDoneToString() {
        return isDone ? IS_DONE : NOT_DONE;
    }

    /**
     * Method for returning the symbol used in the save file for a Task.
     * 'E' for Event, 'D' for Deadline etc.
     */
    public abstract String getSaveSymbol();

    /**
     * Returns field identifier of a Task if any.
     * "/by" for Deadline
     * "/at" for Event
     */
    public abstract Optional<String> getFieldIdentifier();

    /**
     * Returns a stored date/time in the form of an Optional.
     * Todo does not have any date associated, so this returns an empty Optional.
     * Deadline and Event tasks will have an associated date/time.
     */
    public abstract Optional<String> getDate();

    /**
     * Returns true if description contains the given keywords.
     * @param keyWords Keywords of user search.
     */
    public boolean contains(String keyWords) {
        return description.contains(keyWords);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract Task duplicate();

    public abstract void setField(String fieldContent) throws DukeException;
}
