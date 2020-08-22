package duke;

/**
 * Controls application.
 */

public class Duke {
    /** Local task store */
    private final Storage storage;
    /** List of tasks */
    private TaskList tasks;
    /** duke.Ui to interact with user */
    private final Ui ui;

    /**
     * Constructor for duke.DeleteCommand.
     * @param filepath Contains filepath of local data storage.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
        ui.greet();
    }

    /**
     * Initialises the program.
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs event loop to listen for user input, until the user invokes a duke.ByeCommand.
     */
    private void run() {
        while (true) {
            Parser parser = new Parser();
            String input = ui.readCommand();
            try {
                Command cmd = parser.parse(input);
                cmd.execute(tasks, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
