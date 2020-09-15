package duke;

/**
 * Entry point of the program.
 */
public class Duke {
    private Storage storage;
    private TaskList<Task> tasks;
    private Ui ui;
    private boolean isFirstInteraction;

    /**
     * Instantiates a Duke object.
     *
     * @param filePath the directory to store data
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isFirstInteraction = true;
        try {
            tasks = new TaskList<>(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    public String getResponse(String userInput) {
        try {
            ui.clearMessage();
            assert ui.toString().isEmpty()
                    : "ui message should be empty"; // assert that ui has successfully cleared its message

            Command c = Parser.parse(userInput);
            c.execute(tasks, ui, storage);
            storage.writeToFile(tasks);
            return ui.toString();
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}
