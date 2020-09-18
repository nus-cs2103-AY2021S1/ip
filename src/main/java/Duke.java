import java.io.IOException;

/**
 * Main class for running bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises a duke bot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load().getList());
        } catch (IOException e) {
            ui.errorWithLoading();
            tasks = new TaskList();
        }
    }

    /**
     * Getter for ui.
     *
     * @return ui
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Takes in user response.
     */
    protected String getResponse(String input) {
        String s;
        try {
            Command c = Parser.parse(input);
            s = c.execute(tasks, ui, storage);
        } catch (DukeException | IOException e) {
            s = ui.showError(e.getMessage());
        }
        return s;
    }

}
