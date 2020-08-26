package Duke.Main;

import Duke.Errors.*;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.util.Scanner;
import java.time.LocalDateTime;

import java.util.List;

public class Ui {
	public void showLoadingError() {
		echo("☹ OOPS!!! An error occurred while loading from the .txt file");
	}

	/**
	 * Echo command to provide basic styling
	 * @param s String to echo
	 */
	public static void echo(String s) {
		String line = "____________________________________________________________";
		System.out.printf("%s\n%s\n%s\n", line, s, line);
	}

	/**
	 * Gets the provided List
	 * @param lst List of Tasks
	 */
	private String getList(List<Task> lst) {
		String s = "";
		for (int i = 1; i <= lst.size(); i++) {
			Task item = lst.get(i - 1);
			s += String.format("%d.%s", i, item);
			s += (i == lst.size()) ? "" : "\n";
		}
		return s;
	}

	/**
	 * Show welcome message
	 */
	public void showWelcome() {
		Ui.echo("Hello! I'm Duke\nWhat can I do for you?");
	}

	/**
	 * Main command to check inputs and output the bot "answers"
	 * @param sc Scanner to check inputs
	 * @param tasks TaskList object to manipulate database
	 */
	public void checkCommands(Scanner sc, TaskList tasks) {
		loop: while (sc.hasNextLine()) {
			String sentence = sc.nextLine();
			String[] arr = sentence.split("\\s+");
			String command = arr[0];
			String comText = "";
			for (int i = 1; i < arr.length; i++) {
				comText += arr[i] + " ";
			}
			comText = comText.trim();
			try {
				switch (command) {
					case "todo":
						if (arr.length == 1) {
							throw new EmptyDescException();
						} else {
							Task todo = new Todo(comText, "0");
							tasks.addTask(todo.getStringArr());
							Ui.echo(String.format(
									"Got it. I've added this task:" + "\n%s\nNow you have %d tasks in the list.", todo,
									tasks.getSize()));
						}
						break;
					case "deadline":
						int dIdx = comText.lastIndexOf("/by");
						if (arr.length == 1) {
							throw new EmptyDescException();
						} else if (dIdx == -1 || comText.length() == (dIdx + 3)) {
							throw new DeadlineException();
						} else {
							String desc = comText.substring(0, dIdx - 1);
							String by = comText.substring(dIdx + 4, comText.length()).trim();

							LocalDateTime deadlineDate = Parser.strToDate(by);
							Task deadline = new Deadline(desc, "0", deadlineDate);
							tasks.addTask(deadline.getStringArr());
							Ui.echo(String.format(
									"Got it. I've added this task:" + "\n%s\nNow you have %d tasks in the list.",
									deadline, tasks.getSize()));
						}
						break;
					case "event":
						int eIdx = comText.lastIndexOf("/at");
						if (arr.length == 1) {
							throw new EmptyDescException();
						} else if (eIdx == -1 || comText.length() == (eIdx + 3)) {
							throw new EventTaskException();
						} else {
							String desc = comText.substring(0, eIdx - 1);
							String at = comText.substring(eIdx + 4, comText.length()).trim();

							LocalDateTime eventDate = Parser.strToDate(at);
							Task event = new Event(desc, "0", eventDate);
							tasks.addTask(event.getStringArr());
							Ui.echo(String.format(
									"Got it. I've added this task:" + "\n%s\nNow you have %d tasks in the list.", event,
									tasks.getSize()));
						}
						break;
					case "find":
						List<Task> foundTasks = tasks.findTasks(comText);
						Ui.echo(String.format(
								"Here are the matching tasks in your list:\n%s",
								getList(foundTasks))
						);
						break;
					case "list":
						Ui.echo(getList(tasks.getList()));
						break;
					case "done":
						if (arr.length != 2) {
							throw new InvalidCommandException();
						}
						int doneNum = Integer.parseInt(arr[1]);
						if (doneNum > tasks.getSize() || doneNum < 0) {
							throw new InvalidIndexException();
						} else {
							Task item = tasks.completeTask(doneNum);
							Ui.echo(String.format("Nice! I've marked this task as done:\n%s", item));
						}
						break;
					case "delete":
						if (arr.length != 2) {
							throw new InvalidCommandException();
						}
						int deleteNum = Integer.parseInt(arr[1]);
						if (deleteNum > tasks.getSize() || deleteNum < 0) {
							throw new InvalidIndexException();
						} else {
							Task item = tasks.deleteTask(deleteNum);
							Ui.echo(String.format(
									"Noted. I've removed this task:\n%s" + "\nNow you have %d tasks in the list.", item,
									tasks.getSize()));
						}
						break;
					case "bye":
						Ui.echo("Bye. Hope to see you again soon!");
						break loop;
					default:
						throw new InvalidCommandException();
				}
			} catch (Exception e) {
				Ui.echo(e.getMessage());
			}

		}
	}
}