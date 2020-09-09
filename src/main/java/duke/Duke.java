package duke;

import java.io.IOException;

/**
 * The Duke program represents a person assistant
 * chatbot that helps the user manage tasks and store them
 * in a list according to the user input.
 * 
 * @author York Tat
 * @version 1.0
 * @since 2020-08-14
 */
public class Duke {
    
    private Storage store;
    private TaskList taskList;
    private Ui ui;
    
    public Duke() {
    }

    /**
     * Creates a new Duke chatbot that saves and loads tasks from the given filePath
     * 
     * @param filePath
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.store = new Storage(filePath);
        try {
            this.taskList = new TaskList(store.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }
    
    public String giveReminders() throws IOException, DukeException {
        Command start = Parser.start();
        return start.execute(taskList, ui, store);
    }
    
    /**
     * Runs the chatbot until an exit command is issued.
     */
    public String run(String input) {
        try {
            Command c = Parser.parse(input);
            assert !c.equals("") : "Command cannot be empty";
            return c.execute(taskList, ui, store);
        } catch (DukeException | IOException e) {
            return ui.showError(e.getMessage());
        }
    }
    
    public String showWelcomeMessage() throws DukeException, IOException {
        try {
            return this.ui.greetUser() + "\n" + giveReminders();
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
    
    public String getResponse(String input) {
        return run(input);
    }
}