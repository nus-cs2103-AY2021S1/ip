package exception;

public class DukeException {
    private final static String line = "\t____________________________________________________________\n";

    public static void invalidCommand() {
        System.out.println(line + "\tCommand written incorrectly. Please check again.\n" + line);
    }

    public static void genericTask() {
        System.out.println(line + "\tPlease specify either a Deadline, To Do, or Event!\n" + line);
    }

    public static void noSuchTask() {
        System.out.println(line + "\tNo such task in list of task.\n" + line);
    }

    public static void invalidDeadline() {
        System.out.println(line + "\tDeadline format is invalid\n" + line);
    }

    public static void invalidEvent() {
        System.out.println(line + "\tEvent format is invalid\n" + line);
    }

    public static void invalidTodo() {
        System.out.println(line + "\tTodo format is invalid\n" + line);
    }

    public static void errorCreatingFolder() {
        System.out.println("Something went wrong while creating src\\main\\java\\data folder \n");
    }

    public static void errorAppendingToFile() {
        System.out.println("Something went wrong while writing to file \n");
    }

    public static void errorAmendingFile() {
        System.out.println("Something went wrong while amending text to file \n");
    }

    public static void errorDeletingTextFromFile() {
        System.out.println("Something went wrong while deleting text from file \n");
    }

    public static void errorLoadingTextIntoTaskList() {
        System.out.println("<<IGNORE THIS ERROR IF IT IS YOUR FIRST TIME USING THE APPLICATION AND HAVE NOT ADD ANYTHING INTO TASK LIST!>>");
        System.out.println("<<ERROR IS THROWN BECAUSE data\\duke.txt\\ HAS NOT BEEN CREATED YET ON FIRST RUN>>");
        System.out.println("Something went wrong while loading duke.txt into task list \n");
    }
}
