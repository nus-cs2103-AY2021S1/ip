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
    /** Parser to parse user's inputs */
    private Parser parser;

    /**
     * Initialises Duke and objects required.
     */
    @SuppressWarnings("checkstyle:WhitespaceAround")
    public Duke() {
        ui = new Ui(parser);
        storage = new Storage();
        tasks = new TaskList();
        parser = new Parser();
        try {
            storage.load(tasks);
        } catch (Exception e) {
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
        boolean isExit = ui.isExit();
        while (!isExit) {
            ui.readInput();
            isExit = ui.isExit();
        }
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

    protected String getResponse(String input) throws DukeException {
        storage.save(TaskList.tasks);
        return parser.parseInputs(input);
    }
}
