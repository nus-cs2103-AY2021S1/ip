package exception;

/**
 * Throws custom exception commands if something goes wrong when processing user input.
 */
public class DukeException {
    /**
     * Line for proper formatting of error messages in DukeException methods.
     */
    private static final String line = "\t____________________________________________________________\n";
    private static final String javaFxLine = "\t_______________________\n";

    /**
     * Renders an error message that user input has been written incorrectly.
     */
    public static String invalidCommand() {
        System.out.println(line + "\tCommand written incorrectly. Please check again.\n" + line);
        return javaFxLine + "\tCommand written incorrectly. Please check again.\n" + javaFxLine;
    }

    /**
     * Renders an error message that user did not specify the type of task (ie Deadline, To Do, Event).
     */
    public static String genericTask() {
        System.out.println(line + "\tPlease specify either a Deadline, To Do, or Event!\n" + line);
        return javaFxLine + "\tPlease specify either a Deadline, To Do, or Event!\n" + javaFxLine;
    }

    /**
     * Renders an error message that there is no such task in the list of tasks.
     */
    public static String noSuchTask() {
        System.out.println(line + "\tNo such task in list of tasks.\n" + line);
        return javaFxLine + "\tNo such task in list of tasks.\n" + javaFxLine;
    }

    /**
     * Renders an error message that user input format for deadline is invalid.
     */
    public static String invalidDeadline() {
        System.out.println(line + "\tDeadline format is invalid\n" + line);
        return javaFxLine + "\tDeadline format is invalid\n" + javaFxLine;
    }

    /**
     * Renders an error message that user input format for event is invalid.
     */
    public static String invalidEvent() {
        System.out.println(line + "\tEvent format is invalid\n" + line);
        return javaFxLine + "\tEvent format is invalid\n" + javaFxLine;
    }

    /**
     * Renders an error message that user input format for todo is invalid.
     */
    public static String invalidTodo() {
        System.out.println(line + "\tTodo format is invalid\n" + line);
        return javaFxLine + "\tTodo format is invalid\n" + javaFxLine;
    }

    /**
     * Renders an error message that the application has issues creating a folder for duke.txt.
     */
    public static String errorCreatingFolder() {
        System.out.println("Something went wrong while creating src\\main\\java\\data folder \n");
        return "Something went wrong while creating src\\main\\java\\data folder \n";
    }

    /**
     * Renders an error message that the application has issues appending to duke.txt.
     */
    public static String errorAppendingToFile() {
        System.out.println("Something went wrong while writing to file \n");
        return "Something went wrong while writing to file \n";
    }

    /**
     * Renders an error message that the application has issues amending duke.txt.
     */
    public static String errorAmendingFile() {
        System.out.println("Something went wrong while amending text to file \n");
        return "Something went wrong while amending text to file \n";
    }

    /**
     * Renders an error message that the application has issues deleting text from duke.txt.
     */
    public static String errorDeletingTextFromFile() {
        System.out.println("Something went wrong while deleting text from file \n");
        return "Something went wrong while deleting text from file \n";
    }

    /**
     * Renders an error message that the application has issues loading task data from duke.txt into task list.
     */
    public static String errorLoadingTextIntoTaskList() {
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
