package taskbot.Parser;

import taskbot.command.*;
import taskbot.exceptions.EmptyArgumentException;
import taskbot.exceptions.InvalidCommandException;

public class Parser {
    /**
     * Determines the correct command to be used.
     * @param command The string to be parsed.
     * @return The command corresponding to the string.
     */
    public static Command parse(String command) throws InvalidCommandException, EmptyArgumentException {
        String[] commandArgs = command.split(" ", 2);
        switch (commandArgs[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
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
        case "todo":
            if (commandArgs.length == 1 || commandArgs[1].strip().length() == 0) {
                throw new EmptyArgumentException("The description of a todo cannot be empty. Please input a valid description.");
            }
            return new TodoCommand(commandArgs[1]);
        case "deadline":
            if (commandArgs.length == 1 || commandArgs[1].strip().length() == 0) {
                throw new EmptyArgumentException("The description of a deadline cannot be empty. Please input a valid description.");
            }
            return new DeadlineCommand(commandArgs[1]);
        case "event":
            if (commandArgs.length == 1 || commandArgs[1].strip().length() == 0) {
                throw new EmptyArgumentException("The description of an event cannot be empty. Please input a valid description.");
            }
            return new EventCommand(commandArgs[1]);
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
        default:
            throw new InvalidCommandException("That was not a valid command. Please try again.");
        }
    }
}
