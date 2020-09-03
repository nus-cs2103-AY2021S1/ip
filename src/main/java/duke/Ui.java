package duke;

import duke.Task;
import duke.TaskList;
/**
 * Ui deals with interactions with the user.
 */
public class Ui {
	public static void showLine(String msg) {
		System.out.println(msg);
	}

	public static String bye() {
		return "See you again!";
	}

	public static String addTask(Task task, TaskList taskList) {
		return "Got it. I've added this task:\n" + task.toString() + "\nNow you have "
				+ taskList.size() + " tasks in the list.";
	}

	public static String getTasks(TaskList taskList) {
		int num = 1;
		String res = "Here are the tasks in your list:\n";
		for (Task task: taskList.getList()) {
			res += (num + "." + task.toString() + "\n");
			num++;
		}
	}
}