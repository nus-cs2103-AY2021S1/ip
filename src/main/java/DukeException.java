public class DukeException {
    private final static String line = "\t____________________________________________________________\n";

    protected static void invalidCommand() {
        System.out.println(line + "\tCommand written incorrectly. Please check again.\n" + line);
    }

    protected static void genericTask() {
        System.out.println(line + "\tPlease specify either a Deadline, To Do, or Event!\n" + line);
    }

    protected static void noSuchTask() {
        System.out.println(line + "\tNo such task in list of task.\n" + line);
    }

    protected static void invalidDeadline() {
        System.out.println(line + "\tDeadline format is invalid\n" + line);
    }

    protected static void invalidEvent() {
        System.out.println(line + "\tEvent format is invalid\n" + line);
    }

    protected static void invalidTodo() {
        System.out.println(line + "\tTodo format is invalid\n" + line);
    }

    protected static void errorCreatingFolder() {
        System.out.println("Something went wrong while creating src\\main\\java\\data folder \n");
    }

    protected static void errorAppendingToFile() {
        System.out.println("Something went wrong while writing to file \n");
    }

    protected static void errorAmendingFile() {
        System.out.println("Something went wrong while amending text to file \n");
    }

    protected static void errorDeletingTextFromFile() {
        System.out.println("Something went wrong while deleting text from file \n");
    }

    protected static void errorLoadingTextIntoTaskList() {
        System.out.println("<<IGNORE THIS ERROR IF IT IS YOUR FIRST TIME USING THE APPLICATION AND HAVE NOT ADD ANYTHING INTO TASK LIST!>>");
        System.out.println("<<ERROR IS THROWN BECAUSE data\\duke.txt\\ HAS NOT BEEN CREATED YET ON FIRST RUN>>");
        System.out.println("Something went wrong while loading duke.txt into task list \n");
    }
}
