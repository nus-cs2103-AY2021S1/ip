/**
 * Represents a Duke chatbot that can store, delete, mark tasks as done and display them.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    // Constructor
    public Duke(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Displays a welcome message and runs the chatbot, continuously receiving user input and executing them accordingly.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String inputData = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(inputData);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs a Duke object with a file at filePath of "data/duke.txt".
     * @param args
     */
    public static void main(String[] args) {
        Duke dukeObj = new Duke("data/duke.txt");
        dukeObj.run();
    }
}