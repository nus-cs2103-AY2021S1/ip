package duke;

import duke.command.*;

public class Parser {

    public static Command parse(String userCommand) throws DukeException {
        String[] inputWords = userCommand.split(" ");

        if (userCommand.equals("bye")) {
            return new ByeCommand();
        } else if (userCommand.equals("format")) {
            return new FormatCommand();
        } else if (userCommand.equals("list")) {
            return new ListCommand();
        } else if (inputWords[0].equals("done") && inputWords.length == 2
                && inputWords[1].matches("[0-9]+")) {
            // condition checks that user input is in the format "done X" where X is a numeric
            return new MarkDoneCommand(userCommand);
        } else if (inputWords[0].equals("delete") && inputWords.length == 2
                && inputWords[1].matches("[0-9]+")) {
            // condition checks that user input is in the format "delete X" where X is a numeric
            return new DeleteCommand(userCommand);
        } else if (inputWords[0].equals("find") && inputWords.length == 2) {
            // condition checks that user input is in format "find <key word>"
            return new FindCommand(userCommand);
        } else if (inputWords[0].equals("todo") || inputWords[0].equals("deadline")
                || inputWords[0].equals("event")){
            // Dino adds task to list
            return new AddCommand(userCommand);
        } else {
            throw new DukeException("Invalid command entered! Please enter a valid command.");
        }
    }
}
