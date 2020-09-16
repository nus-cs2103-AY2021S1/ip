package duke.util;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.RemoveCommand;
import duke.command.ListCommand;
import duke.command.FindCommand;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.HelpCommand;
import duke.command.SortCommand;
import duke.command.StartTaskCommand;

import java.util.Arrays;

/**
 * The Parser is in charge of parsing raw strings of user inputs
 * and generating the appropriate response to the inputs.
 */
public class Parser {

    private static String[] format(String raw) {
        return raw.split("\\s+");
    }

    /**
     * Gets the command in lower case.
     * @param raw the raw string input.
     * @return the command in lower case.
     */
    public static String getCommand(String[] raw) {
        return raw[0].toLowerCase();
    }

    /**
     * Gets the text after the command which is typically the first token
     * of the raw string.
     * @param raw the raw string input.
     * @return the text after the command.
     */
    public static String getTextAfterCommand(String[] raw) {
        String[] temp = Arrays.copyOfRange(raw, 1, raw.length);
        return String.join(" ", temp);
    }

    /**
     * Parses the raw string input and generates the appropriate
     * command as a response to the input.
     * @param input the raw string input.
     * @return the appropriate Command object in response to the user input.
     * @throws DukeException when parsing of the input fails.
     */
    public static Command parse(String input) throws DukeException {
        if (input.contains("|")) {
            return new InvalidCommand("Sorry you cannot use '|' because that's my favorite character :P");
        }
        String[] parsed = Parser.format(input);
        String command = Parser.getCommand(parsed);

        switch (command) {
        case "bye":
        case "quit":
        case "exit":
            return new ExitCommand();
        case "todo":
        case "event":
        case "deadline":
        case "fixed":
            String description = Parser.getTextAfterCommand(parsed);
            return new AddCommand(command, description);
        case "done":
            try {
                String taskSelection = parsed[1];
                if (taskSelection.equals("all")) {
                    return new DoneCommand();
                }
                int taskNumber = Integer.parseInt(taskSelection);
                return new DoneCommand(taskNumber);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Invalid task number!");
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                return new InvalidCommand("Uh oh! Here's an example:\n'done 1' or 'done all'");
            }
        case "list":
            return new ListCommand();
        case "delete":
        case "remove":
            try {
                String taskSelection = parsed[1].toLowerCase();
                if (taskSelection.equals("all") || taskSelection.equals("done")) {
                    return new RemoveCommand(taskSelection);
                }
                int taskNumber = Integer.parseInt(taskSelection);
                return new RemoveCommand(taskNumber);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Invalid task number!");
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                return new InvalidCommand("Uh oh! Here's an example:\n'remove 1' or 'remove all'");
            }
        case "find":
            try {
                return new FindCommand(parsed[1]);
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                return new InvalidCommand("Uh oh! Here's an example:\n'find assignment'");
            }
        case "help":
            return new HelpCommand();
        case "sort":
            try {
                return new SortCommand(parsed[1]);
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                return new InvalidCommand(
                        "Uh oh! Here's an example:\n'sort name'\nYou can sort by name, datetime, type");
            }
        case "start":
            try {
                int taskNumber = Integer.parseInt(parsed[1]);
                String startAt = String.join(" ", Arrays.copyOfRange(parsed, 2, parsed.length));
                return new StartTaskCommand(taskNumber, startAt);
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                return new InvalidCommand("Uh oh! Here's an example:\n'start 1 today 23:00' or 'start 1 now'");
            } catch (NumberFormatException nfe) {
                throw new DukeException("Invalid fixed task number!");
            }
        default:
            return new InvalidCommand();
        }
    }
}
