package nekochan.parser;

import nekochan.command.AddCommand;
import nekochan.command.Command;
import nekochan.command.CompleteCommand;
import nekochan.command.DeleteAllCommand;
import nekochan.command.DeleteCommand;
import nekochan.command.ExitCommand;
import nekochan.command.ListCommand;
import nekochan.command.SearchCommand;
import nekochan.exceptions.ParseNekoCommandException;
import nekochan.task.TaskType;
import nekochan.util.Messages;

/**
 * The {@code Parser} class provides methods for parsing user input.
 */
public class Parser {

    /**
     * An enum containing keywords used by the user for program control.
     */
    enum Keyword {
        EXIT("exit"),
        LIST("list"),
        COMPLETE("complete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        SEARCH("search");

        private final String input;

        Keyword(String keyword) {
            input = keyword;
        }

        /**
         * Finds and returns the {@code Keyword} matching the specified input.
         *
         * @param keyword the input to match against.
         * @return the {@code keyword} corresponding to the specified input.
         * @throws ParseNekoCommandException if there is no matching {@code Keyword}.
         */
        public static Keyword findKeyword(String keyword) throws ParseNekoCommandException {
            for (Keyword k : values()) {
                if (keyword.toLowerCase().equals(k.input)) {
                    return k;
                }
            }
            throw new ParseNekoCommandException(Messages.PARSE_COMMAND_UNKNOWN);
        }
    }

    /**
     * Parses the specified user input and returns its corresponding {@link Command}.
     *
     * @param userInput the user input to be parsed.
     * @return a {@code Command} object to be executed.
     * @throws ParseNekoCommandException if the user input is not recognised.
     */
    public static Command parse(String userInput) throws ParseNekoCommandException {
        String[] inputs = userInput.split(" ", 2);
        // By spec, inputs is guaranteed to have at least one element.
        String inputKeyword = inputs[0];
        Keyword keyword = Keyword.findKeyword(inputKeyword);
        switch (keyword) {
        case EXIT:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case COMPLETE:
            try {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                return new CompleteCommand(index);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new ParseNekoCommandException(Messages.PARSE_COMMAND_COMPLETE_MISSING_ARGUMENT);
            }
        case DELETE:
            try {
                if (userInput.toLowerCase().contains("all")) {
                    return new DeleteAllCommand();
                } else {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    return new DeleteCommand(index);
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new ParseNekoCommandException(Messages.PARSE_COMMAND_DELETE_MISSING_ARGUMENT);
            }
        case DEADLINE:
            try {
                return new AddCommand(TaskType.DEADLINE, inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParseNekoCommandException(Messages.PARSE_COMMAND_DEADLINE_MISSING_ARGUMENT);
            }
        case TODO:
            try {
                return new AddCommand(TaskType.TODO, inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParseNekoCommandException(Messages.PARSE_COMMAND_TODO_MISSING_ARGUMENT);
            }
        case EVENT:
            try {
                return new AddCommand(TaskType.EVENT, inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParseNekoCommandException(Messages.PARSE_COMMAND_EVENT_MISSING_ARGUMENT);
            }
        case SEARCH:
            try {
                return new SearchCommand(inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParseNekoCommandException(Messages.PARSE_COMMAND_SEARCH_MISSING_ARGUMENT);
            }
        default:
            throw new ParseNekoCommandException(Messages.PARSE_COMMAND_UNKNOWN);
        }
    }
}
