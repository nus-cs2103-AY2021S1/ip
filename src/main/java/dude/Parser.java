package dude;

import dude.command.Command;

import dude.command.DeadlineCommand;
import dude.command.DeleteCommand;
import dude.command.DoneCommand;
import dude.command.ExitCommand;
import dude.command.EventCommand;
import dude.command.ListCommand;
import dude.command.TodoCommand;

import dude.util.InvalidArgumentException;
import dude.util.InvalidCommandException;

public class Parser {
    public static Command parse(String input) throws InvalidArgumentException, InvalidCommandException {
            String[] commands = input.split(" ", 2);
            Commands current;
            try {
                current = Commands.valueOf(commands[0].toUpperCase());
                switch(current) {
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
                default:
                    throw new InvalidCommandException("");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidArgumentException("Sorry, your argument cannot be empty!");
            } catch (InvalidCommandException | IllegalArgumentException e) {
                throw new InvalidCommandException("Sorry, your command is not recognised!");
            }
    }
}

