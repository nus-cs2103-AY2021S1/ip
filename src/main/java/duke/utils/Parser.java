package duke.utils;

import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;


/**
 * Handles all the commands from the user.
 */
public class Parser {

    /**
     * Converts the users input from string to Command type.
     *
     * @param input raw unprocessed string the user entered.
     * @return Command which the user wanted.
     * @throws DukeException if the command format is wrong.
     */
    public static Command parse(String input) throws DukeException {
        String trimmed = input.trim();

        if (trimmed.equals("")) {
            throw new DukeException("Please type a duke command");
        }

        String[] keywords = findKeywords(input.trim());
        CommandType commandType = Command.containsKeyword(keywords[0]);

        switch (commandType) {
        case EXIT:
            return new ExitCommand();
        case CLEAR:
            return new ClearCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            try {
                return new DoneCommand(Integer.parseInt(keywords[1].trim()) - 1);
            } catch (Exception e) {
                throw new DukeException("You can't do something not in the list?");
            }
        case DELETE:
            return new DeleteCommand(Integer.parseInt(keywords[1].trim()) - 1);
        case DEADLINE:
            try {
                int by = input.indexOf(" /by");
                return new DeadlineCommand(input.substring(9, by), input.substring(by + 5));
            } catch (Exception e) {
                throw new DukeException("Deadline format isn't correct");
            }
        case EVENT:
            try {
                int at = input.indexOf(" /at");
                return new EventCommand(input.substring(6, at), input.substring(at + 5));
            } catch (Exception e) {
                throw new DukeException("Event format isn't correct");
            }
        case TODO:
            int indexOfTodoTasks = input.indexOf(" ");
            String descriptionOfTodo = input.substring(indexOfTodoTasks).trim();
            if (descriptionOfTodo.equals("")) {
                throw new DukeException("Todo what? ");
            }
            return new TodoCommand(descriptionOfTodo.trim());
        case FIND:
            int indexOfCharsToFind = input.indexOf(" ");
            String descriptionToFind = input.substring(indexOfCharsToFind).trim();
            try {
                return new FindCommand(descriptionToFind);
            } catch (Exception e) {
                throw new DukeException("Find format isn't correct");
            }
        case UNKNOWN:
        default:
            throw new DukeException("I don't know what that means :( ");
        }
    }


    /**
     * Converts string into array of keywords.
     * Split the strings by the spacing.
     *
     * @param stringToConvert the string to be converted.
     * @return array with commands.
     */
    private static String[] findKeywords(String stringToConvert) {
        return stringToConvert.split(" ");
    }


}
