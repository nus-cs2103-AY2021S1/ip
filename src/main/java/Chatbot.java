import java.io.File;
import java.util.Scanner;

/**
 * Class responsible for the execution of the Duke chatbot.
 */
public class Chatbot {
    private final String DATA_PATH_NAME = "./data";
    private final String DATA_FILE_PATH = "./data/dukedata.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises a new Chatbot object using the specified DATA_PATH_NAME and DATA_FILE_PATH values.
     */
    public Chatbot() {
        ui = new Ui();
        storage = new Storage(DATA_PATH_NAME, DATA_FILE_PATH);
        File file = storage.getSavedData(ui);

        if (file != null) {
            tasks = new TaskList(file, ui);
        } else {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot process using the initialised storage, tasklist and ui objects and runs it until
     * programme execution is ended by user.
     */
    public void start() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        while (!Parser.isDone()) {
            String input = scanner.nextLine();
            Parser.parse(input, storage, tasks, ui);
        }

        scanner.close();
    }
}
