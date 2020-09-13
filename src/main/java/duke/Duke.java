package duke;
import duke.command.Command;

public class Duke {
    public static final String FILE_PATH = "data/duke.txt";
    /** File path for storage object to write to. */
    private Storage storage;
    private TaskList taskItems;
    private Ui ui;
    private Parser parser;

    /**
     * Instantiates Duke Object which initializes variables needed throughout program.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            taskItems = new TaskList(storage.loadTasksFromMemory());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskItems = new TaskList();
        }
    }

    /**
     * Greets the user upon execution of duke process.
     * Reads User input and execute appropriate command.
     */
    public void run() {
        System.out.println(ui.greetUser());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                System.out.println(ui.formatReply(c.execute(taskItems, ui, storage)));
                isExit = c.isExit();
            } catch (DukeException duked) {
                System.out.println(ui.formatReply(ui.showError(duked.getMessage())));
            } finally {
            }
        }
    }

    /**
     * Returns responses after parsing user commands.
     */
    public String getResponse(String input) throws DukeException {
        Command command = Parser.parse(input);
        return command.execute(taskItems, ui, storage);
    }

    /** * Executes the Duke process.
     *
     * @param args command line argument it is a collection of variables in the string format.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
