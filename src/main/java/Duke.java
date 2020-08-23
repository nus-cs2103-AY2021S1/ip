import java.io.FileNotFoundException;

/**
 * Serves as a chat bot. Duke can keep a record of user's inputs as a list of
 * tasks, mark them as completed when they are done, and show the user the list
 * of tasks upon request.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String memoryFilePath = "data/duke.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | WrongFormatException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(memoryFilePath).run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) { // Duke takes in user input indefinitely until the user says "bye"
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }
}
