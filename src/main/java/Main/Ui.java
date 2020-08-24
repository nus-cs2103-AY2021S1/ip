package Main;

import Errors.*;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.util.Scanner;
import java.time.LocalDateTime;

import java.util.List;

public class Ui {
	public void showLoadingError() {
		echo("☹ OOPS!!! An error occurred while loading from the .txt file");
	}

	public static void echo(String s) {
		String line = "____________________________________________________________";
		System.out.printf("%s\n%s\n%s\n", line, s, line);
	}

	private void printList(List<Task> lst) {
		String s = "";
		for (int i = 1; i <= lst.size(); i++) {
			Task item = lst.get(i - 1);
			s += String.format("%d.%s", i, item);
			s += (i == lst.size()) ? "" : "\n";
		}
		Ui.echo(s);
	}

	public void showWelcome() {
		Ui.echo("Hello! I'm Main.Duke\nWhat can I do for you?");
	}

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
					case "list":
						printList(tasks.getList());
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