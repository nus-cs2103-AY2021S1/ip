package duke;

import java.time.format.FormatStyle;

public class Parser {

    /**
     * Constructor of Parser.
     */
    public Parser() {}


    /**
     * Returns Formatted datetime of a task.
     * Returns 'improperDateTime' if datetime input cannot be processed properly.
     *
     * @param dateTime  User input of date and time of a Task object (specifically Deadline/Event).
     * @returns  Processed datetime of a Deadline or Event object.
     */
    private String dateTimeParser(String commandType, String dateTime) {
        try {
            DateTimeConverter dtc = new DateTimeConverter(FormatStyle.MEDIUM, FormatStyle.SHORT);
            return dtc.processTime(commandType, dateTime);
        } catch (Exception e) {
            return "improperDateTime";
        }
    }


    /**
     * Returns Specific information of a Task object.
     *
     * @param taskLine  Single line of information of a Task object.
     * @returns  Specific information of a Task contained in an array of Strings.
     */
    public String[] localFileTaskParser(String taskLine) {
        assert (!taskLine.isBlank());
        String[] taskTypeAndContent = taskLine.split(SpecialFormat.SPLIT_NOTN, 2);
        String taskType = taskTypeAndContent[0];
        String taskContent = taskTypeAndContent[1];
        String[] taskDetails;

        if (taskType.equals("T")) {
            taskDetails = taskContent.split(SpecialFormat.SPLIT_NOTN, 2);
            return new String[] {taskType, taskDetails[0], taskDetails[1]};
        } else {    // if taskType has value of "E" or "D", representing Event or Deadline object
            taskDetails = taskContent.split(SpecialFormat.SPLIT_NOTN, 3);

            // taskDetails[0] isDone, taskDetails[1] taskAction, taskDetails[2] datetime
            return new String[] {taskType, taskDetails[0], taskDetails[1], taskDetails[2]};
        }
    }


    /**
     * Returns Specific information of a Command in Segments.
     * Returns Specific information of an exception in Segments if exceptions caught.
     *
     * @param input  User input of command.
     * @returns  Specific information of a Command in the form of String array.
     */
    public String[] commandParser(String input) {
        String cleanInput = input.trim();

        if (!cleanInput.contains(" ")) {   // single word command
            return simpleCommandParser(cleanInput);
        }

        return complexCommandParser(cleanInput);
    }


    /**
     * Returns Specific information of a single-word Command in Segments.
     * Returns Specific information of an exception in Segments if exceptions caught.
     *
     * @param cleanInput  cleaned User input of command.
     * @returns  Specific information of a simple Command in the form of String array.
     */
    public String[] simpleCommandParser(String cleanInput) {
        switch (cleanInput) {
            case "bye":
            case "hello":
            case "help":
            case "list":
            case "archive":
            case "listArchive":
                return new String[]{cleanInput};
            default:
                return parseException(cleanInput);
        }

    }


    /**
     * Returns Specific information of a multi-word Command in Segments.
     * Returns Specific information of an exception in Segments if exceptions caught.
     *
     * @param cleanInput  cleaned User input of command.
     * @returns  Specific information of a complex Command in the form of String array.
     */
    public String[] complexCommandParser(String cleanInput) {
        String[] inputSplitArr = cleanInput.split(" ", 2);
        String commandType = inputSplitArr[0];
        String taskContent = inputSplitArr[1];

        switch (commandType) {
            case "find":
                return parseFind(taskContent);
            case "done":
            case "delete":
                return parseModifications(commandType, taskContent);
            case "loadArchive":
                return parseLoadArchive(taskContent);
            case "binArchive":
                return parseBinArchive(taskContent);
            case "todo":
            case "event":
            case "deadline":
                return parseNewEvent(commandType, taskContent);
            default:
                return new String[] {"exception", "no_meaning"};
        }

    }


    /**
     * Returns Specific information of a 'read' command in Segments.
     *
     * @param fileName  User input of index of the archive file to load.
     * @returns  Specific information of 'read' command.
     */
    private String[] parseLoadArchive(String fileName) {
        return new String[] {"loadArchive", fileName};
    }


    /**
     * Returns Specific information of a 'bin' command in Segments.
     *
     * @param fileName  User input of index of the archive file to delete.
     * @returns  Specific information of 'bin' command.
     */
    private String[] parseBinArchive(String fileName) {
        return new String[] {"binArchive", fileName};
    }


    /**
     * Returns Specific information of a 'find' command in Segments.
     *
     * @param keyword  User input of keyword to search for.
     * @returns  Specific information of a find command in the form of String array.
     */
    private String[] parseFind(String keyword) {
        return new String[] {"find", keyword};
    }


    /**
     * Returns Specific information of a 'done' or 'delete' command in Segments.
     *
     * @param commandType  type of command, i.e. 'done' or 'delete'.
     * @param actionNumber  index of task to be modified.
     * @returns  Specific information of this modification command in the form of String array.
     */
    private String[] parseModifications(String commandType, String actionNumber) {
        return new String[] {commandType, actionNumber};
    }


    /**
     * Returns Specific information of a add-new-event command in Segments.
     *
     * @param commandType  type of command, i.e. 'todo' or 'event' or 'deadline'.
     * @param taskContent  content of task to be added.
     * @returns  Specific information of 'todo' or 'event' or 'deadline' command in String array.
     */
    private String[] parseNewEvent(String commandType, String taskContent) {
        if (commandType.equals("todo")) {
            return parseTodo(taskContent);
        } else {
            return parseEventAndDeadline(commandType, taskContent);
        }

    }


    /**
     * Returns Specific information of a 'todo' command in Segments.
     *
     * @param taskContent  content of task to be added.
     * @returns  Specific information of 'todo' command in String array.
     */
    private String[] parseTodo(String taskContent) {
        return new String[] {"todo", taskContent};
    }


    /**
     * Returns Specific information of a 'event' or 'deadline' command in Segments.
     * Returns Specific information of an exception in Segments if exceptions caught.
     *
     * @param commandType  type of command, i.e. 'event' or 'deadline'.
     * @param taskContent  content of task to be added.
     * @returns  Specific information of 'event' or 'deadline' command in String array.
     */
    private String[] parseEventAndDeadline(String commandType, String taskContent) {
        try {
            String[] taskDetails = taskContent.split(
                    commandType.equals("event") ? " /at " : " /by ", 2);

            String taskAction = taskDetails[0];
            String taskDateTime = taskDetails[1];

            String dateTime = dateTimeParser(commandType, taskDateTime);

            if (dateTime.equals("improperDateTime")) {
                return new String[]{"exception", "improperDateTime"};
            }

            return new String[]{commandType, taskAction, dateTime};
        } catch (Exception ex) {
            return parseException(commandType);
        }

    }


    /**
     * Returns Specific information of an exception in Segments.
     *
     * @param type  type of exception to be processed.
     * @returns  Specific information of exception in String array.
     */
    private String[] parseException(String type) {
        switch (type) {
        case "done":
        case "delete":
            return new String[] {"exception", "empty_illegal"};
        case "find":
        case "todo":
        case "event":
        case "deadline":
            return new String[] {"exception", type};
        default:
            return new String[] {"exception", "no_meaning"};
        }

    }

}
