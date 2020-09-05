package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

import duke.task.TaskException;
import duke.task.TaskList;
import duke.task.TaskTypeDecodeException;

/**
 * Encapsulates the logic for storing tasks.
 * It will create folders and file based on the given filePath in the constructor.
 * A connection to the file is created and maintained once this class is instantiated.
 */
public class Storage {
    private final File file;

    /**
     * Constructs a Storage class.
     * A connection to the file is created and maintained once this class is instantiated.
     *
     * @param filePath File to save the tasks in.
     * @throws StorageException If the path cannot be created.
     */
    public Storage(String filePath) throws StorageException {
        try {
            Path path = Path.of(filePath);
            File file = path.toFile();
            createFileIfMissing(file);
            assert file.exists() : "File does not exist after initialisation";
            this.file = file;
        } catch (InvalidPathException e) {
            throw new StorageException("Invalid path.");
        }
    }

    /**
     * Creates the directory if missing.
     *
     * @param file The file instance to save the tasks in.
     * @throws StorageException If the path cannot be created.
     */
    private void createFileIfMissing(File file) throws StorageException {
        if (file.exists()) {
            return;
        }

        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            boolean didSucceed = parentDirectory.mkdirs();
            if (!didSucceed) {
                throw new StorageException("Failed to create parent directory.");
            }
        }

        try {
            boolean didSucceed = file.createNewFile();
            if (!didSucceed) {
                throw new StorageException("Failed to create file.");
            }
        } catch (IOException e) {
            throw new StorageException("Failed to create file.");
        }
    }

    /**
     * Reads from the file and insert in the TaskList.
     *
     * @param taskList TaskList instance to insert into
     * @return The given taskList.
     * @throws StorageException If the file cannot be read or is missing.
     */
    public TaskList load(TaskList taskList) throws StorageException {
        try {
            Scanner reader = new Scanner(file, StandardCharsets.UTF_8);
            while (reader.hasNextLine()) {
                String storageLine = reader.nextLine();
                taskList.parseStorageLine(storageLine);
            }
        } catch (IOException e) {
            throw new StorageException("File remains missing even after initialisation.");
        } catch (TaskTypeDecodeException e) {
            throw new StorageException("Unknown task type. File may have been corrupted.");
        } catch (TaskException e) {
            throw new StorageException("Could not read one or more task.");
        }

        return taskList;
    }

    /**
     * Saves the taskList into file.
     *
     * @param taskList The taskList to read from.
     * @return The File instance that has been newly written.
     * @throws StorageException If the file is missing or the task cannot be represented in storage.
     */
    public File save(TaskList taskList) throws StorageException {
        try {
            String storageDocument = taskList.getStorageDocument();
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), StandardCharsets.UTF_8));
            out.write(storageDocument);
            out.close();
        } catch (TaskTypeDecodeException e) {
            throw new StorageException("One or more tasks cannot be saved.");
        } catch (IOException e) {
            throw new StorageException("File remains missing even after initialisation.");
        }

        return file;
    }
}
