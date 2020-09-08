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


    /** Command Guide for Users */
    public static String fullGuide = "Please follow the formats below:\n"
            + "\n1. 'help'" + "\n2. 'list'" + "\n3. 'done TASK_NUMBER'" + "\n4. 'delete TASK_NUMBER'"
            + "\n5. 'todo MY_TASK'" + "\n6. 'deadline MY_TASK /by DATE_OR_TIME'"
            + "\n7. 'event MY_TASK /at DATE_OR_TIME'" + "\n8. 'find KEYWORD'" + "\n9. 'archive'"
            + "\n10. 'listArchive'" + "\n11. 'loadArchive ARCHIVE_FILE_NAME'"
            + "\n12. 'binArchive ARCHIVE_FILE_NAME'" + "\n13. 'bye'" + "\n\nLegend: "
            + "\n1 -> Get command guide\n2 -> List all tasks in the current version"
            + "\n3 & 4 -> Modify the status of a task\n5 & 6 & 7 -> Add a new task"
            + "\n8 -> Search for task(s) matching a certain keyword\n9 -> Archive your current version of task list"
            + "\n10 -> List all existing archive files"
            + "\n11 -> Load a certain version of task list to your current work space"
            + "\n12 -> Delete a certain archive file\n13 -> Let Duke enter the sleep mode";


    /**
     * Returns String representation of DukeException object in form of List<String>.
     *
     */
    public List<String> toArrList() {
        List<String> output = new ArrayList<>();

        switch(et) {
        case NO_MEANING:
            output.add("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            output.add(fullGuide);
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
            output.add("☹ OOPS!!! The description of a deadline cannot be empty or incomplete.\n"
                    + "\nUse '/by undecided' if you want to decide later!");
            output.add("Please follow the format: \n'deadline MY_TASK /by DATE_OR_TIME'\n"
                    + "\n(Acceptable DATE/TIME format:\n'YYYY-MM-DD HHMM', 'YYYY-MM-DD', 'YYYY-MM-DD HH', 'YYYY-MM-DD H' or 'undecided')");
            break;
        case EVENT_INCOMPLETE:
            output.add("☹ OOPS!!! The description of an event cannot be empty or incomplete.\n"
                    + "\nUse '/at undecided' if you want to decide later!");
            output.add("Please follow the format: \n'event MY_TASK /at DATE_OR_TIME'\n"
                    + "\nAcceptable DATE/TIME format:\n 'YYYY-MM-DD HHMM', 'YYYY-MM-DD', 'YYYY-MM-DD HH', 'YYYY-MM-DD H' or 'undecided'");
            break;
        case READ_FILE:
            output.add("☹ OOPS!!! There seems to be some problem accessing the memory file!");
            break;
        case IMPROPER_DATETIME:
            output.add("☹ OOPS!!! We are unable to understand the DateTime provided.");
            output.add("Please use the formats 'YYYY-MM-DD HHMM', 'YYYY-MM-DD', 'YYYY-MM-DD HH', 'YYYY-MM-DD H' or 'undecided'!");
            break;
        default:
            output.add("☹ OOPS!!! Unspecified problem detected!");
            output.add(fullGuide);
            break;
        }

        return output;
    }

}