package duke;

/**
 * Encapsulates the greenfield project Duke that manages user tasks.
 */
public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskManager taskManager;

    /**
     * Constructs a duke object.
     */
    public Duke() {
        this.ui = new Ui();
        this.taskManager = new TaskManager();
    }

    /**
     * Initialises the program by using ui object to display the welcome message,
     * and calls on taskManager object to begin requesting and processing user input.
     */
    public void run() {
        this.ui.printWelcomeMessage();
        this.taskManager.manage();
    }

    /**
     * Drives program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
