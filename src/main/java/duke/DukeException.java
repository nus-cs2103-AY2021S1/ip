/**
 * A custom checked exception class to represent
 * exceptions specific to Duke
 */
package duke;

import duke.task.Task;

public class DukeException extends Exception {

    // message constants
    private static final String EMPTY_DESCRIPTION = "☹ OOPS!!! The description of " +
            "a task cannot be empty";
    private static final String EMPTY_TIME = "☹ OOPS!!! The description of " +
            "a task with attached time cannot be empty";

    private static final String ALREADY_DONE_FRONT = "The task \'";
    private static final String ALREADY_DONE_END = "\' has already been marked as done.";

    private static final String INVALID_NUMBER = "Please specify a valid item number";

    private static final String UNSPECIFIC_COMMAND = "This is a single word command. Please try again";

    private static final String UNKNOWN_OPERATION = "☹ OOPS!!! I'm sorry, " +
            "but I don't know what that means :-(";

    /**
     * Generic message DukeException constructor, maybe used
     * for future factory methods
     * @param message the message to be passed in Exception
     */
    private DukeException(String message) {
        super(message);
    }

    // factory methods for different exceptions related to Duke
    public static DukeException alreadyDone(Task t) {
        return new DukeException(ALREADY_DONE_FRONT+ t.getDesc() + ALREADY_DONE_END);
    }

    public static DukeException emptyDescription() {
        return new DukeException(EMPTY_DESCRIPTION);
    }

    public static DukeException emptyTimeDescription() {
        return new DukeException(EMPTY_TIME);
    }

    public static DukeException unknownOperation() {
        return new DukeException(UNKNOWN_OPERATION);
    }

    public static DukeException invalidNumber() {
        return new DukeException(INVALID_NUMBER);
    }

    public static DukeException unspecificCommand() {
        return new DukeException(UNSPECIFIC_COMMAND);
    }

}

