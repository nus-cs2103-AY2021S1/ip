import java.time.LocalDate;

public final class Parser {
	// utility class for reading lines into tasks
	final static String regex = ",, ";

	/**
	 * Reads a String and splits it to create a new Task based on its type and values.
	 * Format: Type | Completed | Name | Time
	 * Example: "[D],, 1,, Deadline,, 23-08-2020"
	 *
	 * @param line the String from the csv to be parsed
	 * @return A Task object
	 * @throws DukeException if the line does not follow the given regex.
	 */
	static Task parseLine(String line) throws DukeException {
		String[] values = line.split(regex);
		switch (values[0]) {
		case "[T]":
			return new Todo(values[2], values[1]);
		case "[E]":
			return new Event(values[2], LocalDate.parse(values[3]), values[1]);
		case "[D]":
			return new Deadline(values[2], LocalDate.parse(values[3]), values[1]);
		default:
			throw new DukeException(String.format("The line '%s' could not be parsed.", line));
		}
	}

	static String convertTask(Task task) {
		String[] args = task.toArray();
		String result = "";
		for (String s : args) {
			result = result.concat(String.format("%s%s", s, regex));
		}
		return result + "\n";
	}

}
