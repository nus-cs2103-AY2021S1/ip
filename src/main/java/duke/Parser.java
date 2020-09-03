package duke;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.UndoneCommand;


/**
 * Handles parsing of inputs from the user and generates Command objects accordingly.
 */
public class Parser {

    private boolean isQuit = false;

    public boolean hasQuit() {
        return isQuit;
    }

    /**
     * Parses and makes sense of the user input.
     * @param input The user's input string.
     * @return A Command object corresponding to what the user has input.
     */
    public Command parse(String input) {
        if (input.equals("exit")) {
            isQuit = true;
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("help")) {
            return new HelpCommand();
        } else if (input.startsWith("done")) {
            return new DoneCommand(Integer.parseInt(input.substring(input.length() - 1)));
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(Integer.parseInt(input.substring(input.length() - 1)));
        } else if (input.startsWith("find ")) {
            return new FindCommand(input.substring(5));
        } else if (input.startsWith("undone")) {
            return new UndoneCommand(Integer.parseInt(input.substring(input.length() - 1)));
        } else {
            return new AddCommand(input);
        }
    }
}
