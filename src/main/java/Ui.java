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

	public void formatList(TaskList taskList, Date date) {
		ArrayList<String> lst = new ArrayList<>();
		if (date == null) {
			lst.add("Here are the tasks in your list:");
		} else {
			lst.add("Here are the tasks in your list that occur on " + (new SimpleDateFormat("MMM d yyyy")).format(date) + ":");
		}
		int i = 1;
		for (Task task: taskList.getTasks()) {
			if (date == null || task.isOccuringOn(date)) {
				lst.add((i++) + ". " + task.toString());
			}
		}
		formatResponse(lst);
	}

	public static void formatDoneTask(Task task) {
		formatResponse("Nice! I've marked this task as done:", INDENT + task.toString());
	}

	public static void formatDeletedTask(Task task, TaskList taskList) {
		formatResponse("Noted. I've removed this task: ", INDENT + task.toString(), taskList.toString());
	}

	public static void formatAddTask(Task task, TaskList taskList) {
		formatResponse("Got it. I've added this task: ", INDENT + task.toString(), taskList.toString());
	}

}
