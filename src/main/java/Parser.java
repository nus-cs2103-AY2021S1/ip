import java.time.LocalDate;

/**
 * Represents a parser that makes sense of user input.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class Parser {
    private final static int LABEL_LENGTH = 6;
    private final static String BY_STRING = "/by";
    private final static String AT_STRING = "/at";
    private final static String PARSE_ERROR = "Uh oh I can't read the task" +
            " you saved in your local file.";


    private static Deadline createDeadline(String desc, String dateTime,
           boolean isTaskDone, boolean containsTime) throws DukeException {
        if (isTaskDone && containsTime) {
            String[] dateTimeArray = dateTime.split(" ");
            String date = dateTimeArray[0].trim();
            String time = dateTimeArray[1].trim();
            LocalDate dateObject = LocalDate.parse(date);
            return new Deadline(desc, true, dateObject, time);
        } else if (isTaskDone && !containsTime) {
            LocalDate dateObj = LocalDate.parse(dateTime);
            return new Deadline(desc, true, dateObj);
        } else if (!isTaskDone && containsTime) {
            String[] dateTimeArray = dateTime.split(" ");
            String date = dateTimeArray[0].trim();
            String time = dateTimeArray[1].trim();
            LocalDate dateObject = LocalDate.parse(date);
            return new Deadline(desc, dateObject, time);
        } else if (!isTaskDone && !containsTime) {
            LocalDate dateObject = LocalDate.parse(dateTime);
            return new Deadline(desc, dateObject);
        } else { //task is marked as done
            throw new DukeException(PARSE_ERROR);
        }
    }

    private static Event createEvent(String desc, String dateTime,
                        boolean isTaskDone, boolean containsTime) throws DukeException {
        if (isTaskDone && containsTime) {
            String[] dateTimeArray = dateTime.split(" ");
            String date = dateTimeArray[0].trim();
            String time = dateTimeArray[1].trim();
            LocalDate dateObject = LocalDate.parse(date);
            return new Event(desc, dateObject, time);
        } else if (isTaskDone && !containsTime) {
            LocalDate dateObj = LocalDate.parse(dateTime);
            return new Event(desc, dateObj);
        } else if (!isTaskDone && containsTime) {
            String[] dateTimeArray = dateTime.split(" ");
            String date = dateTimeArray[0].trim();
            String time = dateTimeArray[1].trim();
            LocalDate dateObject = LocalDate.parse(date);
            return new Event(desc, true, dateObject, time);
        } else if (!isTaskDone && !containsTime) {
            LocalDate dateObj = LocalDate.parse(dateTime);
            return new Event(desc, true, dateObj);
        } else {
            throw new DukeException(PARSE_ERROR);
        }
    }
    private static Todo parseTodoInput(String taskString) {
        String desc = taskString.substring(LABEL_LENGTH).trim();
        boolean isTaskDone = taskString.startsWith("[T][1]");
        if (isTaskDone) {
            return new Todo(desc, true);
        } else {
            return new Todo(desc);
        }
    }

    private static Deadline parseDeadlineInput(String taskString) throws DukeException {
        String[] descDateTimeArray = taskString.substring(LABEL_LENGTH).split(BY_STRING);
        String desc = descDateTimeArray[0].trim();
        String dateTime = descDateTimeArray[1].trim();

        boolean isTaskDone = taskString.startsWith("[D][1]");
        boolean containsTime = dateTime.contains(" ");

        return createDeadline(desc, dateTime, isTaskDone, containsTime);
    }

    private static Event parseEventInput(String taskString) throws DukeException {
        String[] descDateTimeArray = taskString.substring(LABEL_LENGTH).split(AT_STRING);
        String desc = descDateTimeArray[0].trim();
        String dateTime = descDateTimeArray[1].trim();

        boolean isTaskDone = taskString.startsWith("[E][1]");
        boolean containsTime = dateTime.contains(" ");

        return createEvent(desc, dateTime, isTaskDone, containsTime);
    }

    /**
     * Parses list items from the saved list.
     *
     * @param taskString    The string to be parsed.
     * @param taskList      The current task list.
     * @return TaskList     The updated task list.
     */
    public static TaskList parseTaskFromFile(final String taskString,
                       final TaskList taskList) throws DukeException {
        if (taskString.startsWith("[T]")) {
            Todo todo = parseTodoInput(taskString);
            taskList.add(todo);
        } else if (taskString.startsWith("[D]")) {
            Deadline deadline = parseDeadlineInput(taskString);
            taskList.add(deadline);
        } else if (taskString.startsWith("[E]")){
            Event event = parseEventInput(taskString);
            taskList.add(event);
        } else {
            throw new DukeException(PARSE_ERROR);
        }
        return taskList;
    }

    /**
     * Checks the input and calls the respective parsing functions.
     *
     * @param input     The user input.
     * @return          Whether the user is done using the chatbot.
     * @throws DukeException When the input is not recognized.
     */
    public static Command parse(final String input) throws DukeException {
        if (input.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.toLowerCase().startsWith("done")) {
            return new DoneCommand(input);
        } else if (input.toLowerCase().startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.toLowerCase().startsWith("find")) {
            return new FindCommand(input);
        } else if (input.toLowerCase().startsWith("todo")) {
            return new TodoCommand(input);
        } else if (input.toLowerCase().startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.toLowerCase().startsWith("event")) {
            return new EventCommand(input);
        } else {
            throw new DukeException("Sorry I don't know what that means :(");
        }
    }
}
