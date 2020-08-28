package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * The Ui class handles all the interactions, including input and output, with the user.
 */
public class Ui {

	private Scanner sc;
	public Ui () {
		this.sc = new Scanner(System.in);
	}

	/**
	 * Print out exit program to user.
	 */
	public void printExit() {
		System.out.println("Bye. Hope to see you again soon!");
	}

	/**
	 * Print out section divider.
	 */
	public void printDivider() {
		System.out.println("_______________________________________________________________");
	}

	/**
	 * Print out greeting to user.
	 */
	public void printGreeting() {
		String logo =
				" ____        _                    \n"
						+ "|  _ \\ _   _| | _____  ______ ______ ______  ___  _____\n"
						+ "| | | | | | | |/ / _ \\|  __  |__  __|___   |/ _ \\|  _  \\\n"
						+ "| |_| | |_| |   <  __/| |  | |__||__ /   /_<  __/|     /\n"
						+ "|____/ \\__,_|_|\\_\\___|| |  | |______|______|\\___||_|\\__\\ \n";
		System.out.println("Hello from\n" + logo);
		System.out.println("_______________________________________________________________");
		System.out.println("Hello! I'm Dukenizer\nWhat can I do for you?");
		System.out.println("_______________________________________________________________");
		System.out.println();
	}

	/**
	 * Prints the task list in the program.
	 */
	public void printList(TaskList lst) {
		System.out.println(lst.toString());
		if (lst.getSize() == 0) {
			System.out.println("Your task list is empty!");
		}
	}

	/**
	 * Takes in user input to be processed.
	 *
	 * @return String representation of user input.
	 */
	public String readCommand() {
		//Take in Input
		String query = sc.nextLine();
		return query;
	}

	/**
	 * Prints out any error messages.
	 *
	 * @param message error message
	 */
	public void showError(String message) {
		System.out.println(message);
	}

	/**
	 * Prints out to user that task is successfully added.
	 *
	 * @param lst TaskList in the program
	 * @param task Task that was added
	 */
	public void printTaskAdded(TaskList lst, Task task) {
		System.out.println("Got it. I've added this task:");
		System.out.println(task.toString());
		System.out.println("Now you have " + lst.getSize() + " tasks in the list.");
	}

	/**
	 * Print out mark Task as done.
	 *
	 * @param task Task to be marked done.
	 */
	public void printTaskDone(Task task) {
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(task.toString());
	}

	/**
	 * Print out Task successfully deleted
	 *
	 * @param lst TaskList in the program
	 * @param task Task to be deleted
	 */
	public void printTaskDeleted(TaskList lst, Task task) {
		System.out.println("Noted. I've removed this task:");
		System.out.println(task.toString());
		System.out.println("Now you have " + lst.getSize() + " tasks in the list.");
	}

	public void showLoadingError() {
		System.out.println("We created a new Task List for you since we couldn't find any items in your previous list! :)");
	}

	/**
	 * Prints out matching tasks line.
	 */
	public void printMatchingTasks() {
		System.out.println("Here are the matching tasks in your list:");
	}

}
