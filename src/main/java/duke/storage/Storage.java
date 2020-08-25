package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskException;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.TaskTypeDecodeException;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Encapsulates the logic for storing tasks.
 *
 * It will create folders and file based on the given filePath in the constructor.
 *
 * A connection to the file is created and maintained once this class is instantiated.
 */
public class Storage {
    private final File file;

    /**
     * Constructs a Storage class.
     *
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
        if (!file.exists()) {
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
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                TaskType taskType = TaskType.decodeTaskType(line);
                String taskStorageLine = TaskType.getStorageLine(line, taskType);
                switch (taskType) {
                case DEADLINE:
                    taskList.addTask(Deadline.parseStorageString(taskStorageLine));
                    break;
                case EVENT:
                    taskList.addTask(Event.parseStorageString(taskStorageLine));
                    break;
                case TODO:
                    taskList.addTask(Todo.parseStorageString(taskStorageLine));
                    break;
                }
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
            FileWriter writer = new FileWriter(file);
            writer.write(getStorageString(taskList).toString());
            writer.close();
        } catch (IOException e) {
            throw new StorageException("File remains missing even after initialisation.");
        } catch (TaskTypeDecodeException e) {
            throw new StorageException("One or more tasks cannot be saved.");
        }

        return file;
    }

    /**
     * Iterates through the given taskList to get each storage representation.
     *
     * @param taskList TaskList to iterate over.
     * @return StringBuilder with the storage representations separated by \n.
     * @throws TaskTypeDecodeException If the task cannot be properly represented in storage.
     */
    private StringBuilder getStorageString(TaskList taskList) throws TaskTypeDecodeException {
        StringBuilder string = new StringBuilder();
        boolean isFirst = true;
        for (Task task : taskList.getTaskList()) {
            String taskStorageString = TaskType.appendIdentifier(task.toStorageString(), task.getTaskType());
            if (isFirst) {
                string.append(taskStorageString);
                isFirst = false;
            } else {
                string.append("\n").append(taskStorageString);
            }
        }
        return string;
    }
}
