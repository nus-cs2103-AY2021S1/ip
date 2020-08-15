public class DukeException {
    private final static String line = "\t____________________________________________________________\n";

    protected static void invalidDoneCommand() {
        System.out.println(line + "\tDone command written incorrectly. Please check again.\n" + line);
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
}
