package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FormatCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;

public class Parser {

    /**
     * Returns Command corresponding to the
     * user input argument when Duke is run.
     *
     * @param userInput String of user's input command.
     * @return Command command for Dino to execute.
     */
    public static Command parse(String userInput) {
        String[] inputWords = userInput.split(" ");
        if (userInput.equals("bye")) {
            return new ByeCommand();
        } else if (userInput.equals("format")) {
            return new FormatCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (inputWords[0].equals("done") && inputWords.length == 2
                && inputWords[1].matches("[0-9]+")) {
            // condition checks that user input is in the format "done X" where X is a numeric
            return new MarkDoneCommand(userInput);
        } else if (inputWords[0].equals("delete") && inputWords.length == 2
                && inputWords[1].matches("[0-9]+")) {
            // condition checks that user input is in the format "delete X" where X is a numeric
            return new DeleteCommand(userInput);
        } else {
            // Dino adds task to list
            return new AddCommand(userInput);
        }
    }
}
