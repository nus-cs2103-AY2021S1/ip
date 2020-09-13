package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.PriorityCommand;

/**
 * The Parser class parses user input into
 * commands to be executed by the Command class.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
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
        } else if (userInput.equals("help")) {
            return new HelpCommand();
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
                || inputWords[0].equals("event")) {
            // Dino adds task to list
            return new AddCommand(userInput);
        } else if (checkInputForPriority(userInput)) {
            // condition checks that user input is in format
            // "priority <word> X", where X is a numeric
            return new PriorityCommand(userInput);
        } else {
            throw new DukeException("Invalid command entered! Please enter a valid command.");
        }
    }

    /**
     * Checks the user input format to see if it matches a command to set
     * priority of task.
     * @param input user input command
     * @return boolean true if input format is correct
     */
    public static boolean checkInputForPriority(String input) {
        String[] inputWords = input.split(" ");
        if (inputWords[0].equals("priority") && inputWords[2].matches("[0-9]+")
                && inputWords.length == 3) {
            String priority = inputWords[1];
            switch (priority) {
            case "high":
            case "mid":
            case "low":
                return true;
            default:
                return false;
            }
        } else {
            return false;
        }
    }
}
