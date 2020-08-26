
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Duke {

	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	public Duke() {
		ui = new Ui();
		tasks = new TaskList();
	};

	public Duke(String filePath) {
		//initialize User interface
		ui = new Ui();

		storage = new Storage(filePath);

		//Initialize TaskList
		try {
			tasks = new TaskList(storage.load());
		} catch (DukeException e) {
//			ui.showLoadingError();
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
