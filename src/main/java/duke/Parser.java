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
    public static Command parse(String input) throws IOException {
        String command = input.split(" ")[0];
        switch (command) {
            case "bye":
                return new ByeC();
            case "list":
                return new ListC();
            case "done":
                return new DoneC(input);
            case "todo":
                return new TodoC(input);
            case "deadline":
                return new DeadlineC(input);
            case "event":
                return new EventC(input);
            case "delete":
                return new DeleteC(input);
            case "on":
                return new OnC(input);
            case "by":
                return new ByC(input);
            case "find":
                return new FindC(input);
            default:
                return new Command();
        }
    }

}
