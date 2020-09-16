import java.io.IOException;

/**
 * Represents the main Chatbot.
 */
public class Duke{

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return this.run(input);
    }

    /**
     * Loads the saved tasks into the list of tasks.
     * @param filePath path to file containing saved tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
        ui = new Ui();
    }

    /**
     * Activates and deactivates the Chatbot accordingly.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.toString());
        }
    }
}
