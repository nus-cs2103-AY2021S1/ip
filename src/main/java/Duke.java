/**
 * Duke is an application that stores a user's tasks.
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(".//.//.//savedTasks.txt");
        this.taskList = new TaskList();
        this.parser = new Parser(ui, taskList, storage);
        storage.loadTasksOnSavedFile(taskList,ui);
    }
    
    /**
     * Constructor that creates a Duke object.
     * @param filePath the filepath in which the application's data will be saved.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        this.parser = new Parser(ui, taskList, storage);
        storage.loadTasksOnSavedFile(taskList,ui);
    }

    /**
     * Returns the response of the parser as a String after parsing the user input.
     * @param input user input
     * @return Returns the response of the parser as a String after parsing the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parseInputIntoCommand(input);
            String response = command.execute();
            storage.saveTasksToSavedFile(taskList, ui);
            return response;
        } catch (DukeUnknownArgumentException e) {
            return ui.showErrorMessage(e);
        }
    }
    
}