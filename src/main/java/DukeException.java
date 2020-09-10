public class DukeException extends Exception {

    protected DukeExceptionType exceptionType;
    protected Commands command;

    /**
     * constructor for a DukeException object
     * @param e error name
     * @param exceptionType type of error
     */
    public DukeException(String e, DukeExceptionType exceptionType){
        super(e);
        this.exceptionType = exceptionType;
    }

    /**
     * constructor for a DukeException object
     * @param e error name
     * @param exceptionType type of error
     * @param command the command which results in the error
     */
    public DukeException(String e, DukeExceptionType exceptionType, Commands command) {
        super(e);
        this.exceptionType = exceptionType;
        this.command = command;
    }

    @Override
    public String toString() {
        String error = "";
        switch (exceptionType){
            case EMPTY_TIME:
                switch (command){
                    case DEADLINE:
                        error+= "Deadline task requires a time";
                        break;
                    case EVENT:
                        error+= "Event task requires a time";
                        break;
                }
                break;
            case INVALID_TASK:
                error+= "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                break;
            case NO_DESCRIPTION:
                switch (command) {
                    case DEADLINE:
                        error+= "Deadline task description cannot be empty";
                        break;
                    case TODO:
                        error+= "Todo task description cannot be empty";
                        break;
                    case EVENT:
                        error+= "Event task description cannot be empty";
                        break;
                    case DONE:
                        error+= "Done task description cannot be empty";
                        break;
                }
                break;
            case WRONG_DESCRIPTION:
                switch (command) {
                    case DEADLINE:
                        error+= "Deadline command must be followed by '/by' and then a deadline";
                        break;
                    case EVENT:
                        error+= "Event command must be followed by '/at' and then a duration";
                        break;
                    case DONE:
                        error+= "Such a task does not exist, please only follow done by the serial number of the task to be marked done";
                        break;
                    case DELETE:
                        error+= "Such a task does not exist, please only follow delete by the serial number of the task to be marked deleted";
                        break;
                }
                break;
        }
        return error;
    }
}
