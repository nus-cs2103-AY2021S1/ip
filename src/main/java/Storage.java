import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {
    private final File file;

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

    public File save(TaskList taskList) throws StorageException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(getStorageString(taskList).toString());
            writer.close();
        } catch (IOException e) {
            throw new StorageException("File remains missing even after initialisation.");
        } catch (TaskTypeDecodeException e) {
            throw new StorageException("File contains unknown task types.");
        }

        return file;
    }

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
