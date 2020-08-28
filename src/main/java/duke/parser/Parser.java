package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Handles user inputs and make sense out of it.
 */
public class Parser {

	/**
	 * Takes in a line of user input as a String and returns a relevant Command. Otherwise,
	 * throw a DukeException when a command is invalid.
	 *
	 * @param fullCommand user input
	 * @return Command to be executed
	 * @throws DukeException when inputs are invalid or incomplete
	 */
	public static Command parse(String fullCommand) throws DukeException {

		//remove leading and trailing white spaces
		String noWhiteSpace = fullCommand.strip();

		//get first word of command
		String[] instruction = noWhiteSpace.split(" ", 2);

		if (instruction[0].equals("bye")) {
			return new ExitCommand();
		} else if (instruction[0].equals("list")) {
			if (instruction.length == 1) {
				return new ListCommand();
			} else {
				throw new DukeException("Extra inputs detected! Please only input 'list'.");
			}
		} else if (instruction[0].equals("todo")) {
			if (instruction.length == 1) {
				throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
			} else {
				return new TodoCommand(instruction[1]);
			}
		} else if (instruction[0].equals("deadline")) {
			if (instruction.length == 1) {
				throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
			} else {
				//check if contains deadline " /by yyyy-MM-dd HH:mm"
				if ((instruction[1].contains(" /by ")) &&
						(instruction[1].split(" /by ")[1]
								.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01]) " +
										"([01]?[0-9]|2[0-3]):[0-5][0-9]"))) {
					return new DeadlineCommand(instruction[1].split(" /by ")[0],
							instruction[1].split(" /by ")[1]);

				} else {
					throw new DukeException("Please input in the following format " +
							"'deadline <description> /by <yyyy-MM-dd HH:mm>' with a valid date & time");
				}
			}

		} else if (instruction[0].equals("event")) {

			if (instruction.length == 1) {
				throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
			} else {
				//check if contains event " /at yyyy-MM-dd HH:mm"
				if ((instruction[1].contains(" /at ")) &&
						(instruction[1].split(" /at ")[1]
								.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01]) " +
										"([01]?[0-9]|2[0-3]):[0-5][0-9]"))) {
					return new EventCommand(instruction[1].split(" /at ")[0],
							instruction[1].split(" /at ")[1]);
				} else {
					throw new DukeException("Please input in the following format " +
							"'event <description> /at <yyyy-MM-dd HH:mm>' with a valid date & time");
				}
			}
		} else if (instruction[0].equals("done")) {
			//done with no other arguments
			if (instruction.length == 1) {
				throw new DukeException("Please specify item number!");
			}

			//done with exactly 2 inputs
			else if (instruction.length == 2) {
				return new DoneCommand(instruction[1]);

			}
		} else if (instruction[0].equals("delete")) {
			//done with no other arguments
			if (instruction.length == 1) {
				throw new DukeException("Please specify item number!");
			}
			//done with exactly 2 inputs
			else if (instruction.length == 2) {
				return new DeleteCommand(instruction[1]);
			}
		} else {
			throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
		}

		throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
	}

}
