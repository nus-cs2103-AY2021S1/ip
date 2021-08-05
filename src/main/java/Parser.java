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


    private static Deadline createDeadline(String desc, Tag tag, String dateTime,
           boolean isTaskDone, boolean containsTime) throws DukeException {
        if (containsTime) {
            String[] dateTimeArray = dateTime.split(" ");
            String date = dateTimeArray[0].trim();
            String time = dateTimeArray[1].trim();
            LocalDate dateObject = LocalDate.parse(date);
            return new Deadline(desc, isTaskDone, dateObject, time, tag);
        } else if (!containsTime) {
            LocalDate dateObject = LocalDate.parse(dateTime);
            return new Deadline(desc, isTaskDone, dateObject, "", tag);
        } else {
            throw new DukeException(PARSE_ERROR);
        }
    }

    private static Event createEvent(String desc, Tag tag, String dateTime,
                        boolean isTaskDone, boolean containsTime) throws DukeException {
        if (containsTime) {
            String[] dateTimeArray = dateTime.split(" ");
            String date = dateTimeArray[0].trim();
            String time = dateTimeArray[1].trim();
            LocalDate dateObject = LocalDate.parse(date);
            return new Event(desc, isTaskDone, dateObject, time, tag);
        } else if (!containsTime) {
            LocalDate dateObj = LocalDate.parse(dateTime);
            return new Event(desc, isTaskDone, dateObj, "", tag);
        } else {
            throw new DukeException(PARSE_ERROR);
        }
    }
    private static Todo parseTodoInput(String taskString) {
        String descTag = taskString.substring(LABEL_LENGTH).trim();
        boolean isTaskDone = taskString.startsWith("[T][1]");
        boolean containsTag = descTag.contains("#");
        if (containsTag) {
            String[] descTagArray = descTag.split("#");
            assert descTagArray.length == 2 : "descTagArray should have length of 2";
            String desc = descTagArray[0].trim();
            String tagString = descTagArray[1].trim();
            Tag tag = new Tag(tagString);
            return new Todo(desc, isTaskDone, tag);
        } else {
            return new Todo(descTag, isTaskDone, new Tag());
        }
    }

    private static Deadline parseDeadlineInput(String taskString) throws DukeException {
        String[] descTagDateTimeArray = taskString.substring(LABEL_LENGTH).split(BY_STRING);
        String descTag = descTagDateTimeArray[0].trim();
        String dateTime = descTagDateTimeArray[1].trim();

        boolean isTaskDone = taskString.startsWith("[D][1]");
        boolean containsTime = dateTime.contains(" ");
        boolean containsTag = descTag.contains("#");

        if (containsTag) {
            String[] descTagArray = descTag.split("#");
            assert descTagArray.length == 2 : "descTagArray should have length of 2";
            String desc = descTagArray[0].trim();
            String tagString = descTagArray[1].trim();
            Tag tag = new Tag(tagString);
            return createDeadline(desc, tag, dateTime, isTaskDone, containsTime);
        } else {
            return createDeadline(descTag, new Tag(), dateTime, isTaskDone, containsTime);
        }

    }

    private static Event parseEventInput(String taskString) throws DukeException {
        String[] descTagDateTimeArray = taskString.substring(LABEL_LENGTH).split(AT_STRING);
        String descTag = descTagDateTimeArray[0].trim();
        String dateTime = descTagDateTimeArray[1].trim();

        boolean isTaskDone = taskString.startsWith("[E][1]");
        boolean containsTime = dateTime.contains(" ");
        boolean containsTag = descTag.contains("#");

        if (containsTag) {
            String[] descTagArray = descTag.split("#");
            assert descTagArray.length == 2 : "descTagArray should have length of 2";
            String desc = descTagArray[0].trim();
            String tagString = descTagArray[1].trim();
            Tag tag = new Tag(tagString);
            return createEvent(desc, tag, dateTime, isTaskDone, containsTime);
        } else {
            return createEvent(descTag, new Tag(), dateTime, isTaskDone, containsTime);
        }
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
