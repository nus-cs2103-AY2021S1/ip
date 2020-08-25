package duke;

/**
 * This enum represents the different types of inputs that can be provided by the user.
 */
public enum InputType {
    TERMINATION,
    LIST,
    COMPLETE_TASK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE;

    /**
     * Gives an InputType corresponding to the input string
     * @param s String which corresponds to a particular InputType
     * @return InputType representing the string
     */
    public static InputType getCommand(String s) {
        switch (s) {
        case "T":
            return TODO;
        case "D":
            return DEADLINE;
        case "E":
            return EVENT;
        case "DEL":
            return DELETE;
        case "DONE":
            return COMPLETE_TASK;
        case "LIST":
            return LIST;
        case "BYE":
            return TERMINATION;
        default:
            throw new IllegalArgumentException("Bad argument for getCommand");
        }
    }
}
