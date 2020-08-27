public class DukeException extends Exception {
    protected final String MESSAGE_ALL_TASKS_EMPTY_INPUT = "    I am sorry my Lord. "
            + "You have to give a command.\n";
    protected final String MESSAGE_ALL_TASKS_INVALID_COMMAND = "    I am sorry my Lord. "
            + "I do not recognise that command.\n";
    protected final String MESSAGE_ALL_TASKS_EMPTY_DESCRIPTION = "    I am sorry my Lord. "
            + "Your description cannot be empty.\n";
    protected final String MESSAGE_DEADLINE_NO_BY = "    I am sorry my Lord. "
            + "A deadline must have a specific end date/time.\n";
    protected final String MESSAGE_EVENT_NO_START_END = "    I am sorry my Lord. "
            + "An event must have a start and end time.\n";
    protected final String MESSAGE_INDEX_OUT_OF_BOUNDS = "    I am sorry my Lord. "
            + "That task cannot be found!";

    protected ExceptionType exceptionType;

    public DukeException(String err, ExceptionType exceptionType) {
        super(err);
        this.exceptionType = exceptionType;
    }

    @Override
    public String toString() {
        String errorMessage = "";
        switch (exceptionType) {
            case EMPTY_INPUT:
                errorMessage = MESSAGE_ALL_TASKS_EMPTY_INPUT;
                break;
            case INVALID_COMMAND:
                errorMessage = MESSAGE_ALL_TASKS_INVALID_COMMAND;
                break;
            case EMPTY_DESCRIPTION:
                errorMessage = MESSAGE_ALL_TASKS_EMPTY_DESCRIPTION;
                break;
            case DEADLINE_NO_BY:
                errorMessage = MESSAGE_DEADLINE_NO_BY;
                break;
            case EVENT_NO_START_END:
                errorMessage = MESSAGE_EVENT_NO_START_END;
                break;
            case INDEX_OUT_OF_BOUNDS:
                errorMessage = MESSAGE_INDEX_OUT_OF_BOUNDS;
                break;
        }
        return errorMessage;
    }
}
