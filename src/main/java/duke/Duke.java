package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

	private static String DATA_PATHNAME = "data/duke.txt";

	private TaskList tasks;
	private Storage storage;
	private Ui ui;

	Duke() {
		storage = new Storage(DATA_PATHNAME);
		ui = new Ui();
		try {
			tasks = new TaskList(storage.load());
		} catch (DukeException e) {
			System.out.println("ERROR LOADING DATA");
			tasks = new TaskList();
		}
	}

	public void run() {
		ui.showWelcome();
		boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
	}


	public static void main(String[] args) {
		Duke duke = new Duke();
		duke.run();
	}
}
