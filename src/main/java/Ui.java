import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Ui handles interactions with the user.
 */
public abstract class Ui {
    private static final String INDENT = "    ";
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<String> WELCOME_MSG = new ArrayList<>(Arrays.asList("Hello! I'm Duke", "What can I"
            + " do for you?"));
    private static final String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private static final String DONE_MSG = "Nice! I've marked this task as done:";
    private static final String DELETED_MSG = "Noted. I've removed this task:";
    private static final String ADD_MSG = "Got it. I've added this task:";

    public static String formatDate(Date date) {
        return (new SimpleDateFormat("MMM d yyyy")).format(date);
    }

    /**
     * Formats each feedback message, e.g. enclosed in two lines.
     *
     * @param response ArrayList of feedback messages.
     * @return String of formatted responses.
     */
    public static String formatResponse(ArrayList<String> response) {
        String indentLine = INDENT + LINE + "\n";
        String result = indentLine;
        for (String resp : response) {
            result += INDENT + " " + resp + "\n";
        }
        result += indentLine;
        return result;
    }

    /**
     * Formats each feedback message, e.g. enclosed in two lines.
     * Overloaded formatResponse, takes in a variable number of strings, puts them in a ArrayList and passes it to the
     * other formatResponse method.
     *
     * @param response Variable number of Strings of feedback messages.
     * @return String of formatted responses.
     */
    public static String formatResponse(String... response) {
        ArrayList<String> lst = new ArrayList<>();
        for (String resp : response) {
            lst.add(resp);
        }
        return formatResponse(lst);
    }

    /**
     * Returns formatted error message.
     *
     * @param errorMsg Error message.
     * @return String Formatted error message.
     */
    public static String getError(String errorMsg) {
        return formatResponse(errorMsg);
    }

    /**
     * Displays error message.
     *
     * @param errorMsg Error message to display.
     */
    public abstract void showError(String errorMsg);

    /**
     * Returns formatted welcome message.
     *
     * @return String Formatted welcome message.
     */
    public String getWelcome() {
        return formatResponse(WELCOME_MSG);
    }

    /**
     * Displays welcome message.
     */
    public abstract void showWelcome();

    /**
     * Returns formatted goodbye message.
     *
     * @return String Formatted goodbye message.
     */
    public String getGoodbye() {
        return formatResponse(GOODBYE_MSG);
    }

    /**
     * Displays goodbye message.
     */
    public abstract void showGoodbye();

    /**
     * Returns a String with count of tasks.
     *
     * @param tasks TaskList to count number of tasks from.
     * @return String with count of tasks.
     */
    public String getListCount(TaskList tasks) {
        return "Now you have " + tasks.getCount() + " task" + (tasks.getCount() == 1 ? "" : "s") + " in the list.";
    }

    /**
     * Returns a String containing details of all the tasks that pass date and keyWord criteria.
     *
     * @param tasks   Tasks to filter from.
     * @param date    Date to filter tasks by.
     * @param keyWord Keyword to filter tasks by.
     * @return String containing details of tasks that fulfil the criteria.
     */
    public String getTaskList(TaskList tasks, Date date, String keyWord) {
        ArrayList<String> lst = tasks.toString(date, keyWord);
        lst.add(0, "Here are the " + ((keyWord == null) ? "" : "matching ") + "tasks in your list" + ((date == null)
                ? "" : " that occur on " + formatDate(date)) + ":");
        return formatResponse(lst);
    }

    /**
     * Displays all the tasks that pass date and keyWord criteria.
     *
     * @param tasks   Tasks to filter from.
     * @param date    Date to filter tasks by.
     * @param keyWord Keyword to filter tasks by.
     */
    public abstract void showTaskList(TaskList tasks, Date date, String keyWord);

    /**
     * Returns a String of a feedback message that task is marked as done.
     *
     * @param task Task that is done.
     * @return Feedback message that task is marked as done.
     */
    public String getDoneTask(Task task) {
        return formatResponse(DONE_MSG, INDENT + task.toString());
    }

    /**
     * Displays done Task.
     *
     * @param task Task that is done.
     */
    public abstract void showDoneTask(Task task);

    /**
     * Returns a String of a feedback message that task is deleted.
     *
     * @param task     Task that is deleted.
     * @param taskList
     * @return
     */
    public String getDeletedTask(Task task, TaskList taskList) {
        return formatResponse(DELETED_MSG, INDENT + task.toString(), getListCount(taskList));
    }

    /**
     * Displays deleted Task.
     *
     * @param task     Task that is deleted.
     * @param taskList TaskList.
     */
    public abstract void showDeletedTask(Task task, TaskList taskList);

    /**
     * Returns a String of a feedback message that task is added to taskList.
     *
     * @param task     Task that is added.
     * @param taskList TaskList
     * @return Feedback message that task is added to taskList.
     */
    public String getAddTask(Task task, TaskList taskList) {
        return formatResponse(ADD_MSG, INDENT + task.toString(), getListCount(taskList));
    }

    /**
     * Displays added Task.
     *
     * @param task     Task that is added.
     * @param taskList TaskList
     */
    public abstract void showAddTask(Task task, TaskList taskList);
}
