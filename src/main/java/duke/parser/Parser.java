package duke.parser;

import duke.exceptions.InvalidRequestException;
import duke.exceptions.InvalidTodoException;
import duke.exceptions.InvalidDeadlineException;
import duke.exceptions.InvalidEventException;
import duke.exceptions.InvalidKeyException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Interpret the command and do the instruction.
 */
public class Parser {

    private static final String EMPTY_DELETE_MESSAGE = "Please tell me which task you want "
            + "to delete!";
    private static final String EMPTY_DONE_MESSAGE = "Please tell me which task you want "
            + "to be marked as done.";
    private static final String EMPTY_FIND_MESSAGE = "Please tell me the word you want "
            + "to find!";
    private static final String EMPTY_INPUT_MESSAGE = "OOPS!!! I could not help you since " +
            "+ the command is empty!";
    private static final String EMPTY_TODO_NAME_MESSAGE = "Please tell me the name "
            + "of the todo task!";
    private static final String EXCESSIVE_COMMAND_MESSAGE = "Sorry! I can only handle one request at "
            + "a time!";
    private static final String INVALID_EVENT_INFO_MESSAGE = "Please tell me the name and time period"
            + " of the event task!";
    private static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Please tell me both the name and"
            + " the time due of the deadline task in the correct yyyy-mm-dd form! "
            + "Don't forget to include the time by using /by.";
    private static final String INVALID_INDEX_MESSAGE = "OOPS!!! The task you are looking at "
            + "does not exist! Please try another one.";
    private static final String INVALID_COMMAND_MESSAGE = "I cannot understand your command! "
            + "Please ensure your command follows the rules.";
    public static TaskList taskList;
    private static boolean canClose = false;

    /**
     * Sets taskList for Parser.
     *
     * @param list TaskList that manages tasks.
     */
    public static void setTaskList(TaskList list) {
        taskList = list;
    }

