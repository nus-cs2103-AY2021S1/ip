package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
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
     * @throws DukeException If user input command is invalid command.
     */
    public static Command parse(String userInput) throws DukeException {
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
        } else if (inputWords[0].equals("find") && inputWords.length == 2) {
            // condition checks that user input is in format "find <key word>"
            return new FindCommand(userInput);
        } else if (inputWords[0].equals("todo") || inputWords[0].equals("deadline")
                || inputWords[0].equals("event")){
            // Dino adds task to list
            return new AddCommand(userInput);
        } else {
            throw new DukeException("Invalid command entered! Please enter a valid command.");
        }
    }
}
