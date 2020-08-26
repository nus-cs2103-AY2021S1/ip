package main.java.duke;

public class DukeException extends Throwable {


    /** ExceptionType object of DukeException */
    protected ExceptionType et;


    /**
     * Constructor of DukeException.
     * Initialize ExceptionType object of DukeException object.
     *
     * @param et  ExceptionType passsed in to initialize class variable.
     */
    public DukeException(ExceptionType et) {
        this.et = et;
    }


    /** Enum class of ExceptionType within DukeException class */
    public enum ExceptionType {
        NO_MEANING,
        EMPTY_ILLEGAL,
        TODO_INCOMPLETE,
        DEADLINE_INCOMPLETE,
        EVENT_INCOMPLETE,
        READ_FILE,
        IMPROPER_DATETIME
    }


    /**
     * Return String representation of DukeException object.
     *
     */
    @Override
    public String toString() {
        String output = "";

        String full_guide = "\n     Please follow the format:" +
                "\n     'list'" + "\n     'done TASK_NUMBER' or 'delete TASK_NUMBER'" +
                "\n     'todo MY_TASK'" + "\n     'deadline MY_TASK /by DATE_OR_TIME'" +
                "\n     'event MY_TASK /at DATE_OR_TIME'" + "\n     'bye' + '\n     NOTE: " +
                "\n     Recommended Form of DATE_OR_TIME: 'YYYY-MM-DD HHMM' or 'YYYY-MM-DD''";

        switch(et) {
        case NO_MEANING:
            output = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + full_guide;
            break;
        case EMPTY_ILLEGAL:
            output = "     ☹ OOPS!!! Your number input is empty or invalid." +
                    "\n     Please follow the format: 'done TASK_NUMBER' or 'delete TASK_NUMBER'";
            break;
        case TODO_INCOMPLETE:
            output = "     ☹ OOPS!!! The description of a todo cannot be empty." +
                    "\n     Please follow the format: 'todo MY_TASK'";
            break;
        case DEADLINE_INCOMPLETE:
            output = "     ☹ OOPS!!! The description of a deadline cannot be empty or incomplete." +
                    "\n     Please follow the format: 'deadline MY_TASK /by DATE_OR_TIME'" +
                    "\n     (Recommended DATE/TIME format: 'YYYY-MM-DD HHMM' or 'YYYY-MM-DD')";
            break;
        case EVENT_INCOMPLETE:
            output = "     ☹ OOPS!!! The description of an event cannot be empty or incomplete." +
                    "\n     Please follow the format: 'event MY_TASK /at DATE_OR_TIME'" +
                    "\n     (Recommended DATE/TIME format: 'YYYY-MM-DD HHMM' or 'YYYY-MM-DD')";
            break;
        case READ_FILE:
            output = "     ☹ OOPS!!! There seems to be some problem accessing the memory file!";
            break;
        case IMPROPER_DATETIME:
            output = SpecialFormat.STARTING_LINE +
                    "☹ OOPS!!! We are unable to understand the Time provided." +
                    "\n     However, we will still add this task. No worries!" +
                    "\n     Next time, you can consider the format 'YYYY-MM-DD HHMM' or 'YYYY-MM-DD'!";
            break;
        default:
            output = "     ☹ OOPS!!! Unspecified problem detected!" + full_guide;
            break;
        }
        return output;
    }

}