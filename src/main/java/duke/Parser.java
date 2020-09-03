package duke;



import duke.Command.*;

import java.io.IOException;

/**
 * The Parser class comprehends and carries out the user commands
 * It is used in the Ui class
 */

public class Parser {

    /**
     * Constructor for loading deadlines using switch and case
     * @param command   the user's command
     */
    public static Command parse(String command) throws IOException {
        switch (command) {
            case "bye":
                return new ByeC();
            case "list":
                return new ListC();
            case "done":
                return new DoneC();
            case "todo":
                return new TodoC();
            case "deadline":
                return new DeadlineC();
            case "event":
                return new EventC();
            case "delete":
                return new DeleteC();
            case "on":
                return new OnC();
            case "by":
                return new ByC();
            case "find":
                return new FindC();
            default:
                return new Command();
        }
    }
}
