import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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

	public static void formatResponse(ArrayList<String> response) {
		System.out.println(INDENT + LINE);
		for (String resp: response) {
			System.out.println(INDENT + " " + resp);
		}
		System.out.println(INDENT + LINE);
		System.out.println();
	}

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

	public void formatList(TaskList tasks, Date date, String keyWord) {
		ArrayList<String> lst = tasks.toString(date, keyWord);
		lst.add(0, "Here are the " + ((keyWord == null) ? "" : "matching ") + "tasks in your list" + ((date == null) ?
				"" :
				" that occur on " + formatDate(date)) + ":");
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
