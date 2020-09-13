package duke.parser;

import static duke.util.Keyword.ARRAY_SIZE;
import static duke.util.Keyword.DEADLINE_DELIMITER;
import static duke.util.Keyword.EMPTY_STRING;
import static duke.util.Keyword.EVENT_DELIMITER;
import static duke.util.Keyword.INVALID_ARR_ERROR;
import static duke.util.Keyword.INVALID_TASK_TYPE;
import static duke.util.Keyword.KEYWORD_BYE;
import static duke.util.Keyword.KEYWORD_DEADLINE;
import static duke.util.Keyword.KEYWORD_DELETE;
import static duke.util.Keyword.KEYWORD_DONE;
import static duke.util.Keyword.KEYWORD_EVENT;
import static duke.util.Keyword.KEYWORD_FIND;
import static duke.util.Keyword.KEYWORD_HELP;
import static duke.util.Keyword.KEYWORD_LIST;
import static duke.util.Keyword.KEYWORD_TODO;
import static duke.util.Keyword.MULTI_SPACE;
import static duke.util.Keyword.SINGLE_SPACE;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ShowCommand;
import duke.exception.DukeException;
import duke.exception.EmptyTimeException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.UnknownCommandException;
import duke.task.TaskType;

/**
 * Handles the parsing of user input.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param text User text input.
     * @return Corresponding command.
     * @throws DukeException If user input does not belong to the list of known commands.
     */
    public static Command parse(String text) throws DukeException {
        String[] inputArr = deconstructInput(text);
        String keyWord = getKeyWord(inputArr);
        String details = getRestOfWord(inputArr);

        switch (keyWord) {
        case KEYWORD_LIST:
            return new ShowCommand();
        case KEYWORD_DONE:
            return new DoneCommand(details);
        case KEYWORD_DELETE:
            return new DeleteCommand(details);
        case KEYWORD_TODO:
            return new AddToDoCommand(details);
        case KEYWORD_DEADLINE:
            return new AddDeadlineCommand(details);
        case KEYWORD_EVENT:
            return new AddEventCommand(details);
        case KEYWORD_FIND:
            return new FindCommand(details);
        case KEYWORD_HELP:
            return new HelpCommand();
        case KEYWORD_BYE:
            return new ExitCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Deconstructs the string into a string array.
     *
     * @param input Input String.
     * @return String array of the input.
     */
    private static String[] deconstructInput(String input) {
        String formattedString = input.trim().replaceAll(MULTI_SPACE, SINGLE_SPACE);
        return formattedString.split(SINGLE_SPACE, ARRAY_SIZE);
    }

    /**
     * Obtains the keyword of the user input.
     *
     * @param arr User input in a String array.
     * @return Keyword.
     */
    private static String getKeyWord(String[] arr) {
        assert (arr.length > 0) : INVALID_ARR_ERROR;
        return arr[0].toUpperCase();
    }

    /**
     * Obtains the rest of the user input.
     *
     * @param arr User input in a String array.
     * @return Rest of the user input.
     */
    private static String getRestOfWord(String[] arr) {
        assert (arr.length > 0) : INVALID_ARR_ERROR;
        return arr.length == 1 ? EMPTY_STRING : arr[1];
    }

    /**
     * Returns the unique identifier tied to this {@code Task}.
     *
     * @return String identifier of the Task.
     */
    private static String getIdentifier(TaskType taskType) {
        assert (taskType == TaskType.DEADLINE || taskType == TaskType.EVENT);
        return taskType == TaskType.EVENT ? EVENT_DELIMITER : DEADLINE_DELIMITER;
    }

    /**
     * Parses the description for the {@code Deadline} and {@code Event} tasks and returns an array representation
     * of the input.
     *
     * @param description Description of task.
     * @param taskType Task type of task.
     * @return String Array consisting of task details and time.
     * @throws InvalidDeadlineException If deadline is not formatted correctly.
     * @throws InvalidEventException If Event is not formatted correctly.
     * @throws EmptyTimeException If time of event is not specified.
     */
    public static String[] parseTaskDescription(String description, TaskType taskType)
        throws InvalidDeadlineException, InvalidEventException, EmptyTimeException {

        String[] inputArr = description.split(getIdentifier(taskType), ARRAY_SIZE);
        if (inputArr.length < ARRAY_SIZE) {
            switch(taskType) {
            case DEADLINE:
                throw new InvalidDeadlineException();
            case EVENT:
                throw new InvalidEventException();
            default:
                assert false : INVALID_TASK_TYPE;
            }
        }
        String time = inputArr[1];
        if (time.isBlank()) {
            throw new EmptyTimeException(taskType);
        }

        inputArr[1] = inputArr[1].trim();
        return inputArr;
    }
}
