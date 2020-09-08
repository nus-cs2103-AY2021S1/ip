package main.java.farrell.duke;

/**
 * A personal assistant chatbot that helps to keep track of tasks.
 */
public class Duke {
    /** The list of tasks being tracked */
    private TaskList taskList;

    /** An object that handles saving and loading data */
    private DataManager dataManager = new DataManager();

    /** An object that handles user interaction */
    private UiManager uiManager;

    /**
     * Starts a new instance of the program.
     * Existing data is loaded (if available).
     * Display a startup message.
     * @param uiManager The UIManager to handle the startup message.
     * @throws DukeException If unable to load data
     */
    public Duke(UiManager uiManager) throws DukeException {
        this.uiManager = uiManager;
        taskList = dataManager.load();
        uiManager.displayStartMessage();
    }

    /**
     * Displays a welcome message, then runs the main program loop.
     * The main program loop consists of the following:
     * 1. Obtaining user input.
     * 2. Parsing user input to the corresponding command.
     * 3. Execute the command.
     * 4. Display the program output to the user.
     */
    public void run(String input) {
        try {
            uiManager.sendUserMessage(input);
            Command command = CommandParser.parse(input);
            String output = command.execute(taskList);

            dataManager.save(taskList);

            uiManager.sendDukeMessage(output);

            if (command.shouldExit()) {
                System.exit(0);
            }
        } catch (DukeException exception) {
            uiManager.sendDukeMessage(exception.getMessage());
        }
    }
}
