package duke;

import exception.EmptyDescriptionException;
import exception.EmptyTimeException;
import exception.InvalidCommandException;
import exception.InvalidIndexException;

public class Parser {
	private static void checkCommands (String input) throws InvalidCommandException {
		try {
			Commands.valueOf(input.trim().toUpperCase());
		} catch(IllegalArgumentException ex) {
			throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
		}
	}

	public static Commands parse(String fullCommand) throws InvalidCommandException {
		String[] inputs = fullCommand.split("\\s+", 2);
		checkCommands(inputs[0]);
		return Commands.valueOf(inputs[0].trim().toUpperCase());
	}

	public static void checkIndex (String[] inputs, int numberOfTask) throws InvalidIndexException {
		String usage = (numberOfTask > 1 ? "\nInput a number between 1 - " + numberOfTask : "");
		if(inputs.length < 2 || inputs[1].trim().equals("")) {
			throw new InvalidIndexException("Please input a valid index of task" + usage );
		}
		try {
			int index = Integer.parseInt(inputs[1]);
			if (index < 1 || index > numberOfTask) {
				throw new InvalidIndexException("Please input a valid index of task" + usage );
			}
		} catch (NumberFormatException ex) {
			throw new InvalidIndexException("Please input a valid index of task" + usage );
		}
	}

	public static void checkDescription (String[] task, Commands type) throws EmptyDescriptionException {
		String usage = "Please input using the format: " +
			(type.equals(Commands.TODO) ? "todo <todo_desc>" :
				type.equals(Commands.DEADLINE) ? "deadline <deadline_desc> /by <time>" :
					"event <event_desc> /at <time>");
		if (task.length < 2 || task[1].trim().equals("")) {
			throw new EmptyDescriptionException("Please specify the task description\n" + usage);
		}
	}

	public static void checkTime (String[] desc, Commands type) throws EmptyTimeException {
		String usage = "Please input using the format: " +
			(type.equals(Commands.TODO) ? "todo <todo_desc>" :
				type.equals(Commands.DEADLINE) ? "deadline <deadline_desc> /by <time>" :
					"event <event_desc> /at <time>");
		if (desc.length < 2 || desc[1].trim().equals("")) {
			throw new EmptyTimeException("Please specify the time for the task\n" + usage);
		}
	}
}
