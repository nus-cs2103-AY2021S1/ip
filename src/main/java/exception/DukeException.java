package exception;

/**
 * Throws custom exception commands if something goes wrong when processing user input.
 */
public class DukeException {
    /**
     * Line for proper formatting of error messages in DukeException methods.
     */
    private static final String DIVIDER = "\t____________________________________________________________\n";

    /**
     * Renders an error message that user input has been written incorrectly.
     */
    public static String printInvalidCommand() {
        System.out.println(DIVIDER + "\tCommand written incorrectly. Please check again.\n" + DIVIDER);
        return "Command written incorrectly. Please check again.\n";
    }

    /**
     * Renders an error message that user did not specify the type of task (ie Deadline, To Do, Event).
     */
    public static String printGenericTask() {
        System.out.println(DIVIDER + "\tPlease specify either a Deadline, To Do, or Event!\n" + DIVIDER);
        return "Please specify either a Deadline, To Do, or Event!\n";
    }

    /**
     * Renders an error message that there is no such task in the list of tasks.
     */
    public static String printNoSuchTask() {
        System.out.println(DIVIDER + "\tNo such task in list of tasks.\n" + DIVIDER);
        return "No such task in list of tasks.\n";
    }

    /**
     * Renders an error message that user input format for deadline is invalid.
     */
    public static String printInvalidDeadline() {
        System.out.println(DIVIDER + "\tDeadline format is invalid\n" + DIVIDER);
        return "Deadline format is invalid\n";
    }

    /**
     * Renders an error message that user input format for event is invalid.
     */
    public static String printInvalidEvent() {
        System.out.println(DIVIDER + "\tEvent format is invalid\n" + DIVIDER);
        return "Event format is invalid\n";
    }

    /**
     * Renders an error message that user input format for todo is invalid.
     */
    public static String printInvalidTodo() {
        System.out.println(DIVIDER + "\tTodo format is invalid\n" + DIVIDER);
        return "Todo format is invalid\n";
    }

    /**
     * Renders an error message that the application has issues creating a folder for duke.txt.
     */
    public static String printErrorCreatingFolder() {
        System.out.println("Something went wrong while creating src\\main\\java\\data folder \n");
        return "Something went wrong while creating src\\main\\java\\data folder \n";
    }

    /**
     * Renders an error message that the application has issues appending to duke.txt.
     */
    public static String printErrorAppendingToFile() {
        System.out.println("Something went wrong while writing to file \n");
        return "Something went wrong while writing to file \n";
    }

    /**
     * Renders an error message that the application has issues amending duke.txt.
     */
    public static String printErrorAmendingFile() {
        System.out.println("Something went wrong while amending text to file \n");
        return "Something went wrong while amending text to file \n";
    }

    /**
     * Renders an error message that the application has issues deleting text from duke.txt.
     */
    public static String printErrorDeletingTextFromFile() {
        System.out.println("Something went wrong while deleting text from file \n");
        return "Something went wrong while deleting text from file \n";
    }

    /**
     * Renders an error message that the application has issues loading task data from duke.txt into task list.
     */
    public static String printErrorLoadingTextIntoTaskList() {
        System.out.println("<<IGNORE THIS ERROR IF IT IS YOUR FIRST TIME USING THE APPLICATION "
                + "AND HAVE NOT ADD ANYTHING INTO TASK LIST!>>");
        System.out.println("<<ERROR IS THROWN BECAUSE data\\duke.txt\\ HAS NOT BEEN CREATED YET ON FIRST RUN>>");
        System.out.println("Something went wrong while loading duke.txt into task list \n");
        return "<<IGNORE THIS ERROR IF IT IS YOUR FIRST TIME USING THE APPLICATION "
                + "AND HAVE NOT ADD ANYTHING INTO TASK LIST!>>"
                + "<<ERROR IS THROWN BECAUSE data\\duke.txt\\ HAS NOT BEEN CREATED YET ON FIRST RUN>>"
                + "Something went wrong while loading duke.txt into task list \n";
    }
}
