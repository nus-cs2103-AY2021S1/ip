import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Encapsulates a parser class, supports the function to parse the user input into a command.
 */
public class Parser {

    final static String BYE = "bye";
    final static String LIST = "list";
    final static String DONE = "done";
    final static String DELETE = "delete";
    final static String FIND = "find";
    final static String TODO = "todo";
    final static String DEADLINE = "deadline";
    final static String EVENT = "event";


    /**
     * Returns a list of task indexes for a done or delete user input.
     *
     * @param input       user input.
     * @param commandType type of command, either a done or delete.
     * @return an arrayList of integer representing the task indexes.
     * @throws DukeException if input has an invalid format.
     */
    private static ArrayList<Integer> getTaskIndexes(String input, String commandType) throws DukeException {

        final int TASK_INDEX_POSITION = commandType.equals(DONE)
                ? 5
                : commandType.equals(DELETE)
                ? 7
                : 0;
        assert TASK_INDEX_POSITION != 0 : "Command type should be done or delete.";

        if (input.equals(commandType) || input.equals(commandType + " ")) {
            throw new InvalidTaskIndexException(input);
        } else if (input.startsWith(commandType + " ") && input.length() > TASK_INDEX_POSITION) {
            try {
                return parseIndexString(input.substring(TASK_INDEX_POSITION), new ArrayList<>());
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException(input);
            }
        } else {
            throw new DukeException();
        }
    }

    /**
     * Parses the given string of indexes into an arrayList of indexes.
     * 
     * @param taskIndexString string containing indexes separated by spaces.
     * @param taskIndexes arrayList containing the indexes.
     * @return an arrayList of integers.
     * @throws NumberFormatException if input string has an invalid format.
     */
    private static ArrayList<Integer> parseIndexString(
            String taskIndexString, ArrayList<Integer> taskIndexes) throws NumberFormatException {
        
        int firstIndexEndPosition = taskIndexString.indexOf(" ");
        if (firstIndexEndPosition < 0) {
            int firstIndex = Integer.parseInt(taskIndexString);
            taskIndexes.add(firstIndex);
            return taskIndexes;
        } else {
            String firstIndexString = taskIndexString.substring(0, firstIndexEndPosition);
            int firstIndex = Integer.parseInt(firstIndexString);
            taskIndexes.add(firstIndex);
            String remainingIndexString = taskIndexString.substring(firstIndexEndPosition + 1);
            return parseIndexString(remainingIndexString, taskIndexes);
        }
    }

    /**
     * Takes in a user input starting with 'done' and returns a done command.
     *
     * @param input user input that starts with 'done'.
     * @return a done command.
     * @throws DukeException if user input has an invalid format.
     */
    private static DoneCommand done(String input) throws DukeException {
        return new DoneCommand(getTaskIndexes(input, DONE));
    }

    /**
     * Takes in a user input starting with 'delete' and returns a delete command.
     *
     * @param input user input that starts with 'delete'.
     * @return a delete command.
     * @throws DukeException if user input has an invalid format.
     */
    private static DeleteCommand delete(String input) throws DukeException {
        return new DeleteCommand(getTaskIndexes(input, DELETE));
    }

    /**
     * Takes in a user input starting with 'find' and returns a find command.
     *
     * @param input user input that starts with 'find'.
     * @return a find command.
     * @throws DukeException if user input has an invalid format.
     */
    private static FindCommand find(String input) throws DukeException {
        final int KEYWORD_INDEX = 5;

        if (input.equals(FIND) || input.equals(FIND + " ")) {
            throw new EmptyDescriptionException(FIND);
        } else if (input.startsWith(FIND + " ") && input.length() > KEYWORD_INDEX) {
            return new FindCommand(input.substring(KEYWORD_INDEX));
        } else {
            throw new DukeException();
        }
    }

