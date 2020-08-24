package duke;

/**
 * Duke class is the main class to use in order to run the bot.
 */
public class Duke {

    /** User's storage */
    private Storage storage;
    /** User's task list */
    private TaskList tasks;
    /** Ui to interact with user */
    private Ui ui;

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
     * Runs Duke.
     *
     * @throws DukeException
     */
    public void run() throws DukeException {
        Ui.greeting();
        Parser.parseInputs(tasks);
        storage.save(TaskList.tasks);
    }

    /**
     * Entry point for Duke to start.
     *
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}