package exception;

/**
 * Throws custom exception commands if something goes wrong when processing user input.
 */
public class DukeException {
    /**
     * Line for proper formatting of error messages in DukeException methods.
     */
    private final static String line = "\t____________________________________________________________\n";

    /**
     * Renders an error message that user input has been written incorrectly.
     */
    public static void invalidCommand() {
        System.out.println(line + "\tCommand written incorrectly. Please check again.\n" + line);
    }

    /**
     * Renders an error message that user did not specify the type of task (ie Deadline, To Do, Event).
     */
    public static void genericTask() {
        System.out.println(line + "\tPlease specify either a Deadline, To Do, or Event!\n" + line);
    }

    /**
     * Renders an error message that there is no such task in the list of tasks.
     */
    public static void noSuchTask() {
        System.out.println(line + "\tNo such task in list of tasks.\n" + line);
    }

    /**
     * Renders an error message that user input format for deadline is invalid.
     */
    public static void invalidDeadline() {
        System.out.println(line + "\tDeadline format is invalid\n" + line);
    }

    /**
     * Renders an error message that user input format for event is invalid.
     */
    public static void invalidEvent() {
        System.out.println(line + "\tEvent format is invalid\n" + line);
    }

    /**
     * Renders an error message that user input format for todo is invalid.
     */
    public static void invalidTodo() {
        System.out.println(line + "\tTodo format is invalid\n" + line);
    }

    /**
     * Renders an error message that the application has issues creating a folder for duke.txt.
     */
    public static void errorCreatingFolder() {
        System.out.println("Something went wrong while creating src\\main\\java\\data folder \n");
    }

    /**
     * Renders an error message that the application has issues appending to duke.txt.
     */
    public static void errorAppendingToFile() {
        System.out.println("Something went wrong while writing to file \n");
    }

    /**
     * Renders an error message that the application has issues amending duke.txt.
     */
    public static void errorAmendingFile() {
        System.out.println("Something went wrong while amending text to file \n");
    }

    /**
     * Renders an error message that the application has issues deleting text from duke.txt.
     */
    public static void errorDeletingTextFromFile() {
        System.out.println("Something went wrong while deleting text from file \n");
    }

    /**
     * Renders an error message that the application has issues loading task data from duke.txt into task list.
     */
    public static void errorLoadingTextIntoTaskList() {
        System.out.println("<<IGNORE THIS ERROR IF IT IS YOUR FIRST TIME USING THE APPLICATION AND HAVE NOT ADD ANYTHING INTO TASK LIST!>>");
        System.out.println("<<ERROR IS THROWN BECAUSE data\\duke.txt\\ HAS NOT BEEN CREATED YET ON FIRST RUN>>");
        System.out.println("Something went wrong while loading duke.txt into task list \n");
    }
}