    /**
     * Processes a command sentence.
     *
     * @param command The command sentence to be interpreted.
     * @return The response message.
     * @throws Exception Throws Exception if the command is not following the correct format.
     */
    public static String parseCommand(String command) throws Exception {
        try {
            String[] wordArray = command.split(" ");
            int numberOfWords = wordArray.length;
            if (numberOfWords == 0) {
                throw new InvalidRequestException(EMPTY_INPUT_MESSAGE);
            }
            if (command.equals("bye")) {
                return parseExit();
            }
            if (command.equals("list")) {
                return parseList();
            }
            if(command.equals("hi")) {
                return parseGreeting();
            }
            switch (wordArray[0]) {
                case "done":
                    return parseMarkAsDone(wordArray);
                case "delete":
                    return parseDelete(wordArray);
                case "find":
                    return parseFind(wordArray);
                case "todo":
                    return parseTodo(command);
                case "deadline":
                    return parseDeadline(command);
                case "event":
                    return parseEvent(command);
                default:
                    throw new InvalidRequestException(INVALID_COMMAND_MESSAGE);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Processes the list command.
     *
     * @return The response message.
     */
    public static String parseList() {
        return taskList.printTaskList();
    }

    /**
     * Processes the bye command.
     *
     * @return The response message.
     */
    public static String parseExit() {
        canClose = true;
        return "Bye!";
    }

    /**
     * Processes the hi command.
     *
     * @return The response message.
     */
    public static String parseGreeting() {
        return "Hi!";
    }

    /**
     * Processes the done command.
     *
     * @param wordArray The word array of the command.
     * @return The response message.
     */
    public static String parseMarkAsDone(String[] wordArray) throws Exception {
        int numberOfWords = wordArray.length;

        if (numberOfWords == 1) {
            throw new InvalidRequestException(EMPTY_DONE_MESSAGE);
        }
        if (numberOfWords > 2) {
            throw new InvalidRequestException(EXCESSIVE_COMMAND_MESSAGE);
        }

        Integer index = Integer.parseInt(wordArray[1]);

        if (taskList.findListSize() < index) {
            throw new InvalidKeyException(INVALID_INDEX_MESSAGE);
        }
        if (index <= 0) {
            throw new InvalidKeyException(INVALID_INDEX_MESSAGE);
        }

        return taskList.markAsDone(index);
    }

    /**
     * Processes the delete command.
     *
     * @param wordArray The word array of command.
     * @return The response message.
     */
    public static String parseDelete(String[] wordArray) throws Exception {
        int numberOfWords = wordArray.length;

        if (numberOfWords == 1) {
            throw new InvalidRequestException(EMPTY_DELETE_MESSAGE);
        }

        if (numberOfWords > 2) {
            throw new InvalidRequestException(EXCESSIVE_COMMAND_MESSAGE);
        }

        Integer index = Integer.parseInt(wordArray[1]);

        if (taskList.findListSize() < index) {
            throw new InvalidKeyException(INVALID_INDEX_MESSAGE);
        }
        if (index <= 0) {
            throw new InvalidKeyException(INVALID_INDEX_MESSAGE);
        }

        return taskList.deleteTask(index);
    }

    /**
     * Processes the find command.
     *
     * @param wordArray The word array of the input command.
     * @return The response message.
     */
    public static String parseFind(String[] wordArray) throws Exception {
        int numberOfWords = wordArray.length;

        if (numberOfWords == 1) {
            throw new InvalidRequestException(EMPTY_FIND_MESSAGE);
        }
        if (wordArray.length > 2) {
            throw new InvalidRequestException(EXCESSIVE_COMMAND_MESSAGE);
        }

        return taskList.findTask(wordArray[1]);
    }

    /**
     * Processes the todo command.
     *
     * @param command The command string.
     * @return The response message.
     */
    public static String parseTodo(String command) throws Exception {
        Todo todo;
        String[] wordArray = command.split(" ");
        int numberOfWords = wordArray.length;
        if (numberOfWords == 1) {
            throw new InvalidTodoException(EMPTY_TODO_NAME_MESSAGE);
        }
        String name = command.split(" ", 2)[1];
        todo = new Todo(name);
        return taskList.addTask(todo);
    }

    /**
     * Processes the deadline command.
     *
     * @param command The command string.
     * @return The response message.
     */
    public static String parseDeadline(String command) throws Exception {
        String[] wordArray = command.split(" ");

        int numberOfWords = wordArray.length;

        if (numberOfWords == 1) {
            throw new InvalidDeadlineException(INVALID_DEADLINE_FORMAT_MESSAGE);
        }

        String content = command.split(" ", 2)[1];

        if (content.split(" /by ").length < 2) {
            throw new InvalidDeadlineException(INVALID_DEADLINE_FORMAT_MESSAGE);
        }
        Deadline deadline;

        String name = content.split(" /by ")[0];

        String dueDate = content.split(" /by ")[1];

        TimeConverter timeConverter = new TimeConverter();

        String formattedDueDate = timeConverter.convertTime(dueDate);

        deadline = new Deadline(name, formattedDueDate);

        return taskList.addTask(deadline);
    }

    /**
     * Processes the event command.
     *
     * @param command The command string.
     * @return The cresponse message.
     */
    public static String parseEvent(String command) throws Exception {
        String[] wordArray = command.split(" ");

        Event event;

        int numberOfWords = wordArray.length;

        if (numberOfWords == 1) {
            throw new InvalidEventException(INVALID_EVENT_INFO_MESSAGE);
        }

        String content = command.split(" ", 2)[1];

        if (content.split(" /at ").length < 2) {
            throw new InvalidEventException(INVALID_EVENT_INFO_MESSAGE);
        }

        String name = content.split(" /at ")[0];

        String timePeriod = content.split(" /at ")[1];

        event = new Event(name, timePeriod);

        return taskList.addTask(event);

    }

    /**
     * Checks whether the program can be closed or not.
     *
     * @return Whether the program can close or not.
     */
    public static boolean canClose() {
        return canClose;
    }

}
