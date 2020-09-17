package duke.ui;

import static duke.util.Keyword.DISPLAY_TASKS_MESSAGE;
import static duke.util.Keyword.EMPTY_STRING_ERROR;
import static duke.util.Keyword.EMPTY_TASK_LIST_MESSAGE;
import static duke.util.Keyword.FILE_CREATION_ERR;
import static duke.util.Keyword.FOUR_SPACES;
import static duke.util.Keyword.GOODBYE_MESSAGE;
import static duke.util.Keyword.LINE_SEPARATOR;
import static duke.util.Keyword.NO_MATCHING_TASKS_MESSAGE;
import static duke.util.Keyword.NUM_FORMATTER;
import static duke.util.Keyword.NUM_OF_TASKS_MESSAGE;
import static duke.util.Keyword.SORT_PROMPT_MESSAGE;
import static duke.util.Keyword.SORT_SUCCESS_MESSAGE;
import static duke.util.Keyword.TASK_ADDED_MESSAGE;
import static duke.util.Keyword.TASK_DELETED_MESSAGE;
import static duke.util.Keyword.TASK_MARKED_MESSAGE;

import java.util.stream.IntStream;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Handles the program interactions with user.
 */
public class Ui {

    /**
     * Concatenates the strings, separating each string with a line separator.
     *
     * @param args Strings to concatenate.
     * @return Concatenated strings.
     */
    public static String stringFormatter(String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        int numOfStrings = args.length;
        IntStream.range(0, numOfStrings)
                .forEach(i -> appendWithNewLine(stringBuilder, args[i]));
        assert (stringBuilder.length() > 0) : EMPTY_STRING_ERROR;
        return stringBuilder.toString();
    }

    /**
     * Displays the list of commands.
     *
     * @param array Input array.
     * @param header Header title.
     * @return Numbered list of objects.
     */
    public String printNumberedArray(Object[] array, String header) {
        int numOfCommands = array.length;
        assert numOfCommands > 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(header).append(LINE_SEPARATOR);
        IntStream.range(0, numOfCommands)
                .forEach(i -> appendWithNewLine(stringBuilder, labelString(array[i], i)));
        return stringBuilder.toString();
    }

    /**
     * Labels the string with the corresponding index.
     *
     * @param string Input string.
     * @param i Index of item.
     * @return Labelled string.
     */
    private String labelString(Object string, int i) {
        return String.format(NUM_FORMATTER, i + 1, string);
    }

    /**
     * Appends the string to the {@code StringBuilder} along with a line separator.
     *
     * @param stringBuilder StringBuilder to store strings.
     * @param string Input string.
     */
    private static void appendWithNewLine(StringBuilder stringBuilder, String string) {
        stringBuilder.append(string).append(LINE_SEPARATOR);
    }

    /**
     * Retrieves the goodbye message.
     *
     * @return Goodbye message to the user.
     */
    public String goodbye() {
        return stringFormatter(GOODBYE_MESSAGE);
    }

    /**
     * Retrieves the successful task done message.
     *
     * @param current Input task.
     * @return Successful task marked as done message.
     */
    public String markTaskAsDone(Task current) {
        return stringFormatter(TASK_MARKED_MESSAGE, FOUR_SPACES + current);
    }

    /**
     * Retrieves the deletion success message.
     *
     * @param current Current task.
     * @param size Size of task list.
     * @return Successful deletion of task message.
     */
    public String deleteTask(Task current, int size) {
        return stringFormatter(TASK_DELETED_MESSAGE, FOUR_SPACES + current, String.format(NUM_OF_TASKS_MESSAGE, size));
    }

    /**
     * Retrieves the add task message.
     *
     * @param newTask New task added.
     * @param size Size of task list.
     * @return Successful addition of task message.
     */
    public String addTask(Task newTask, int size) {
        return stringFormatter(TASK_ADDED_MESSAGE, FOUR_SPACES + newTask, String.format(NUM_OF_TASKS_MESSAGE, size));
    }

    /**
     * Retrieves the empty task list message.
     */
    public String emptyTaskList() {
        return stringFormatter(EMPTY_TASK_LIST_MESSAGE);
    }

    /**
     * Displays the task list to the user.
     *
     * @param taskList Task list.
     * @param extra Extra word to add in, if any.
     * @return Show task list message.
     */
    public String showTaskList(TaskList taskList, String extra) {
        String header = String.format(DISPLAY_TASKS_MESSAGE, extra);
        return printNumberedArray(taskList.getTasks().toArray(), header);
    }

    /**
     * Displays the no matching tasks found message.
     *
     * @param queryWord Word use to query task list.
     * @return No matching tasks found message.
     */
    public String emptyFind(String queryWord) {
        String message = String.format(NO_MATCHING_TASKS_MESSAGE, queryWord);
        return stringFormatter(message);
    }

    /**
     * Displays the sort success message to the user.
     *
     * @return Sort success message.
     */
    public String sortMessage() {
        return stringFormatter(SORT_SUCCESS_MESSAGE, SORT_PROMPT_MESSAGE);
    }

    /**
     * Displays the file creation error to the user.
     */
    public void fileCreationError() {
        System.out.println(FILE_CREATION_ERR);
    }
}
