package alice.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Represents the file used to store the list of tasks.
 */
public class StorageFile {
    /**
     * Default file path to store ALICE data file
     */
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";

    private final Path filePath;

    /**
     * Creates a StorageFile from the default filePath.
     */
    public StorageFile() {
        filePath = Paths.get(DEFAULT_FILE_PATH);
    }

    /**
     * Creates a StorageFile from the indicated filePath.
     *
     * @param filePath relative path to the data file.
     */
    public StorageFile(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads the data file located at the filePath.
     * If file does not exists, create the file and directory to file.
     *
     * @return list of encoded tasks in the data file, or
     * null if the data file does not exist.
     * @throws AliceStorageException if there were errors reading or creating the file.
     */
    public List<String> load() throws AliceStorageException {
        boolean fileExists = Files.exists(filePath);
        if (fileExists) {
            return readFile();
        } else {
            // Create data file and directory
            createFile();
            return null;
        }
    }

    /**
     * Reads the data file located at the filePath.
     *
     * @return list of encoded tasks in the data file
     * @throws AliceStorageException if there were errors reading the file.
     */
    private List<String> readFile() throws AliceStorageException {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException ex) {
            throw new AliceStorageException("Unable to read data file!");
        }
    }

    /**
     * Creates a new data file at the filePath.
     * If the file is nested in another directory, creates the respective directories too.
     *
     * @throws AliceStorageException if there were errors creating the file at the path.
     */
    private void createFile() throws AliceStorageException {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException ex) {
            throw new AliceStorageException("Problem occurred creating file!");
        }
    }

    /**
     * Writes the encoded list of tasks onto the data file.
     *
     * @param tasks list of encoded tasks to save.
     * @throws AliceStorageException if there were errors writing to the file.
     */
    public void save(List<String> tasks) throws AliceStorageException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i));
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            throw new AliceStorageException("File save error!");
        }
    }

    /**
     * Writes the encoded task onto the last line in the data file.
     *
     * @param taskToAdd encoded task to save.
     * @throws AliceStorageException if there were errors writing to the file.
     */
    public void saveToLastLine(String taskToAdd) throws AliceStorageException {
        try {
            Files.write(filePath, (taskToAdd + "\n").getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new AliceStorageException("File save error!");
        }
    }
}
