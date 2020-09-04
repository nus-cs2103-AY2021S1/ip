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
import commands.UpcomingCommand;
import exceptions.InvalidNumberInputException;
import exceptions.InvalidUpcomingInputException;


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
     * @throws InvalidNumberInputException if an invalid input is entered at commands where integer input is required.
     */
    public Command parse(String input) throws InvalidNumberInputException {
        assert !isQuit;
        if (input.equals("exit")) {
            isQuit = true;
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("help")) {
            return new HelpCommand();
        } else if (input.startsWith("done")) {
            try {
                int num = Integer.parseInt(input.substring(input.length() - 1));
                return new DoneCommand(num);
            } catch (NumberFormatException e) {
                throw new InvalidNumberInputException("Please enter a valid number!");
            }
        } else if (input.startsWith("undone")) {
            try {
                int num = Integer.parseInt(input.substring(input.length() - 1));
                return new UndoneCommand(num);
            } catch (NumberFormatException e) {
                throw new InvalidNumberInputException("Please enter a valid number!");
            }
        } else if (input.startsWith("delete")) {
            try {
                int num = Integer.parseInt(input.substring(input.length() - 1));
                return new DeleteCommand(num);
            } catch (NumberFormatException e) {
                throw new InvalidNumberInputException("Please enter a valid number!");
            }
        } else if (input.startsWith("find ")) {
            return new FindCommand(input.substring(5));
        } else if (input.startsWith("remindme")) {
            try {
                int num = Integer.parseInt(input.substring(input.length() - 1));
                return new UpcomingCommand(num);
            } catch (NumberFormatException e) {
                throw new InvalidNumberInputException("Please enter a valid number!");
            } catch (InvalidUpcomingInputException e) {
                throw new InvalidNumberInputException(e.getMessage());
            }
        } else {
            return new AddCommand(input);
        }
    }
}
