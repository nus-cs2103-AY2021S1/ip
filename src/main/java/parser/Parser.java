package duke.parser;

import duke.exception.DukeException;

import duke.command.Command;
import duke.command.CommandList;
import duke.command.CommandBye;
import duke.command.CommandDeadline;
import duke.command.CommandDelete;
import duke.command.CommandDone;
import duke.command.CommandEvent;
import duke.command.CommandFind;
import duke.command.CommandSave;
import duke.command.CommandTodo;


/**
 * Parser deals with the full commands given by the user.
 * Inputs given are deciphered and corresponding responses are outputted.
 */

public class Parser {
	public static Command parse(String fullCommand) throws DukeException {
		if(fullCommand.equals("list")) {
			return new CommandList();
		} else if (fullCommand.equals("bye")) {
			return new CommandBye();
		} else if (getWord(fullCommand).equals("done")) {
			return new CommandDone(fullCommand);
		} else if (getWord(fullCommand).equals("todo")) {
			return new CommandTodo(fullCommand);
		} else if (getWord(fullCommand).equals("deadline")) {
			return new CommandDeadline(fullCommand);
		} else if (getWord(fullCommand).equals("event")) {
			return new CommandEvent(fullCommand);
		} else if (getWord(fullCommand).equals("delete")) {
			return new CommandDelete(fullCommand);
		} else if(fullCommand.equals("save")) {
			return new CommandSave();
		} else if (getWord(fullCommand).equals("find")) {
			return new CommandFind(fullCommand);
		} else{
			throw new DukeException("\uD83D\uDE2D OOPS!!! I'm sorry, but I don't know what that means :-(");
		}
	}

	/**
	 * Returns the first word in the string indicated by the first blank space.
	 * If there are no blank space, the entire string is returned.
	 *
	 * @param string
	 * @return
	 */
	public static String getWord(String string) {

		int firstSpaceIndex = string.indexOf(' ');
		if (firstSpaceIndex == -1) {
			return string;
		}
		return string.substring(0, firstSpaceIndex);
	}
}
