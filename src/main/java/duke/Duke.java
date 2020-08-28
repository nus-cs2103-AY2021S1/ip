package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	public Duke(String filePath) {

		//initialize User interface
		ui = new Ui();

		//Initialize Storage location
		storage = new Storage(filePath);

		//Initialize TaskList
		try {
			tasks = new TaskList(storage.load());
		} catch (DukeException e) {
			tasks = new TaskList();
			storage.writeToFile(storage.getPath().toString(), "");
		}

	}

	public void run() {

		//print greeting message
		ui.printGreeting();

		boolean isExit = false;
		while (!isExit) {
			try {

				String fullCommand = ui.readCommand();
				ui.printDivider();
				Command c = Parser.parse(fullCommand);
				c.execute(tasks, ui, storage);
				isExit = c.isExit();
			} catch (DukeException e) {
				ui.showError(e.getMessage());
			} finally {
				ui.printDivider();
				System.out.println();
			}
		}

	}


	public static void main(String[] args) {
		new Duke("DukenizerTaskList.txt").run();
	}




}
