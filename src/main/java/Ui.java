import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Ui handles interactions with the user.
 */
public class Ui {
	private static final String INDENT = "    ";
	private static final String LINE = "____________________________________________________________";
	private final Scanner sc;

	public Ui() {
		this.sc = new Scanner(System.in);
	}

	public static String formatDate(Date date) {
		return (new SimpleDateFormat("MMM d yyyy")).format(date);
	}

	public void showWelcome() {
		formatResponse("Hello! I'm Duke", "What can I do for you?");
	}

	public void showGoodbye(){
		formatResponse("Bye. Hope to see you again soon!");
	}

	public String readCommand() {
		return sc.nextLine();
	}

	/**
	 * Each feedback message from Duke is properly formatted, e.g. enclosed in two lines
	 *
	 * @param response ArrayList of feedback messages.
	 */
	public static void formatResponse(ArrayList<String> response) {
		System.out.println(INDENT + LINE);
		for (String resp: response) {
			System.out.println(INDENT + " " + resp);
		}
		System.out.println(INDENT + LINE);
		System.out.println();
	}

	/**
	 * Overloaded formtResponse, takes in a variable number of strings, puts them in a ArrayList and passes it to the
	 * other formatResponse method.
	 *
	 * @param response Variable number of feedback messages.
	 */
	public static void formatResponse(String ...response) {
		ArrayList<String> lst = new ArrayList<>();
		for (String resp: response) {
			lst.add(resp);
		}
		formatResponse(lst);
	}

	public String formatListCount(TaskList tasks) {
		return "Now you have " + tasks.getCount() + " task" + (tasks.getCount() == 1 ? "" : "s") + " in the list.";
	}

	/**
	 * Displays all the tasks, based on date if date is not null.
	 *
	 * @param tasks Tasks to display.
	 * @param date Date to filter tasks by.
	 */
	public void formatList(TaskList tasks, Date date) {
		ArrayList<String> lst = tasks.toString(date);
		if (date == null) {
			lst.add(0, "Here are the tasks in your list:");
		} else {
			lst.add(0,
					"Here are the tasks in your list that occur on " + formatDate(date) + ":");
		}
		formatResponse(lst);
	}

	public void formatDoneTask(Task task) {
		formatResponse("Nice! I've marked this task as done:", INDENT + task.toString());
	}

	public void formatDeletedTask(Task task, TaskList taskList) {
		formatResponse("Noted. I've removed this task: ", INDENT + task.toString(), formatListCount(taskList));
	}

	public void formatAddTask(Task task, TaskList taskList) {
		formatResponse("Got it. I've added this task: ", INDENT + task.toString(), formatListCount(taskList));
	}

}
