import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Ui handles interactions with the user.
 */
public class Ui {
    private static final String INDENT = "    ";
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<String> WELCOME_MSG = new ArrayList<>(Arrays.asList("Hello! I'm Duke", "What can I"
            + " do for you?"));
    private static final ArrayList<String> GOODBYE_MSG = new ArrayList<>(Arrays.asList(
            "Bye. Hope to see you again soon!"));
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
            result += INDENT + INDENT + resp + "\n";
        }
        result += indentLine;
        return result;
    }

    /**
     * Formats each feedback message, e.g. enclosed in two lines.
     *
     * @param response ArrayList of feedback messages.
     * @return String of formatted responses.
     */
    public static String formatResponse(String response) {
        return formatResponse(new ArrayList<>(Arrays.asList(response)));
    }

    /**
     * Formats each feedback message.
     *
     * @param response ArrayList of feedback messages.
     * @return String of formatted responses.
     */
    public static String formatMultiLine(ArrayList<String> response) {
        String result = "";
        for (String resp : response) {
            result += resp + "\n";
        }
        return result;
    }

    /**
     * Returns formatted welcome message.
     *
     * @return ArrayList welcome message.
     */
    public static ArrayList<String> getWelcome() {
        return WELCOME_MSG;
    }

    /**
     * Returns formatted goodbye message.
     *
     * @return String goodbye message.
     */
    public static ArrayList<String> getGoodbye() {
        return GOODBYE_MSG;
    }

    /**
     * Returns a ArrayList String with count of tasks.
     *
     * @param tasks TaskList to count number of tasks from.
     * @return String with count of tasks.
     */
    public static String getListCount(TaskList tasks) {
        assert tasks == null : "TaskList should not be null";
        return "Now you have " + tasks.getCount() + " task" + (tasks.getCount() == 1 ? "" : "s") + " in the list.";
    }

    /**
     * Returns a ArrayList String containing details of all the tasks that pass date and keyWord criteria.
     *
     * @param tasks   Tasks to filter from.
     * @param date    Date to filter tasks by.
     * @param keyWord Keyword to filter tasks by.
     * @return ArrayList containing details of tasks that fulfil the criteria.
     */
    public static ArrayList<String> getTaskList(TaskList tasks, Date date, String keyWord) {
        assert tasks == null : "TaskList should not be null";
        ArrayList<String> lst = new ArrayList<>();
        int idx = 1;
        for (Task task : tasks.getTasks()) {
            if (task.fulfilCriteria(date, keyWord)) {
                lst.add((idx++) + ". " + task.toString());
            }
        }
        lst.add(0, "Here are the " + ((keyWord == null) ? "" : "matching ") + "tasks in your list" + ((date == null)
                ? "" : " that occur on " + formatDate(date)) + ":");
        return lst;
    }

    /**
     * Returns a ArrayList String of a feedback message that task is marked as done.
     *
     * @param task Task that is done.
     * @return ArrayList feedback message that task is marked as done.
     */
    public static ArrayList<String> getDoneTask(Task task) {
        assert task == null : "Task should not be null";
        ArrayList<String> lst = new ArrayList<>();
        lst.add(DONE_MSG);
        lst.add(INDENT + task.toString());
        return lst;
    }

    /**
     * Returns a ArrayList String of a feedback message that task is deleted.
     *
     * @param task     Task that is deleted.
     * @param taskList
     * @return ArrayList
     */
    public static ArrayList<String> getDeletedTask(Task task, TaskList taskList) {
        assert task == null : "Task should not be null";
        assert taskList == null : "TaskList should not be null";
        ArrayList<String> lst = new ArrayList<>();
        lst.add(DELETED_MSG);
        lst.add(INDENT + task.toString());
        lst.add(getListCount(taskList));
        return lst;
    }

    /**
     * Returns a ArrayList String of a feedback message that task is added to taskList.
     *
     * @param task     Task that is added.
     * @param taskList TaskList
     * @return ArrayList feedback message that task is added to taskList.
     */
    public static ArrayList<String> getAddTask(Task task, TaskList taskList) {
        assert task == null : "Task should not be null";
        assert taskList == null : "TaskList should not be null";
        ArrayList<String> lst = new ArrayList<>();
        lst.add(ADD_MSG);
        lst.add(INDENT + task.toString());
        lst.add(getListCount(taskList));
        return lst;
    }

}
