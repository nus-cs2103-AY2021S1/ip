import java.io.File;

/**
 * Class responsible for the execution of the Duke chatbot.
 */
public class Duke {
    private static final String DATA_PATH_NAME = "./data";
    private static final String DATA_FILE_PATH = "./data/dukedata.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises a new Duke object using the specified DATA_PATH_NAME and DATA_FILE_PATH values.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_PATH_NAME, DATA_FILE_PATH);
        File file = storage.getSavedData(ui);

        if (file != null) {
            tasks = new TaskList(file, ui);
        } else {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        if (Parser.isDone()) {
            System.exit(0);
        }
        String result = Parser.parse(input, storage, tasks, ui);
        return result;
    }
}
