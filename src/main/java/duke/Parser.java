package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;

/**
 * Handles the main logic of inputs and what Command to execute.
 */
public class Parser {

    /**
     * The main method to this class to handle inputs and give commands activated.
     * @param input the user input.
     * @retrun a Command indicating the type of action the user requires.
     */
    public static Command parse(String input) {
        String checker;
        if (input.length() > 5) {
            checker = input.substring(0, 4);
        } else {
            checker = "nothing";
        }
        if (input.equals("list")) {
            return new ListCommand(input);
        } else if (input.equals("bye")) {
            return new ExitCommand(input);
        } else if (checker.equals("done")) {
            int num = Character.getNumericValue(input.charAt(5));
            return new DoneCommand(input, num);
        } else if (checker.equals("dele")) {
            int num = Character.getNumericValue(input.charAt(7));
            return new DeleteCommand(input, num);
        } else if (checker.equals("find")) {
            return new FindCommand(input);
        } else if (checker.equals("help")) {
            return new HelpCommand(input);
        } else {
            return new AddCommand(input);
        }
    }
}
