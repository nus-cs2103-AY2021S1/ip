
/**
 * Duke is a bot that functions as a user's task manager.
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor that creates a Duke object.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(".//SAVED-TASKS.txt");
        this.taskList = new TaskList();
        this.parser = new Parser(ui, taskList, storage);
        storage.load(taskList, ui);

    }

    /**
     * Obtains the Duke response depending on the user input
     * @param input user's input into Duke
     * @return String containing Duke's response
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            String response = command.action();
            storage.saveTasks(taskList, ui);
            return response;
        } catch (DukeException e) {
            return ui.printDukeError(e);
        }
    }
}

