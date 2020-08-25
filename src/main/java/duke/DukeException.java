package duke;

public class DukeException extends Exception {
    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static final String BAD_COMMAND_ERROR = "I have literally no idea what you're asking of me. \uD83D\uDE20";
    private static final String BAD_TODO_ERROR = "HELLO! Please fill up a description of todo. " +
            "Can't be empty!!! \uD83D\uDE20";
    private static final String BAD_DEADLINE_DATE_ERROR = "HELLO! Please fill up due date for the deadline. " +
            "Can't be empty!!! \uD83D\uDE20";
    private static final String BAD_DEADLINE_TASK_ERROR = "HELLO! Please fill up the task for the deadline. " +
            "Can't be empty!!! \uD83D\uDE20";
    private static final String BAD_EVENT_DATE_ERROR = "HELLO! Please fill up the date for the event. " +
            "Can't be empty!!! \uD83D\uDE20";
    private static final String BAD_EVENT_TASK_ERROR = "HELLO! Please fill up the event details. " +
            "Can't be empty!!! \uD83D\uDE20";

    public DukeException(String message) {
        super(message);
    }

    public static DukeException badCommand() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_COMMAND_ERROR + "\n" + HORIZONTAL_RULE);
    }

    public static DukeException badToDo() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_TODO_ERROR + "\n" + HORIZONTAL_RULE);
    }

    public static DukeException badDeadlineTask() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_DEADLINE_TASK_ERROR + "\n" + HORIZONTAL_RULE);
    }

    public static DukeException badDeadlineDate() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_DEADLINE_DATE_ERROR + "\n" + HORIZONTAL_RULE);
    }

    public static DukeException badEventTask() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_EVENT_TASK_ERROR + "\n" + HORIZONTAL_RULE);
    }

    public static DukeException badEventDate() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_EVENT_DATE_ERROR + "\n" + HORIZONTAL_RULE);
    }

}
