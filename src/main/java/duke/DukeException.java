package duke;

import duke.task.Task;

/**
 * A custom checked exception class to represent
 * exceptions specific to Duke
 */
public class DukeException extends Exception {

    // message constants
    private static final String EMPTY_DESCRIPTION = "OOPS!!! The description of "
            + "a task cannot be empty";
    private static final String EMPTY_TIME = "OOPS!!! The description of "
            + "a task with attached time cannot be empty";

    private static final String ALREADY_DONE_FRONT = "The task \'";
    private static final String ALREADY_DONE_END = "\' has already been marked as done.";

    private static final String INVALID_NUMBER = "Please specify a valid item number";

    private static final String UNSPECIFIC_COMMAND = "This is a single word command. Please try again";
    private static final String MISSING_PARAMETERS = "This command requires more parameters. Please try again";

    private static final String UNKNOWN_OPERATION = "OOPS!!! I'm sorry, "
            + "but I don't know what that means :-(";

    /**
     * Generic message DukeException constructor, used for
     * factory methods that create different exceptions
     *
     * @param message the message to be passed in Exception
     */
    private DukeException(String message) {
        super(message);
    }

    // factory methods for different exceptions related to Duke

    /**
     * creates an exception stating
     * that the task is already complete
     *
     * @param t the task that is already complete
     * @return exception indicating the task is done
     */
    public static DukeException alreadyDone(Task t) {
        return new DukeException(ALREADY_DONE_FRONT + t.getDesc() + ALREADY_DONE_END);
    }

    /**
     * creates an exception stating that the
     * description of a task to be created is empty
     *
     * @return exception indicating the description of task is empty
     */
    public static DukeException emptyDescription() {
        return new DukeException(EMPTY_DESCRIPTION);
    }

    /**
     * creates an exception stating that the time description of a task
     * with time attached (event or deadline) that is to be created is empty
     *
     * @return exception indicating the time description is empty
     */
    public static DukeException emptyTimeDescription() {
        return new DukeException(EMPTY_TIME);
    }

    /**
     * creates an exception stating that the command given is not supported
     *
     * @return exception indicating command is unknown and not supported
     */
    public static DukeException unknownOperation() {
        return new DukeException(UNKNOWN_OPERATION);
    }

    /**
     * creates an exception stating that the item number passed
     * as a parameter to a command is invalid
     *
     * @return exception indicating the number is not suitable for use
     */
    public static DukeException invalidNumber() {
        return new DukeException(INVALID_NUMBER);
    }

    /**
     * creates an exception to state that the command
     * given does not require any additional arguments
     * as it is a single word command
     *
     * @return exception indicating command
     * is unspecific with unneeded additional details
     */
    public static DukeException unspecificCommand() {
        return new DukeException(UNSPECIFIC_COMMAND);
    }

    /**
     * creates an exception to state that the command
     * is given in an invalid manner as it requires
     * additional parameters
     *
     * @return exception indicating additional parameters are necessary
     * for the command in question
     */
    public static DukeException missingParameters() {
        return new DukeException(MISSING_PARAMETERS);
    }

}

