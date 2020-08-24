import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private static final String DEFAULT_STORAGE_FILEPATH = "src/data/duke.txt";
    private String filePath;
    private File file;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a");

    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) throws InvalidStorageFilePathException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!this.file.exists())
            throw new InvalidStorageFilePathException("File not found.");
    }

    public TaskList load() throws StorageOperationException, FileNotFoundException, IllegalValueException {
        if (!this.file.exists()) {
            return new TaskList();
        }
        return TaskListDecoder.decodeTaskList(this.file);
    }

    public void save(TaskList taskList) throws StorageOperationException {
        try {
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
            FileWriter fw = new FileWriter(this.filePath);
            StringBuilder textToAdd = new StringBuilder();
            encodedTaskList.forEach(encodedTask -> textToAdd.append(encodedTask).append("\n"));
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + filePath);
        }
    }

}
