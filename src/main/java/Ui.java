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
	private static final ArrayList<String> WELCOME_MSG = new ArrayList<>(Arrays.asList("Hello! I'm Duke", "What can I" +
			" do for you?"));
	private static final String GOODBYE_MSG = "Bye. Hope to see you again soon!";
	private static final String DONE_MSG = "Nice! I've marked this task as done:";
	private static final String DELETED_MSG = "Noted. I've removed this task:";
	private static final String ADD_MSG = "Got it. I've added this task:";

    public static String formatDate(Date date) {
        return (new SimpleDateFormat("MMM d yyyy")).format(date);
    }

	/**
	 * Each feedback message from Duke is properly formatted, e.g. enclosed in two lines.
	 *
	 * @param response ArrayList of feedback messages.
	 * @return String of formatted responses.
	 */
	public static String formatResponse(ArrayList<String> response) {
		String indentLine = INDENT + LINE + "\n";
		String result = indentLine;
		for (String resp: response) {
			result += INDENT + " " + resp + "\n";
		}
		result += indentLine;
		return result;
	}

	/**
	 * Overloaded formatResponse, takes in a variable number of strings, puts them in a ArrayList and passes it to the
	 * other formatResponse method.
	 *
	 * @param response Variable number of Strings of feedback messages.
	 * @return String of formatted responses.
	 */
	public static String formatResponse(String ... response) {
		ArrayList<String> lst = new ArrayList<>();
		for (String resp: response) {
			lst.add(resp);
		}
		return formatResponse(lst);
	}

	public static String getError(String error) {
		return formatResponse(error);
	}

	public abstract void showError(String error);

	public String getWelcome() {
		return formatResponse(WELCOME_MSG);
	}

	public abstract void showWelcome();

	public String getGoodbye() {
		return formatResponse(GOODBYE_MSG);
	}

	public abstract void showGoodbye();

	public String getListCount(TaskList tasks) {
		return "Now you have " + tasks.getCount() + " task" + (tasks.getCount() == 1 ? "" : "s") + " in the list.";
	}

	/**
	 * Displays all the tasks, based on date if date is not null.
	 *
	 * @param tasks Tasks to display.
	 * @param date Date to filter tasks by.
	 */
	public String getTaskList(TaskList tasks, Date date, String keyWord) {
		ArrayList<String> lst = tasks.toString(date, keyWord);
		lst.add(0, "Here are the " + ((keyWord == null) ? "" : "matching ") + "tasks in your list" + ((date == null) ?
				"" :
				" that occur on " + formatDate(date)) + ":");
		return formatResponse(lst);
	}

	/**
	 * Displays all the tasks, based on date if date is not null.
	 *
	 * @param tasks Tasks to display.
	 * @param date Date to filter tasks by.
	 */
	public abstract void showTaskList(TaskList tasks, Date date, String keyWord);

	public String getDoneTask(Task task) {
		return formatResponse(DONE_MSG, INDENT + task.toString());
	}

	public abstract void showDoneTask(Task task);

	public String getDeletedTask(Task task, TaskList taskList) {
		return formatResponse(DELETED_MSG, INDENT + task.toString(), getListCount(taskList));
	}

	public abstract void showDeletedTask(Task task, TaskList taskList);

	public String getAddTask(Task task, TaskList taskList) {
		return formatResponse(ADD_MSG, INDENT + task.toString(), getListCount(taskList));
	}

	public abstract void showAddTask(Task task, TaskList taskList);
}
