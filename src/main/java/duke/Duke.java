package duke;

/**
 * Duke class is the main class to use in order to run the bot.
 */
public class Duke {

    private Storage storage; // User's storage
    private TaskList tasks; // User's task list
    private Ui ui; // Ui to interact with user

    /**
     * Initialises Duke and objects required.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        try {
            storage.load(tasks);
        } catch (Exception e){
            Ui.showLoadingError();
        }
    }

    /**
     * Run Duke.
     * @throws DukeException
     */
    public void run() throws DukeException {
        Ui.greeting();
        Parser.parse(tasks);
        storage.save(TaskList.tasks);
    }

    /**
     * Entry point for Duke to start.
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
