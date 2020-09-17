package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindByDateCommand;
import duke.command.FindByKeywordCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;

/**
 * Represents a parser to make sense of the user inputs so that
 * the correct command can be executed by Duke.
 */
public class Parser {

    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_BY_DATE_COMMAND = "find_by_date";
    private static final String FIND_BY_KEYWORD_COMMAND = "find";
    private static final String HELP_COMMAND = "help";
    private static final String TAG_COMMAND = "tag";

    /**
     * Parses the input entered by users and returns a Command object to be
     * executed by Duke if the input is valid.
     *
     * @param userInput Input entered by the user.
     * @return Command object.
     * @throws DukeException If the input is not a valid command.
     */
    public static Command parse(String userInput) throws DukeException {
        String input = userInput.trim();
        String[] splitInput = input.split(" ", 2);
        String function = splitInput[0];
        if (input.isEmpty()) {
            String error = "No input was entered! Please enter something!";
            throw new InvalidFunctionException(error);
        } else if (input.equals(Parser.HELP_COMMAND)) {
            return new HelpCommand();
        } else if (input.equals(Parser.EXIT_COMMAND)) {
            return new ExitCommand();
        } else if (input.equals(Parser.LIST_COMMAND)) {
            return new ListCommand();
        } else if (function.equals(Parser.DONE_COMMAND)) {
            int taskID = parseDoneOrDeleteInput(input);
            return new DoneCommand(taskID);
        } else if (function.equals(Parser.TODO_COMMAND)) {
            String todoDescription = parseTodoInput(input);
            return new ToDoCommand(todoDescription);
        } else if (function.equals(Parser.DEADLINE_COMMAND)) {
            String[] parsedDeadlineInput = parseDeadlineInput(input);
            return new DeadlineCommand(parsedDeadlineInput);
        } else if (function.equals(Parser.EVENT_COMMAND)) {
            String[] parsedEventInput = parseEventInput(input);
            return new EventCommand(parsedEventInput);
        } else if (function.equals(Parser.DELETE_COMMAND)) {
            int taskID = parseDoneOrDeleteInput(input);
            return new DeleteCommand(taskID);
        } else if (function.equals(Parser.FIND_BY_DATE_COMMAND)) {
            String searchDate = parseFindInput(input);
            return new FindByDateCommand(searchDate);
        } else if (function.equals(Parser.FIND_BY_KEYWORD_COMMAND)) {
            String searchKeyword = parseFindInput(input);
            return new FindByKeywordCommand(searchKeyword);
        } else if (function.equals(Parser.TAG_COMMAND)) {
            String[] parsedTagInput = parseTagInput(input);
            return new TagCommand(parsedTagInput);
        } else {
            return new InvalidCommand();
        }
    }

    /**
     * Parses the user input for a deadline command to retrieve the details of the deadline task
     * and store it in an array.
     *
     * @param userInput Input entered by the user.
     * @return String array containing the deadline description and deadline time stamp.
     * @throws InvalidFunctionException If the deadline information is invalid or the input is missing arguments.
     */
    public static String[] parseDeadlineInput(String userInput) throws InvalidFunctionException {
        try {
            String deadlineArguments = userInput.split("deadline")[1];
            String[] parsedDeadlineArguments = deadlineArguments.split(" /by ");
            if (!deadlineArguments.contains(" /by ") && !deadlineArguments.endsWith("/by")) {
                String error = "Your deadline task has an incorrect format. The task cannot be created.";
                throw new InvalidFunctionException(error);
            } else if (deadlineArguments.trim().equals("/by")) {
                String error = "Your deadline task is missing a description and time stamp. "
                        + "The task cannot be created.";
                throw new InvalidFunctionException(error);
            } else if (deadlineArguments.trim().endsWith("/by")) {
                String error = "Your deadline task is missing a time stamp. The task cannot be created.";
                throw new InvalidFunctionException(error);
            } else if (parsedDeadlineArguments[0].isBlank()) {
                String error = "Your deadline task is missing a description. The task cannot be created.";
                throw new InvalidFunctionException(error);
            }
            String description = parsedDeadlineArguments[0].trim();
            String time = parsedDeadlineArguments[1].trim();
            return new String[]{description, time};
        } catch (ArrayIndexOutOfBoundsException ex) {
            String error = "Your deadline task has missing arguments. The task cannot be created.";
            throw new InvalidFunctionException(error);
        }
    }

