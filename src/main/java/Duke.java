/**
 * Duke is a personal chatbot with the following functionalities:
 * (i) Adding and removing of Tasks to a list
 * (ii) Checking Tasks as completed
 * (iii) Viewing current task list
 *
 * Duke stores the task list in a txt file which is first created
 * when the user does not have such a file.
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
     * the running flag is true. The bot takes in user input, processes
     * it and detects command/keywords.
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
     * Prints a display of the bot's logo, level, and name,
     * before instantiating and running the bot.
     * @param args optional and will be treated as the first user input.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}