package duke;

import duke.command.*;

public class Parser {

    public static Command parse(String userCommand) {
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
        } else {
            // Dino adds task to list
            return new AddCommand(userCommand, inputWords);
        }
    }
}
