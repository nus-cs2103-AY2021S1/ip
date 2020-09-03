import javafx.application.Platform;

/**
 * Represents a Duke chatbot that can store, delete, mark tasks as done and display them.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke object that loads information from specified filePath.
     * @param filePath The text file which Duke loads information from.
     */
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

    public String showWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * Generate response to user input from GUI.
     * @param input The user input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String result = c.execute(tasks, ui, storage);
            return result;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Displays a welcome message and runs the chatbot,
     * continuously receiving user input and executing them accordingly.
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
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke dukeObj = new Duke("data/duke.txt");
        dukeObj.run();
    }
}
