import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents a Duke object that acts like a program.
 * Contains the main method.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isFileLoaded;
    private String filepath;
    private Parser parser;

    /**
     * Creates a Duke object.
     * It is to start the Duke program.
     * <p>
     * Variable filepath is directory to the duke.txt file where reading and writing of the file occurs.
     * <p>
     * Initialises Ui, Storage classes.
     * Sets isLoadingSuccess to true, assuming no errors.
     * <p>
     * Loads the file contents.
     * <p>
     * Parse the file contents into system.
     */
    public Duke() {
        /**@@author Steve Hill How Do I Make Cross-Platform File Paths in Java?
         * https://www.sghill.net/how-do-i-make-cross-platform-file-paths-in-java.html
         */
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "Desktop", "CS2103T", "ip", "data", "duke.txt");

        filepath = path.toString();
        ui = new Ui();
        storage = new Storage(filepath);
        isFileLoaded = false;

        storage.loadFileContent();

        List<Task> fileTasks = storage.getSavedTasks();
        tasks = new TaskList(fileTasks);
        isFileLoaded = true;

        parser = new Parser(tasks);
    }

    /**
     * Gets reminders from user's file data.
     * Passes reminders to MainWindow to display as text.
     *
     * @return String representation of reminders of unfinished/urgent tasks.
     */
    public String getReminder() {
        return tasks.getUrgentTasks();
    }

    /**
     * Returns response from Duke based on user input.
     *
     * @param input represents user input in String format.
     *
     * @return String represents Duke's output in String format.
     */
    public String getResponse(String input) {
        String output;

        try {
            output = parser.parseUserInput(input);

            try {
                storage.writeToFile(tasks.getTasks());
            } catch (IOException e) {
                output = ui.displayUpdateFileError(e.getMessage());
            }

        } catch (DukeException e) {
            output = e.toString();
        }

        return output;
    }
}
