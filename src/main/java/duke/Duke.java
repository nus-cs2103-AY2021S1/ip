package duke;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

/**
 * Main class to initialise and run the chatbot.
 */
public class Duke {

    static final String FILE_NAME = "duke_data.txt";
    final Path FILE_PATH;

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    /**
     * Creates a Duke. Loads existing task data, or creates a file if it doesn't exist.
     */
    public Duke() {
        ui = new Ui();

        String home = System.getProperty("user.dir");
        Path DIR_PATH = java.nio.file.Paths.get(home, "data");
        try {
            java.nio.file.Files.createDirectory(DIR_PATH);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        FILE_PATH = java.nio.file.Paths.get(home, "data", FILE_NAME);
        try {
            java.nio.file.Files.createFile(FILE_PATH);
            storage = new Storage(FILE_PATH);
            taskList = new TaskList();
        } catch (FileAlreadyExistsException ignored) {
            storage = new Storage(FILE_PATH);
            taskList = new TaskList(storage.loadList());
            ui.writeOutput("Existing file loaded!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ui.welcome();
        parser = new Parser(taskList, storage, ui);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Main running loop
     */
    public void run() {
        boolean keepGoing = true;
        String input;
        while (keepGoing) {
            input = ui.readInput();
            try {
                keepGoing = parser.processInput(input);
            } catch (DukeException de) {
                ui.writeOutput(de.getMessage());
            }
        }
    }
}