    /**
     * Takes in a user input starting with 'todo' and returns an add command that adds a new todo.
     *
     * @param input user input that starts with 'todo'.
     * @return an add command.
     * @throws DukeException if user input has an invalid format.
     */
    private static AddCommand newTodo(String input) throws DukeException {
        final int DESCRIPTION_INDEX = 5;

        if (input.equals(TODO) || input.equals(TODO + " ")) {
            throw new EmptyDescriptionException(TODO);
        } else if (input.startsWith(TODO + " ") && input.length() > DESCRIPTION_INDEX) {
            Todo newTodo = new Todo(input.substring(DESCRIPTION_INDEX));
            return new AddCommand(newTodo);
        } else {
            throw new DukeException();
        }
    }

    /**
     * Creates an add command for tasks with timings. Takes in a user input starting with 'deadline' or
     * 'event' and returns an add command for those tasks.
     *
     * @param input user input that starts with 'deadline' or 'event'.
     * @return an add command.
     * @throws DukeException if user input has an invalid format.
     */
    private static AddCommand newTaskWithTiming(String input, String taskType) throws DukeException {
        final int DESCRIPTION_INDEX = taskType.equals(DEADLINE)
                ? 9
                : taskType.equals(EVENT)
                ? 6
                : 0;
        final int TIME_INDEX = 5;
        final String TIME_DESCRIPTOR = taskType.equals(DEADLINE)
                ? " /by "
                : taskType.equals(EVENT)
                ? " /at "
                : "";
        assert DESCRIPTION_INDEX != 0 : "Task type should deadline or event.";

        if (input.equals(taskType) || input.equals(taskType + " ")) {
            throw new EmptyDescriptionException(taskType);
        } else if (input.startsWith(taskType + " ") && input.length() > DESCRIPTION_INDEX) {
            if (!input.contains(TIME_DESCRIPTOR)) {
                throw new InvalidDateTimeException(taskType);
            } else {
                int index = input.indexOf(TIME_DESCRIPTOR);
                String description = input.substring(DESCRIPTION_INDEX, index);
                String date = input.substring(index + TIME_INDEX);
                try {
                    LocalDate eventDate = LocalDate.parse(date);
                    Task newTask;
                    if (taskType.equals(DEADLINE)) {
                        newTask = new Deadline(description, eventDate);
                    } else {
                        newTask = new Event(description, eventDate);
                    }
                    return new AddCommand(newTask);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException(taskType);
                }
            }
        } else {
            throw new DukeException();
        }
    }

    /**
     * Takes in a user input starting with 'deadline' and returns an add command.
     *
     * @param input user input that starts with 'deadline'.
     * @return an add command.
     * @throws DukeException if user input has an invalid format.
     */
    private static AddCommand newDeadline(String input) throws DukeException {
        return newTaskWithTiming(input, DEADLINE);
    }

    /**
     * Takes in a user input starting with 'event' and returns an add command.
     *
     * @param input user input that starts with 'event'.
     * @return an add command.
     * @throws DukeException if user input has an invalid format.
     */
    private static AddCommand newEvent(String input) throws DukeException {
        return newTaskWithTiming(input, EVENT);
    }

    /**
     * Parses the user's input and returns the corresponding command.
     *
     * @param input user's input
     * @return a command
     * @throws DukeException if user input has an invalid format.
     */
    static Command parse(String input) throws DukeException {
        if (input.equals(BYE)) {
            return new ExitCommand();
        } else if (input.equals(LIST) || input.equals(LIST + " ")) {
            return new ListCommand();
        } else {
            if (input.startsWith(DONE)) {
                return done(input);
            } else if (input.startsWith(DELETE)) {
                return delete(input);
            } else if (input.startsWith(FIND)) {
                return find(input);
            } else if (input.startsWith(TODO)) {
                return newTodo(input);
            } else if (input.startsWith(DEADLINE)) {
                return newDeadline(input);
            } else if (input.startsWith(EVENT)) {
                return newEvent(input);
            } else {
                throw new DukeException();
            }
        }
    }

}
