package taskbot.parser;

import taskbot.command.Command;
import taskbot.command.DeadlineCommand;
import taskbot.command.DeleteCommand;
import taskbot.command.DoneCommand;
import taskbot.command.EventCommand;
import taskbot.command.ExitCommand;
import taskbot.command.FindCommand;
import taskbot.command.ListCommand;
import taskbot.command.TodoCommand;
import taskbot.command.UpcomingCommand;
import taskbot.exceptions.EmptyArgumentException;
import taskbot.exceptions.InvalidCommandException;

/**
 * Parses the user input to give appropriate commands.
 */
public class Parser {
    /**
     * Determines the correct command to be used.
     *
     * @param command The string to be parsed.
     * @return The command corresponding to the string parsed.
     * @throws InvalidCommandException if the command given does not match any known commands.
     * @throws EmptyArgumentException if a command requires arguments but received none.
     */
    public static Command parse(String command) throws InvalidCommandException, EmptyArgumentException {
        assert command.length() > 0 : "Empty command string";
        /*Separates the command into the command keyword
          and the required arguments. */
        String[] commandArgs = command.split(" ", 2);

        switch (commandArgs[0]) {
        case "todo":
            if (commandArgs.length == 1 || commandArgs[1].strip().length() == 0) {
                throw new EmptyArgumentException(
                        "The description of a todo cannot be empty. Please input a valid description.");
            }
            return new TodoCommand(commandArgs[1]);
        case "deadline":
            if (commandArgs.length == 1 || commandArgs[1].strip().length() == 0) {
                throw new EmptyArgumentException(
                        "The description of a deadline cannot be empty. Please input a valid description.");
            }
            return new DeadlineCommand(commandArgs[1]);
        case "event":
            if (commandArgs.length == 1 || commandArgs[1].strip().length() == 0) {
                throw new EmptyArgumentException(
                        "The description of an event cannot be empty. Please input a valid description.");
            }
            return new EventCommand(commandArgs[1]);
        case "list":
            return new ListCommand();
        case "upcoming":
            if (commandArgs.length == 1 || commandArgs[1].strip().length() == 0) {
                throw new EmptyArgumentException("Please enter the number of days.");
            }
            try {
                int days = Integer.parseInt(commandArgs[1]);
                return new UpcomingCommand(days);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("Please enter a valid digit for days.");
            }
        case "find":
            if (commandArgs.length == 1 || commandArgs[1].strip().length() == 0) {
                throw new EmptyArgumentException("Please enter a keyword to search for.");
            }
            //Splits the input into multiple keywords if possible
            String[] keywords = commandArgs[1].split(" ");
            return new FindCommand(keywords);
        case "done":
            if (commandArgs.length == 1 || commandArgs[1].strip().length() == 0) {
                throw new EmptyArgumentException("Please enter the index of the task you wish to complete.");
            }
            try {
                int taskIndex = Integer.parseInt(commandArgs[1]) - 1;
                return new DoneCommand(taskIndex);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("Please enter a valid index.");
            }
        case "delete":
            if (commandArgs.length == 1 || commandArgs[1].strip().length() == 0) {
                throw new EmptyArgumentException("Please enter the index of the task you wish to delete.");
            }
            try {
                int taskIndex = Integer.parseInt(commandArgs[1]) - 1;
                return new DeleteCommand(taskIndex);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("Please enter a valid index.");
            }
        case "bye":
            return new ExitCommand();
        default:
            throw new InvalidCommandException("That was not a valid command.\nPlease try again.");
        }
    }
}
