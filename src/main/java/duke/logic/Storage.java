package duke.logic;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Represents a Storage space responsible for saving the task list
 * for future use, or loading the task list for current use.
 */
public class Storage {

    /** Path to the .txt file that contains the list of tasks */
    private final String filepath;

    /**
     * Creates a Storage object to store the task list.
     *
     * @param filepath Path to the .txt file that contains the list of tasks.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the Tasklist from the Storage data for current use.
     *
     * @return Tasklist to be used currently.
     * @throws DukeException If there is a problem faced with reading the .txt file.
     */
    public Tasklist load() throws DukeException {
        Tasklist tasks = new Tasklist();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String nextLine = reader.readLine();
            while (nextLine != null) {
                Task nextTask = Parser.parseTask(nextLine);
                tasks.add(nextTask);
                nextLine = reader.readLine();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            assert(!Paths.get(filepath).toFile().exists());
            throw new DukeException(ErrorMessage.MISSING_TASKS_FILE.getMessage());
        } catch (IOException ioException) {
            throw new DukeException(ErrorMessage.UNABLE_READ_TASK_HISTORY.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the data from current Tasklist into the Storage for future use.
     *
     * @param tasks Current Tasklist in use.
     * @throws DukeException If a problem was encountered when attempting to save the Tasklist.
     */
    public void save(Tasklist tasks) throws DukeException {
        try {
            // Make data directory if it doesn't exist
            Path pathToDirectory = Paths.get("data");
            if (!pathToDirectory.toFile().exists()) {
                File data = new File("data");
                data.mkdir();
            }
            String latestTaskListString = tasks.toSavedString();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(latestTaskListString);
            writer.close();
            assert(Paths.get(filepath).toFile().exists());
        } catch (IOException ioException) {
            throw new DukeException(ErrorMessage.UNABLE_SAVE_TASK_LIST.getMessage());
        }
    }

}
