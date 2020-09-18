package duke;

import java.io.IOException;

/**
 * Represents the driver class to run Duke.
 */
public class Duke {
    
    private Storage store;
    private TaskList taskList;
    private Ui ui;
    
    public Duke() {
        this(Storage.DEFAULT_FILEPATH);
    }
    
    /**
     * Creates a new Duke chatbot that saves and loads tasks from the given filePath.
     *
     * @param filePath The file path for the storage.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.store = new Storage(filePath);
        try {
            this.taskList = new TaskList(store.loadFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Returns a String representation of all the reminders.
     *
     * @return The reminders of the user.
     * @throws IOException if file is not laoded properly.
     * @throws DukeException if reminder command is not executed properly.
     */
    public String giveReminders() throws IOException, DukeException {
        Command start = Parser.giveReminders();
        return start.execute(taskList, ui, store);
    }

    /**
     * Returns a String respresentation of the response of Duke.
     *
     * @param input The user input message.
     * @return The response of Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert !c.equals("") : "Command cannot be empty";
            return c.execute(taskList, ui, store);
        } catch (DukeException | IOException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Greets the user with a welcome message.
     * 
     * @return A welcome message with reminders.
     * @throws IOException if file is not laoded properly.
     * @throws DukeException if reminder command is not executed properly.
     */
    public String showWelcomeMessage() throws IOException, DukeException {
        return this.ui.greetUser() + "\n" + giveReminders();
    }
}