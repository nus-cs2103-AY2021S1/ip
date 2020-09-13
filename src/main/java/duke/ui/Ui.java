package duke.ui;

import static duke.util.Keyword.DISPLAY_TASKS_MESSAGE;
import static duke.util.Keyword.EMPTY_STRING_ERROR;
import static duke.util.Keyword.EMPTY_TASK_LIST_MESSAGE;
import static duke.util.Keyword.FOUR_SPACES;
import static duke.util.Keyword.GOODBYE_MESSAGE;
import static duke.util.Keyword.LINE_SEPARATOR;
import static duke.util.Keyword.NO_MATCHING_TASKS_MESSAGE;
import static duke.util.Keyword.NUM_FORMATTER;
import static duke.util.Keyword.NUM_OF_TASKS_MESSAGE;
import static duke.util.Keyword.TASK_ADDED_MESSAGE;
import static duke.util.Keyword.TASK_DELETED_MESSAGE;
import static duke.util.Keyword.TASK_MARKED_MESSAGE;
import static duke.util.Keyword.WELCOME_MESSAGE_ONE;
import static duke.util.Keyword.WELCOME_MESSAGE_TWO;

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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length - 1; i++) {
            sb.append(args[i]).append(LINE_SEPARATOR);
        }
        sb.append(args[args.length - 1]);
        assert (sb.length() > 0) : EMPTY_STRING_ERROR;
        return sb.toString();
    }

    /**
     * Prints out the list of commands.
     *
     * @param array Input array.
     * @param header Header title.
     * @return Numbered list of commands.
     */
    public String printNumberedArray(Object[] array, String header) {
        int numOfCommands = array.length;
        StringBuilder str1 = new StringBuilder();
        str1.append(header).append(LINE_SEPARATOR);
        for (int i = 1; i < numOfCommands; i++) {
            String s = String.format(NUM_FORMATTER, i, array[i - 1]);
            str1.append(s).append(LINE_SEPARATOR);
        }
        str1.append(String.format(NUM_FORMATTER, numOfCommands, array[numOfCommands - 1]));
        return str1.toString();
    }

    /**
     * Greets the user upon starting the program.
     */
    public static String greetings() {
        return stringFormatter(WELCOME_MESSAGE_ONE, WELCOME_MESSAGE_TWO);
    }

    /**
     * Retrieves the goodbye message.
     */
    public String goodbye() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Prints task done message.
     *
     * @param current Input task.
     */
    public String markTaskAsDone(Task current) {
        return stringFormatter(TASK_MARKED_MESSAGE, FOUR_SPACES + current);
    }

    /**
     * Prints the deletion success message.
     *
     * @param current Current task.
     * @param size Size of task list.
     */
    public String deleteTask(Task current, int size) {
        return stringFormatter(TASK_DELETED_MESSAGE, FOUR_SPACES + current, String.format(NUM_OF_TASKS_MESSAGE, size));
    }

    /**
     * Prints the add task message.
     *
     * @param newTask New task added.
     * @param size Size of task list.
     */
    public String addTask(Task newTask, int size) {
        return stringFormatter(TASK_ADDED_MESSAGE, FOUR_SPACES + newTask, String.format(NUM_OF_TASKS_MESSAGE, size));
    }

    /**
     * Prints the empty task list message.
     */
    public String emptyTaskList() {
        return EMPTY_TASK_LIST_MESSAGE;
    }

    /**
     * Prints the task list to the user.
     *
     * @param tasks Task list.
     * @param extra Extra word to add in, if any.
     */
    public String showTaskList(TaskList tasks, String extra) {
        String header = String.format(DISPLAY_TASKS_MESSAGE, extra);
        return printNumberedArray(tasks.getTasks().toArray(), header);
    }

    /**
     * Prints the no matching tasks found message.printNumberedArray
     *
     * @param queryWord Word use to query task list.
     */
    public String emptyFind(String queryWord) {
        return String.format(NO_MATCHING_TASKS_MESSAGE, queryWord);
    }

}
