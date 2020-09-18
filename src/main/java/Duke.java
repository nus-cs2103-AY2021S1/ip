import java.io.IOException;

/**
 * Represents the main Chatbot.
 */
public class Duke{

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Gets the response from the Chatbot to the user input.
     * @param input the user input.
     * @return the response from the Chatbot.
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
     * Runs the Chatbot with user inputs.
     * @param input the user input.
     * @return the Chatbot's response to the user input.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            if (command.isExit()) {
                storage.saveTasks(tasks.getTasksList());
                System.exit(0);
            }
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.toString());
        }
    }
}
