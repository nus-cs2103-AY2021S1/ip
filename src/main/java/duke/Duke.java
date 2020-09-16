package duke;

/**
 * A task manager.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Duke constructor.
     */
    public Duke() throws DukeException {
        ui = new Ui();
        storage = new Storage("./data/Duke.txt");
        taskList = new TaskList(storage.readFromFile());
    }

    /**
     * Gets Duke's response.
     *
     * @param input the input string.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        ui.clearResponse();
        try {
            Command next = Parser.parse(input);
            next.execute(taskList, storage, ui);
        } catch (DukeException e) {
            ui.addMessage(e.getMessage());
        }
        return ui.getResponse();
    }
}
