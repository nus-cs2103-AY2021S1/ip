package duke.storage;

import duke.exceptions.InvalidFilePathException;
import duke.exceptions.StorageOperationException;

import duke.task.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private final Path path;
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";

    public Storage() throws InvalidFilePathException {
        this(DEFAULT_FILEPATH);
    }

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

    public void save(TaskManager taskManger) throws StorageOperationException {
        try {
            List<String> encodedData = DataTranslator.encode(taskManger);
            Files.write(path, encodedData);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }


}
