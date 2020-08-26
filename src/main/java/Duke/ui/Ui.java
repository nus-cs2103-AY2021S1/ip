package Duke.ui;

import Duke.task.Task;
import Duke.task.TaskList;

import java.util.Scanner;

public class Ui {

	private Scanner sc;
	public Ui () {
		this.sc = new Scanner(System.in);
	}
	public void printExit() {
		System.out.println("Bye. Hope to see you again soon!");
	}

	public void printDivider() {
		System.out.println("_______________________________________________________________");
	}

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

	public void printList(TaskList lst) {
		System.out.println(lst.toString());
	}

	public String readCommand() {
		//Take in Input
		String query = sc.nextLine();
		return query;
	}

	public void showError(String message) {
		System.out.println(message);
	}

	public void printTaskAdded(TaskList lst, Task task) {
		System.out.println("Got it. I've added this task:");
		System.out.println(task.toString());
		System.out.println("Now you have " + lst.getSize() + " tasks in the list.");
	}

	public void printTaskDone(Task task) {
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(task.toString());
	}

	public void printTaskDeleted(TaskList lst, Task task) {
		System.out.println("Noted. I've removed this task:");
		System.out.println(task.toString());
		System.out.println("Now you have " + lst.getSize() + " tasks in the list.");
	}

	public void showLoadingError() {
		System.out.println("We created a new Task List for you since we couldn't find any items in your previous list! :)");
	}

}
