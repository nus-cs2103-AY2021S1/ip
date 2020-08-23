package duke.exception;

public class UnknownCommandException extends DukeException {

    private static final String unsureString = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    private static final String listingString = "Here are the list of duke.commands available:\n";
    private static final String s1 = "1. list\n";
    private static final String s2 = "2. bye\n";
    private static final String s3 = "3. todo \'duke.task name\' (e.g. todo duke.task 1)\n";
    private static final String s4 =
            "4. deadline \'duke.task name\' /by \'any date format\' (e.g. deadline project /by YYYY-MM-DD HHMM or" +
                    " YYYY-MM-DD)\n";
    private static final String s5 =
            "5. event \'event name\' /at \'any date format\' (e.g. event project /at YYYY-MM-DD HHMM or YYYY-MM-DD)\n";
    private static final String s6 = "6. delete ___ (e.g. delete 1)  *Note that it should be a value more than 0*\n";
    private static final String s7 = "7. done ___ (e.g. done 1)  *Note that it should be a value more than 0*\n";

    public UnknownCommandException() {
        super(unsureString + listingString + s1 + s2 + s3 + s4 + s5 + s6 + s7);
    }
}
