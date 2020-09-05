package emily.command;

import emily.exception.DukeException;
import emily.storage.Storage;
import emily.storage.TaskList;
import java.io.File;


/**
 * Initialises the app components.
 */
public class Emily {

    private static final String FILE_PATH = "data/emily.txt";
    private final Storage storage;
    private final Logic logic;
    private TaskList tasks;

    /**
     * Manages the app components
     */
    public Emily() {
        logic = new Logic();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            File f = new File(FILE_PATH);
            f.getParentFile().mkdirs();
        }
    }

    /**
     * Reads the string of user command and return the corresponding output
     * by the program.
     * @param userInputText user will type into the box
     * @return a String response from Emily
     */
    public String receiveCommandLine(String userInputText) {
        boolean end = false;
        String output = "";

        while (!end) {
            try {
                output = logic.readsLine(userInputText, tasks);
                storage.saveData(tasks.getTaskArrayList());
                end = true;
            } catch (DukeException e) {
                return "    OOPS! " + e.getMessage() + "\n";
            }
        }
        return output;
    }
}