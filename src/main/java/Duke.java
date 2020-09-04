import java.io.IOException;

public class Duke{

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // Application.launch will automatically call constructor with no para
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

    public Ui getUi() {
        return ui;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        // take in a user input string
        // return a string
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
