package duke;

import duke.exceptions.DukeException;
import duke.commands.Command;
import javafx.application.Platform;

/**
 * Duke is a small educational project which acts
 * based on user input.
 * @author Xia Liyi.
 */
public class Duke{
	private Storage storage;
	private TaskList taskList;

	public Duke() {
		storage = new Storage("F:/XIA-LIYI/ip/src/main/data/duke.txt");
		taskList = new TaskList(storage.getTaskList());
	}

	public String getResponse(String input) {
		try {
			Command cmd = Parser.parse(input);
			String res = cmd.run(taskList, storage);
			if (cmd.isBye()) {
				Platform.exit();
				System.exit(0);
			}
			return res;
		} catch (DukeException e) {
			return e.getMessage();
		}
	}

}
