package duke.parts;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.PrintCommand;
import duke.command.SortCommand;
import duke.error.UnknownAction;


/**
 * Used to parse inputs from the user
 */
public class Parser {

    /**
     * Process the input and return a command which represents the input.
     * @author Roger Lim
     * @param input The input string got from the user.
     * @return A command that can be executed.
     * @throws UnknownAction
     *
     */

    public static Command parse(String input) throws UnknownAction {
        if (input.indexOf("todo") == 0 || input.indexOf("deadline") == 0 || input.indexOf("event") == 0) {
            return new AddCommand(input);
        } else if (input.equals("list")) {
            return new PrintCommand();
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("sort")) {
            return new SortCommand();
        } else if (input.indexOf("delete") == 0) {
            String index = input.replaceAll("\\D", "");
            assert Integer.parseInt(index) > 0;
            return new DeleteCommand(Integer.parseInt(index));
        } else if (input.indexOf("find") == 0) {
            int space = input.indexOf(" ") + 1;
            String word = input.substring(space, input.length());
            return new FindCommand(word);
        } else if (input.indexOf("done") == 0) {
            int space = input.indexOf(" ") + 1;
            String index = input.substring(space, input.length());
            return new DoneCommand(index);
        } else {
            throw new UnknownAction();
        }
    }
}
