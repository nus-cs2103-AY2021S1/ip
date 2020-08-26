/**
 * Duke is a personal chat bot with the following functionalities:
 *     (i) Adding and removing of Tasks to a list
 *     (ii) Checking Tasks as completed
 *     (iii) Viewing current task list
 *
 * The bot has the following components:
 *     - TaskList - to add, remove, mark tasks
 *     - Ui - to provide basic displays for user to interact with
 *     - Storage - to read and write tasks into a .txt file
 *
 * @author Andy Wu
 */

public class Duke {

    /** List which stores the Tasks. */
    private final TaskList tasks;

    /** Reads and writes the txt file. */
    private final Storage storage;

    /** In charge of all user interface related operations */
    private final Ui ui;

    /**
     * Constructor for initializing the bot.
     * A file path is required to store the task list.
     * @param filePath the path of the txt file.
     */
    public Duke(String filePath) {
        TaskList temp;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            temp = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
            temp = new TaskList();
        }
        tasks = temp;
    }

    /**
     * The main algorithm of the bot which runs indefinitely as long as
     * the running flag is true. The algorithm is summarised as:
     *
     *     0. Show the welcome message
     *     1. Read raw user input
     *     2. Parse user input and create the appropriate command
     *     3. Execute the command
     *     4. Repeat 1-3 until an exit command
     *     5. Show the exit message
     *
     * Throughout the process, DukeExceptions may be created but are
     * caught and handled by the respective classes.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            String input = ui.readCommand();
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isRunning = !c.isExit();
            } catch (DukeException e) {
                ui.sendMessage(e.getMessage());
            }
        }
    }

    /**
     * Driver to create the chat bot object and run it.
     * @param args optional and will be treated as the first user input.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}