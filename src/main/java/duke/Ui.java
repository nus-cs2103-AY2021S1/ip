package duke;

import java.util.ArrayList;

/**
 * Represents the interface for Duke to display the chat box output.
 */
public class Ui {
	/**
	 * Prints line divider between user input and Duke response.
	 */
	public void printDivider() {
		System.out.println("          ____________________________________________________________");
	}

	/**
	 * Prints welcome message.
	 */
	public void printWelcome() {
		String logo = " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);
		printDivider();
		printOutput("Hi! I'm Duke \nWhat can I do for you?");
		printDivider();
	}

	/**
	 * Prints the output with indentation.
	 * @param output the ouput to be printed.
	 */
	public void printOutput(String output) {
		String[] splitted = output.split("\n");
		for(String line : splitted) {
			System.out.println("          " + line);
		}
	}

	/**
	 * Prints goodbye message.
	 */
	public void printGoodbye() {
		printOutput("Bye. See you again next time!" );
	}

	/**
	 * Prints confirmation when a new task is added.
	 * @param task the task added.
	 * @param numberOfTask the current number of tasks.
	 */
	public void printAddedTask(Task task, int numberOfTask) {
		printOutput("Got it. I've added this task: ");
		printOutput(task.toString());
		printOutput("Now you have " + numberOfTask + " tasks in the list.");
	}

	/**
	 * Prints the current list of tasks.
	 * @param taskList the lists of task.
	 */
	public void printTaskList(TaskList taskList) {
		ArrayList<Task> tasks = taskList.getTask();
		printOutput("Here are the tasks in your list:");
		for(int index = 0; index < tasks.size(); index++) {
			printOutput((index + 1) + ": " + tasks.get(index));
		}
	}

	/**
	 * Prints confirmation when a task is marked as done.
	 * @param task the task being completed.
	 */
	public void printCompleteTask(Task task) {
		printOutput("Nice! I've marked this task as done:");
		printOutput(task.toString());
	}

	/**
	 * Prints confirmation when a task is deleted from the list.
	 * @param task the task deleted.
	 * @param numberOfTask the current number of tasks.
	 */
	public void printDeleteTask(Task task, int numberOfTask) {
		printOutput("Noted. I've removed this task:");
		printOutput(task.toString());
		printOutput("Now you have " + numberOfTask + " tasks in the list.");

	}

	/**
	 * Prints confirmation when all tasks is deleted from the list.
	 */
	public void printDeleteAllTasks() {
		printOutput("Noted. I've removed all tasks in the list.");
		printOutput("Now you have no task in the list.");
	}
}
