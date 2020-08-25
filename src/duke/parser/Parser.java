package duke.parser;

import duke.command.*;
import duke.exception.DukeArgumentException;
import duke.exception.DukeException;
import duke.exception.DukeIOException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for reading input and files
 */
public final class Parser {

	final static String regex = ",, ";
	private final static String BYE = "bye";
	private final static String LIST = "list";
	private final static String DONE = "done";
	private final static String DELETE = "delete";
	private final static String TODO = "todo";
	private final static String EVENT = "event";
	private final static String DEADLINE = "deadline";

	/**
	 * Reads a String and splits it to create a new duke.Tasks.Task based on its type and values.
	 * Format: Type | Completed | Name | Time
	 * Example: "[D],, 1,, deadline,, 23-08-2020"
	 *
	 * @param line the String from the csv to be parsed
	 * @return A duke.Tasks.Task object
	 * @throws DukeException if the line does not follow the given regex.
	 */
	public static Task parseLine(String line) throws DukeIOException {
		String[] values = line.split(regex);
		switch (values[0]) {
		case "[T]":
			return new Todo(values[2], values[1]);
		case "[E]":
			return new Event(values[2], LocalDate.parse(values[3], DateTimeFormatter.ofPattern("MMM d yyyy")),
					values[1]);
		case "[D]":
			return new Deadline(values[2], LocalDate.parse(values[3], DateTimeFormatter.ofPattern("MMM d yyyy")), values[1]);
		default:
			throw new DukeIOException(String.format("The line '%s' could not be parsed.", line));
		}
	}

	public static String convertTask(Task task) {
		String[] args = task.toArray();
		String result = "";
		for (String s : args) {
			result = result.concat(String.format("%s%s", s, regex));
		}
		return result + "\n";
	}

	public static Command parseCommand(String text) throws DukeArgumentException {
		String[] parsedInput = text.split(" ", 2);
		switch (parsedInput[0].toLowerCase()) {
		case BYE:
			return new ExitCommand();
		case LIST:
			return new ListCommand();
		case DONE:
			return new DoneCommand(parsedInput[1]);
		case DELETE:
			return new DeleteCommand(parsedInput[1]);
		case TODO:
			return new TodoCommand(parsedInput[1]);
		case EVENT:
			return new EventCommand(parsedInput[1]);
		case DEADLINE:
			return new DeadlineCommand(parsedInput[1]);
		default:
			throw new DukeArgumentException("duke.commands.Command did not match any known commands");
		}
	}


}
