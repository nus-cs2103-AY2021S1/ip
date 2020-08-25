package duke.parser;

import duke.Duke;
import duke.exception.DukeException;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;

public class Parser {
	/**
	 * Parses the command from CLI and returns a corresponding Command object.
	 *
	 * @param command
	 * @return A command object corresponding to the input command string.
	 * @throws DukeException If unknown command or error in command.
	 */
	public static Command parse(String command) throws DukeException {
		return parseAndReturnCommand(command);
	}

	static boolean stringIsInt(String string) {
		try {
			parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	static Command parseAndReturnCommand(String string) throws DukeException {
			String[] splitString = string.split("\\s+");
			if (string.equals("bye")) {
				return new ExitCommand();
			} else if (string.equals("list")) {
				return new ListCommand();
			} else if (splitString.length == 2 &&
					splitString[0].equals("done") && stringIsInt(splitString[1])) {
				int index = parseInt(splitString[1]);
				return new DoneCommand(index - 1);
			} else if (splitString.length == 2 &&
					splitString[0].equals("delete") && stringIsInt(splitString[1])) {
				int index = parseInt(splitString[1]);
				return new DeleteCommand(index - 1);
			} else if (splitString.length == 2 && splitString[0].equals("date")) {
				LocalDate date = parseDateStringToLocalDate(splitString[1]);
				return new DateListCommand(date);
			} else if (splitString.length == 3 && splitString[0].equals("dateAndTime")) {
				LocalDate date = parseDateStringToLocalDate(splitString[1]);
				LocalTime time = parseTimeStringToLocalTime(splitString[2]);
				return new DateAndTimeListCommand(date, time);
			} else if (splitString[0].equals(Duke.TaskType.TODO.name)
				|| splitString[0].equals(Duke.TaskType.DEADLINE.name)
					|| splitString[0].equals(Duke.TaskType.EVENT.name)) {
				return createAddCommand(splitString);
			} else if (splitString[0].equals("find") && splitString.length == 2) {
				return new FindCommand(splitString[1]);
			} else {
				throw new DukeException("Oops! I'm sorry, I don't know what that means.");
			}

	}

	static AddCommand createAddCommand(String[] stringArray) throws DukeException {
		Task taskToAdd = null;
		if (stringArray[0].equals(Duke.TaskType.TODO.name)) {
			taskToAdd = todo(stringArray);
		} else if (stringArray[0].equals(Duke.TaskType.DEADLINE.name)) {
			taskToAdd = deadline(stringArray);
		} else if (stringArray[0].equals(Duke.TaskType.EVENT.name)) {
			taskToAdd = event(stringArray);
		}
		return new AddCommand(taskToAdd);
	}

	static ToDo todo(String[] stringArray) throws DukeException {
		if (stringArray.length == 1) {
			throw new DukeException("Oops! A todo task needs a description.");
		} else {
			String description = stringArrayToString(stringArray,
					1, stringArray.length);
			ToDo todoTask = new ToDo(description);
			return todoTask;
		}
	}

	static Deadline deadline(String[] stringArray) throws DukeException {
		int indexOfBy = -1;
		for (int i = 0; i < stringArray.length; i++) {
			if (stringArray[i].equals("/by")) {
				indexOfBy = i;
				break;
			}
		}
		if (indexOfBy == 1 || stringArray.length == 1) {
			throw new DukeException("Oops! A deadline task needs a description");
		} else if (indexOfBy == stringArray.length - 1 || indexOfBy == -1) {
			throw new DukeException("Oops! A deadline task needs a deadline date and time");
		} else if (stringArray.length < indexOfBy + 3) {
			throw new DukeException("Oops! Date or time is missing from deadline");
		} else {
			String dateString = stringArray[indexOfBy + 1];
			String timeString = stringArray[indexOfBy + 2];
			String description = stringArrayToString(stringArray, 1, indexOfBy);
			LocalDate localDate = parseDateStringToLocalDate(dateString);
			LocalTime localTime = parseTimeStringToLocalTime(timeString);
			Deadline deadline = new Deadline(description, localDate, localTime);
			return deadline;
		}
	}

	// Takes input as a string array, then adds a new event to list, then prints the message
	static Event event(String[] stringArray) throws DukeException {
		int indexOfAt = -1;
		for (int i = 0; i < stringArray.length; i++) {
			if (stringArray[i].equals("/at")) {
				indexOfAt = i;
				break;
			}
		}
		if (indexOfAt == 1 || stringArray.length == 1) {
			throw new DukeException("Oops! An event task needs a description");
		}  else if (indexOfAt == stringArray.length - 1 || indexOfAt == -1) {
			throw new DukeException("Oops! An event task needs a date and time");
		}else if (stringArray.length < indexOfAt + 3) {
			throw new DukeException("Oops! Date or time is missing from event");
		} else {
			String dateString = stringArray[indexOfAt + 1];
			String timeString = stringArray[indexOfAt + 2];
			String description = stringArrayToString(stringArray, 1, indexOfAt);
			LocalDate localDate = parseDateStringToLocalDate(dateString);
			LocalTime localTime = parseTimeStringToLocalTime(timeString);
			Event event = new Event(description, localDate, localTime);
			return event;
		}
	}
// ---------------------------- Methods for date and time ---------------------------
	// Assumes input to be dd/mm/yyyy, returns in yyyy-mm-dd format
	static LocalDate parseDateStringToLocalDate(String dateString) throws DukeException {
		try {
			String[] stringArray = dateString.split("/");
			String day = stringArray[0];
			String month = stringArray[1];
			String year = stringArray[2];
			while (day.length() < 2) {
				day = "0" + day;
			}
			while (month.length() < 2) {
				month = "0" + month;
			}
			String editedDateString = day + "/" + month + "/" + year;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.parse(editedDateString, formatter);
			return localDate;
		} catch (IndexOutOfBoundsException e) {
			throw new DukeException("Date: " + dateString + " is not formatted correctly\n"
					+ "Please use dd/mm/yyyy format.");
		} catch (DateTimeParseException e) {
			throw new DukeException("Date: " + dateString + " is not formatted correctly\n"
					+ "Please use dd/mm/yyyy format.");
		}
	}

	static LocalTime parseTimeStringToLocalTime(String timeString) throws DukeException {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("kkmm");
			LocalTime localTime = LocalTime.parse(timeString, formatter);
			return localTime;
		} catch (DateTimeParseException e) {
			throw new DukeException("Time: " + timeString + " is not formatted correctly.\n"
					+ "Please use HHMM format.");
		}
	}


	// ----------------------------------------------------------------------------------

	static String stringArrayToString(String[] arr, int startIndex, int endIndex) {
		try {
			if (endIndex > startIndex) {
				StringBuilder stringBuilder = new StringBuilder();
				for (int i = startIndex; i < endIndex - 1; i++) {
					stringBuilder.append(arr[i] + " ");
				}
				stringBuilder.append(arr[endIndex - 1]);
				return stringBuilder.toString();
			} else {
				return null;
			}
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
}
