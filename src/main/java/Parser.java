import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Parser takes in a variety of strings, makes sense of the strings and creates relevant objects.
 */
public class Parser {
	/**
	 * Parses dates that are of the accepted date formats.
	 *
	 * @param str String to parse.
	 * @return Date if successfully parsed, null if wrong date format.
	 */
	public static Date parseDate(String str) {
		List<String> formatStrings = Arrays.asList("yyyy-M-dd", "dd/M/yyyy HHmm", "dd/M/yyyy", "MMM d yyyy");

		for (String formatString : formatStrings) {
			try {
				return new SimpleDateFormat(formatString).parse(str);
			} catch (ParseException e) {}
		}

		return null;
	}

	/**
	 * Parses in user inputs and generates commands based on the inputs.
	 *
	 * @param display User input.
	 * @return Command based on display.
	 * @throws DukeException
	 * @throws TaskException
	 */
	public static Command parse(String display) throws DukeException, TaskException {
		if (display.equals("list")) {
			return new ListCommand();
		} else if (display.length() >= 4 && display.startsWith("done")) {
			try {
				int idx = Integer.parseInt(String.valueOf(display.charAt(5))) - 1;
				return new DoneCommand(idx);
			} catch (NumberFormatException ex) {
				throw new DukeException("task index is not a valid number");
			}
		} else if (display.length() >= 6 && display.startsWith("delete")) {
			try {
				int idx = Integer.parseInt(String.valueOf(display.charAt(7))) - 1;
				return new DeleteCommand(idx);
			} catch (IndexOutOfBoundsException ex) {
				throw new DukeException("task index is not a valid number");
			}
		} else if (display.length() >= 12 && display.startsWith("tasks due on")) {
			if (parseDate(display.substring(13)) == null) {
				throw new DukeException("time is of the wrong format");
			} else {
				return new ListCommand(parseDate(display.substring(13)));
			}
		} else if (display.equals("bye")) {
			return new ExitCommand();
		} else {
			if (display.length() >= 4 && display.startsWith("todo")) {
				if (display.length() == 4 || display.substring(4).isBlank()) {
					throw new TaskException(TaskType.TODO, "description",  "cannot be empty.");
				} else {
					return new AddCommand(TaskType.TODO, display.substring(5), null);
				}
			} else if (display.length() >= 8 && display.startsWith("deadline")) {
				int idx = display.indexOf(" /by ");
				if (idx == -1 || display.length() == idx + 5 || display.substring(idx + 5).isBlank()) {
					throw new TaskException(TaskType.DEADLINE, "time", "cannot be identified.");
				} else if (idx <= 9 || display.substring(9, idx).isBlank()) {
					throw new TaskException(TaskType.DEADLINE, "description", "cannot be empty.");
				} else {
					if (parseDate(display.substring(idx + 5)) == null) {
						throw new TaskException(TaskType.DEADLINE, "time", "format is wrong.");
					} else {
						return new AddCommand(TaskType.DEADLINE, display.substring(9, idx),
								parseDate(display.substring(idx + 5)));
					}
				}
			} else if (display.length() >= 5 && display.startsWith("event")) {
				int idx = display.indexOf(" /at ");
				if (idx == -1 || display.length() < idx + 5 || display.substring(idx + 5).isBlank()) {
					throw new TaskException(TaskType.EVENT, "time", "cannot be identified.");
				} else if (idx <= 6 || display.substring(6, idx).isBlank()) {
					throw new TaskException(TaskType.EVENT, "description", "cannot be empty.");
				} else {
					if (parseDate(display.substring(idx + 5)) == null) {
						throw new TaskException(TaskType.EVENT, "time", "format is wrong.");
					} else {
						return new AddCommand(TaskType.EVENT, display.substring(6, idx),
								parseDate(display.substring(idx + 5)));
					}
				}
			} else {
				throw new DukeException("I don't know what that means");
			}
		}
	}
}
