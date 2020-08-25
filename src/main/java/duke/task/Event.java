package duke.task;

import duke.exception.WrongFormatException;

/**
 * Encapsulates the event task type. An event task has a description of the task and the venue of where the task will
 * be done.
 */
public class Event extends Task {

    /** The venue where the task is to be done */
    protected String at;

    /**
     * Creates and initializes an event task that has not been completed by default.
     *
     * @param description The description of the task.
     * @param at The venue where the task is to be done.
     * @throws WrongFormatException If no description is provided.
     */
    public Event(String description, String at) throws WrongFormatException {
        super(description, "[E]", "event", false);
        this.at = at;
    }

    /**
     * Creates and initializes an event task that can be marked as completed.
     *
     * @param description The description of the task.
     * @param at The venue where the task is to be done.
     * @param isDone Marks whether the task has been completed or not.
     * @throws WrongFormatException If no description is provided.
     */
    public Event(String description, String at, boolean isDone) throws WrongFormatException {
        super(description, "[E]", "event", isDone);
        this.at = at;
    }

    /**
     * Formats the string that will be written in the save file to represent this particular event task.
     *
     * @return The string that will be written in the save file to represent this particular event task.
     */
    @Override
    public String stringToSaveInMemory() {
        return super.stringToSaveInMemory() + "|" + at;
    }

    /**
     * Formats the way the event task is presented to the user as part of the task list.
     *
     * @return The String that represents the event task when it is presented to the user as part of the task list.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}