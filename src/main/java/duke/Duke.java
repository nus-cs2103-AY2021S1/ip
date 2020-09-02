package duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates the Duke object.
     */
    public Duke() {
        String filePath = "data/duke.TaskList.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            try {
                storage.newStorage();
            } catch (DukeException ex) {
                ui.printError(e.getMessage());
            }
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Returns a response to be displayed.
     * @param input User input string.
     * @return The response.
     */
    public String getResponse(String input) {
        String output = null;
        try {
            Command command = Parser.parse(input);
            output = command.execute(ui, tasks, storage);
        } catch (DukeException e) {
            output = ui.printError(e.getMessage());
        }
        return output;
    }
}
