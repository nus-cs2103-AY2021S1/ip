/**
 * A custom checked exception class to represent
 * exceptions specific to Duke
 */
public class DukeException extends Exception {
    // message constants
    private static final String UNKNOWN_OPERATION = "☹ OOPS!!! I'm sorry, " +
            "but I don't know what that means :-(";
    private static final String EMPTY_DESCRIPTION_FRONT = "☹ OOPS!!! The description " +
            "(and / or time) of a ";
    private static final String EMPTY_DESCRIPTION_END = " cannot be empty.";

    /**
     * Generic message DukeException constructor, maybe used
     * for future factory methods
     * @param message the message to be passed in Exception
     */
    public DukeException(String message) {
        super(message);
    }

    // factory methods for unknown op and empty descriptions
    static DukeException unknownOperation() {
        return new DukeException(UNKNOWN_OPERATION);
    }
    static DukeException emptyDescription(String taskType) {
        return new DukeException(EMPTY_DESCRIPTION_FRONT
                + taskType + EMPTY_DESCRIPTION_END);
    }

}
