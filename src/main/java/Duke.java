import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents a Duke assistant.
 * @author davidsqf
 *
 */
public class Duke {
    public Storage storage;
    public TaskList tasks;
    public Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(new File(filePath)));
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initiates the whole process.
     * @throws IOException
     */
    public void run() throws IOException {
        ui.greeting();
        ui.run();
    }

    public String getResponse(String input) throws IOException {
        return ui.getResponse(input);
    }

    public String getGreeting() {
        return ui.greet();
    }

    /**
     * Reads from user's command line and creates a Duke object and starts the process.
     *
     * @param args User's command line.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        boolean fileExists = new java.io.File("File.txt").exists();
        new Duke("File.txt").run();
    }
}
