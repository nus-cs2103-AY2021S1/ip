package duke.resource;

import duke.Command;

/**
 * Parser class that parses the user input.
 */

public class Parser {

    /**
     * Parses the given user input, then returns a Command object with the associated input.
     * @param command a String that contains the user input
     * @return a Command object with the associated input
     */

    public static Command parse(String command) {
        return new Command(command.split(" "));
    }

}
