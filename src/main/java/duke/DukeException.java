package duke;

import java.util.ArrayList;
import java.util.List;

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


    /** Enum class of ExceptionType within DukeException class. */
    public enum ExceptionType {
        NO_MEANING,
        EMPTY_ILLEGAL,
        TODO_INCOMPLETE,
        FIND_INCOMPLETE,
        DEADLINE_INCOMPLETE,
        EVENT_INCOMPLETE,
        READ_FILE,
        IMPROPER_DATETIME
    }


    /**
     * Return String representation of DukeException object in form of List<String>.
     *
     */
    public List<String> toArrList() {
        List<String> output = new ArrayList<>();

        String full_guide = "Please follow the format below:\n"
                + "\n'list'" + "\n'done TASK_NUMBER'" + "\n'delete TASK_NUMBER'"
                + "\n'todo MY_TASK'" + "\n'deadline MY_TASK /by DATE_OR_TIME'"
                + "\n'event MY_TASK /at DATE_OR_TIME'"
                + "\n'find KEYWORD'"
                + "\n'bye'\n\nNOTE: "
                + "\nRecommended Form of DATE_OR_TIME: 'YYYY-MM-DD HHMM' or 'YYYY-MM-DD''";

        switch(et) {
        case NO_MEANING:
            output.add("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            output.add(full_guide);
            break;
        case EMPTY_ILLEGAL:
            output.add("☹ OOPS!!! Your number input is empty or invalid.");
            output.add("Please follow the format: \n'done TASK_NUMBER' or 'delete TASK_NUMBER'");
            break;
        case TODO_INCOMPLETE:
            output.add("☹ OOPS!!! The description of a todo cannot be empty.");
            output.add("Please follow the format: \n'todo MY_TASK'");
            break;
        case FIND_INCOMPLETE:
            output.add("☹ OOPS!!! The keyword of a search cannot be empty.");
            output.add("Please follow the format: 'find KEYWORD'");
            break;
        case DEADLINE_INCOMPLETE:
            output.add("☹ OOPS!!! The description of a deadline cannot be empty or incomplete.");
            output.add("Please follow the format: \n'deadline MY_TASK /by DATE_OR_TIME'\n"
                    + "\n(Recommended DATE/TIME format:\n'YYYY-MM-DD HHMM' or 'YYYY-MM-DD')");
            break;
        case EVENT_INCOMPLETE:
            output.add("☹ OOPS!!! The description of an event cannot be empty or incomplete.");
            output.add("Please follow the format: \n'event MY_TASK /at DATE_OR_TIME'\n"
                    + "\nRecommended DATE/TIME format: 'YYYY-MM-DD HHMM' or 'YYYY-MM-DD'");
            break;
        case READ_FILE:
            output.add("☹ OOPS!!! There seems to be some problem accessing the memory file!");
            break;
        case IMPROPER_DATETIME:
            output.add("☹ OOPS!!! We are unable to understand the Time provided.");
            output.add("However, we will still add this task. No worries!\n"
                    + "Next time, please consider the format 'YYYY-MM-DD HHMM' or 'YYYY-MM-DD'!");
            break;
        default:
            output.add("☹ OOPS!!! Unspecified problem detected!");
            output.add(full_guide);
            break;
        }

        return output;
    }

}