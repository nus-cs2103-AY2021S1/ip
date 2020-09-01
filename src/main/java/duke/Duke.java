package duke;

/**
 * Encapsulates the greenfield project Duke that manages user tasks.
 */
public class Duke {
    private Ui ui;
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
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
