package duke.exception;

/**
 * Throws when a command that create task is input but missing some supporting info
 */
public class InadequateCommandException extends DukeException {
    private String typeOfTask;
    private String[] missingSections;

    /**
     * Creates an <code>InadequateCommandException</code> object.
     * @param typeOfTask The type of the task intended to create (<code>Todo</code>, <code>Deadline</code>,
     *                   or <code>Event</code>)
     * @param missingSections The missing information sections in the input
     */
    public InadequateCommandException(String typeOfTask, String[] missingSections) {
        super("");
        this.typeOfTask = typeOfTask;
        this.missingSections = missingSections;
    }

    /**
     * Returns the message of the exception
     * @return The message describing the missing sections.
     */
    @Override
    public String getMessage() {
        String listing = "";
        for (int i = 0; i < missingSections.length - 1; i++) {
            listing += missingSections[i] + ", ";
        }
        listing += missingSections[missingSections.length - 1];
        return "OOPS, the " + listing + " of a " + typeOfTask + " cannot be empty";
    }
}