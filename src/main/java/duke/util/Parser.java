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

import java.util.Arrays;

/**
 * The Parser is in charge of parsing raw strings of user inputs
 * and generating the appropriate response to the inputs. For example,
 * when the user sends "exit", the parser will return an ExitCommand
 * to be executed by the program.
 *
 * All methods of this class are static as this class is meant to be used functionally.
 */
public class Parser {

    /**
     * Splits the raw string using single spaces as delimiters to generate
     * tokenized strings. This is required for the program to parse commands.
     * Example:
     *
     * "a user input" -> ["a", "user", "input"]
     *
     * @param raw the raw string input.
     * @return the split string.
     */
    private static String[] format(String raw) {
        return raw.split(" ");
    }

    /**
     * This method is used when the user's command involves
     * task creation. The parser extracts the description of the task.
     *
     * "todo sleep for 20 hrs"          -> "sleep for 20 hrs"
     * "event lecture /at today 10:00"  -> "lecture /at today 10:00"
     *
     * The output string will be passed onto the respective task constructors.
     * @param raw the raw string input.
     * @return the task description.
     */
    public static String getTaskDescription(String[] raw) {
        String[] temp = Arrays.copyOfRange(raw, 1, raw.length);
        return String.join(" ", temp);
    }

    /**
     * Generates the appropriate command object based on the user input.
     * This requires a series of string analysis such as checking of the
     * command denoted as the first word of the user's message, and further
     * parsing of the rest of the message accordingly.
     * @param input the raw string input.
     * @return the appropriate Command object in response to the user input.
     * @throws DukeException when task creation fails or task indices are incorrect.
     */
    public static Command parse(String input) throws DukeException {
        String[] parsed = Parser.format(input);
        String command = parsed[0];

        switch (command.toLowerCase()) {
        case "bye":
        case "quit":
        case "exit":
            return new ExitCommand();
        case "todo":
        case "event":
        case "deadline":
            String description = Parser.getTaskDescription(parsed);
            return new AddCommand(command, description);
        case "done":
            try {
                int i = Integer.parseInt(parsed[1]);
                return new DoneCommand(i);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Invalid task number!");
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                return new InvalidCommand("Hm? What task number have you done?");
            }
        case "list":
            return new ListCommand();
        case "delete":
        case "remove":
            try {
                String taskSelection = parsed[1];
                if (taskSelection.equals("all")) {
                    return new RemoveCommand();
                }
                int taskNumber = Integer.parseInt(taskSelection);
                return new RemoveCommand(taskNumber);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Invalid task number!");
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                return new InvalidCommand("Uh? What task number to remove?");
            }
        case "find":
            try {
                return new FindCommand(parsed[1]);
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                return new InvalidCommand("Hm? What do you want to find?");
            }
        case "help":
            return new HelpCommand();
        default:
            return new InvalidCommand();
        }
    }
}
