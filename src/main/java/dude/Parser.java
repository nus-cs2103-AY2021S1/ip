package dude;

import dude.command.Command;
import dude.command.DeadlineCommand;
import dude.command.DeleteCommand;
import dude.command.DoneCommand;
import dude.command.EventCommand;
import dude.command.ExitCommand;
import dude.command.FindCommand;
import dude.command.ListCommand;
import dude.command.TodoCommand;
import dude.util.InvalidArgumentException;
import dude.util.InvalidCommandException;

/**
 * The class that processes the logic behind the bot.
 */

public class Parser {

    /**
     * Returns a Command based on the user input.
     * If the user gives an invalid input, an exception is thrown.
     *
     * @param input The command given by the user.
     * @return Command to be invoked.
     * @throws InvalidArgumentException If there are no arguments.
     * @throws InvalidCommandException If the command is invalid.
     */
    public static Command parse(String input) throws InvalidArgumentException, InvalidCommandException {
        String[] commands = input.split(" ", 2);
        Commands currentCommand;
        try {
            currentCommand = Commands.valueOf(commands[0].toUpperCase());
            switch(currentCommand) {
            case BYE:
                return new ExitCommand(commands[0]);
            case LIST:
                return new ListCommand(commands[0]);
            case DONE:
                return new DoneCommand(commands[0], Integer.valueOf(commands[1]));
            case TODO:
                return new TodoCommand(commands[0], commands[1]);
            case EVENT:
                String[] arr = commands[1].split("/at");
                return new EventCommand(commands[0], arr[0], arr[1].trim());
            case DEADLINE:
                String[] arr2 = commands[1].split("/by");
                return new DeadlineCommand(commands[0], arr2[0], arr2[1].trim());
            case DELETE:
                return new DeleteCommand(commands[0], Integer.valueOf(commands[1]));
            case FIND:
                return new FindCommand(commands[0], commands[1].trim());
            default:
                assert false : currentCommand;
                throw new InvalidCommandException("Sorry, your command is not recognised!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Sorry, your argument cannot be empty!");
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Sorry, your command is not recognised!");
        }
    }
}

