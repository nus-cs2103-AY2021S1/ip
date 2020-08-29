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
    /** Parser to understand user commands. */
    private final Parser parser;

    /**
     * Constructor for Duke.
     * @param filepath Contains filepath of local data storage.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
        System.out.println(ui.greet());
    }

    /**
     * Listens for user input and responds.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command cmd = parser.parse(input);
            response = cmd.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}
