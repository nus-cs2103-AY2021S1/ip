package duke;

/**
 * Encapsulates a Duke object.
 */
public class Duke {
    private static TaskList tasks;
    private Ui ui;
    private Storage storage;
    private boolean isExit;


    /**
     * Instantiates a Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");

        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String showGreetingMessage() {
        ui.clearMessage();
        ui.showGreeting();
        return ui.getMessage();
    }

    public String getResponse(String input) {
        ui.clearMessage();
        try {
            Command command = Parser.readUserInput(input);
            command.execute(tasks, storage, ui);
            isExit = command.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return ui.getMessage();
    }
}
