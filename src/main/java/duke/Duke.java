package duke;

/**
 * Main class to run Ui object and listen for user input
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws Exception {
        this.ui.initialise(tasks, storage);
    }

    public static void main(String[] args) throws Exception {
        // remember to change filepath to "../../../data/duke.ser" during jar build
        Duke duke = new Duke("data/duke.ser");
        duke.run();
    }
}
