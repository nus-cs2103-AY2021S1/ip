package emily.command;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

import emily.exception.DukeException;
import emily.storage.Storage;
import emily.storage.TaskList;


/**
 * Initialises the app components.
 */
public class Emily {

    private static final String FILE_PATH = "data/emily.txt"; //store txt file under data folder
    private final Storage storage;
    private final Logic logic;
    private TaskList tasks;

    /**
     * Manages the app components
     * Loads the tasklist with existing saved tasks from previous sessions
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
     * Copy constructor with preloaded txt file path
     * @param filePath where the data text is loaded from
     */
    public Emily(String filePath) {
        logic = new Logic();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
        }
    }

    public String formatOutput(ArrayList<String> outputLines) {
        return outputLines.stream().map(x -> ("\n    " + x)).collect(Collectors.joining());
    }

    /**
     * Reads the string of user command and return the corresponding output
     * by the program.
     *
     * @param userInputText user will type into the box
     * @return a String response from Emily
     */
    public String receiveCommandLine(String userInputText) {
        boolean end = false;
        ArrayList<String> outputLines = new ArrayList<>();

        /*The program within the loop
        will set end as true once the command is completed without errors*/
        while (!end) {
            try {
                outputLines = logic.readsLine(userInputText, tasks);
                storage.saveData(tasks.getTaskArrayList());
                end = true;
            } catch (DukeException e) {
                return "    OOPS! " + e.getMessage() + "\n";
            }
        }

        String output = formatOutput(outputLines);
        return output;
    }
}
