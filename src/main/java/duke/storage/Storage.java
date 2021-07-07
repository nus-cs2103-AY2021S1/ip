package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.exceptions.InvalidFilePathException;
import duke.exceptions.StorageOperationException;

import duke.task.TaskManager;

/**
 * Represents the database of the system which will handle the reading and writing to update the items stored in the
 * database.
 */

public class Storage {
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";
    private final Path path;

    public Storage() throws InvalidFilePathException {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Constructs a new {@code Storage} object with the specified file path.
     *
     * @param filepath the file path of the file that will store all of the user {@code Task} information.
     * @throws InvalidFilePathException if the file path is invalid.
     */
    public Storage(String filepath) throws InvalidFilePathException {
        this.path = Paths.get(filepath);
        if (!isValidPath(path)) {
            throw new InvalidFilePathException();
        }
        File file = new File(filepath);
        file.getParentFile().mkdirs();
    }

    private static boolean isValidPath(Path filepath) {
        return filepath.toString().endsWith(".txt");
    }

    /**
     * Loads the file contents.
     *
     * @return a {@code TaskManager} object containing the {@code Task} information that is stored in the file.
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public TaskManager load() throws StorageOperationException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskManager();
        }

        try {
            return DataTranslator.decode(Files.readAllLines(path));
        } catch (FileNotFoundException e) {
            throw new AssertionError("A non-existent file scenario has been handled earlier.");
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    /**
     * Saves the list of {@code Task} stored within the {@code TaskManager} object.
     *
     * @param taskManger the {@code TaskManager} object which contains all of the user's {@code Task} instances.
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(TaskManager taskManger) throws StorageOperationException {
        try {
            List<String> encodedData = DataTranslator.encode(taskManger);
            Files.write(path, encodedData);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }


}