    /**
     * Parses the user input for an event command to retrieve the details of the event task
     * and store it in an array.
     *
     * @param userInput Input entered by the user.
     * @return String array containing the event description and event time stamp.
     * @throws InvalidFunctionException If the event information is invalid or the input is missing arguments.
     */
    public static String[] parseEventInput(String userInput) throws InvalidFunctionException {
        try {
            String eventArguments = userInput.split("event")[1];
            String[] parsedEventArguments = eventArguments.split(" /at ");
            if (!eventArguments.contains(" /at ") && !eventArguments.endsWith("/at")) {
                String error = "Your event task has an incorrect format. The task cannot be created.";
                throw new InvalidFunctionException(error);
            } else if (eventArguments.trim().equals("/at")) {
                String error = "Your event task is missing a description and time stamp. "
                        + "The task cannot be created.";
                throw new InvalidFunctionException(error);
            } else if (eventArguments.trim().endsWith("/at")) {
                String error = "Your event task is missing a time stamp. The task cannot be created.";
                throw new InvalidFunctionException(error);
            } else if (parsedEventArguments[0].isBlank()) {
                String error = "Your event task is missing a description. The task cannot be created.";
                throw new InvalidFunctionException(error);
            }
            String description = parsedEventArguments[0].trim();
            String time = parsedEventArguments[1].trim();
            return new String[]{description, time};
        } catch (ArrayIndexOutOfBoundsException ex) {
            String error = "Your event task has missing arguments. The task cannot be created.";
            throw new InvalidFunctionException(error);
        }
    }

    /**
     * Parses the user input for a todo command to retrieve the todo description.
     *
     * @param userInput Input entered by the user.
     * @return String containing the todo description.
     * @throws InvalidFunctionException If the todo description is missing.
     */
    public static String parseTodoInput(String userInput) throws InvalidFunctionException {
        String[] parsedTodoArguments = userInput.split("todo");
        if (parsedTodoArguments.length == 0) {
            String error = "Your todo task description is empty. The task cannot be created.";
            throw new InvalidFunctionException(error);
        }
        String description = parsedTodoArguments[1].trim();
        return description;
    }

    /**
     * Parses the user input for either a done or delete command to retrieve the ID
     * of the desired task to be marked as done or deleted respectively.
     *
     * @param userInput Input entered by the user.
     * @return int value representing the ID of the desired task to be marked as done or deleted.
     * @throws InvalidFunctionException If the task ID is missing or invalid.
     */
    public static int parseDoneOrDeleteInput(String userInput) throws InvalidFunctionException {
        String[] parsedInput = userInput.split(" ", 2);
        String command = parsedInput[0];
        try {
            String taskIdAsString = parsedInput[1].trim();
            int taskID = Integer.parseInt(taskIdAsString);
            return taskID;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            String error = "No Task ID provided! Please input the ID of the task you wish to "
                    + (command.equals("done") ? "mark as done." : "delete.");
            throw new InvalidFunctionException(error);
        }
    }

    /**
     * Parses the user input for either a find by keyword or find by date command to retrieve
     * the search keyword or search date respectively.
     *
     * @param userInput Input entered by the user.
     * @return String containing either the search keyword or search date.
     * @throws InvalidFunctionException If no search keyword or date was provided by the user.
     */
    public static String parseFindInput(String userInput) throws InvalidFunctionException {
        String[] parsedInput = userInput.split(" ", 2);
        String command = parsedInput[0];
        try {
            String searchInput = parsedInput[1].trim();
            return searchInput;
        } catch (ArrayIndexOutOfBoundsException ex) {
            String missingKeywordError = "No keyword for the search was entered. Please enter a keyword!";
            String missingDateError = "No task date provided. Please input a valid date using the format: 'dd/mm/yyyy'";
            String error = (command.equals("find") ? missingKeywordError : missingDateError);
            throw new InvalidFunctionException(error);
        }
    }

    /**
     * Parses the user input for a tag command to retrieve the details
     * of the tag command and store it in an array.
     *
     * @param userInput Input entered by the user.
     * @return String array containing the ID of the task to be tagged and the tag.
     * @throws InvalidFunctionException If the user input has missing arguments.
     */
    public static String[] parseTagInput(String userInput) throws InvalidFunctionException {
        try {
            String tagArguments = userInput.split(" ", 2)[1].trim();
            String[] parsedTagArguments = tagArguments.split(" ", 2);
            String taskId = parsedTagArguments[0];
            String tag = parsedTagArguments[1].trim();
            return new String[]{taskId, tag};
        } catch (ArrayIndexOutOfBoundsException ex) {
            String error = "Your tag command has missing arguments";
            throw new InvalidFunctionException(error);
        }
    }
}
