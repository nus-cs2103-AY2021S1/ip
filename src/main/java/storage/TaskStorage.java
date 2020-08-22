package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exception.DukeException;
import parser.StorageParser;
import task.Task;
import task.TaskList;

public class TaskStorage {
    private final File file;
    private final StorageParser storageParser;

    private static final String DEFAULT_FILEPATH = "src/main/java/storage/";
    private static final String DEFAULT_FILENAME = "taskstorage.txt";

    private TaskStorage(File file) {
        this.file = file;
        this.storageParser = new StorageParser();
    }

    public static TaskStorage createTaskStorage() {
        File f = new File(DEFAULT_FILEPATH);
        File actualFile = f.exists()
                ? new File(DEFAULT_FILEPATH + DEFAULT_FILENAME)
                : new File(DEFAULT_FILENAME);
        return new TaskStorage(actualFile);
    }

    public TaskList loadTaskList() throws DukeException {
        TaskList taskList = new TaskList();
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                Task task = this.storageParser.convertStorageToTask(s.nextLine());
                taskList.addTask(task);
            }
            return taskList;
        } catch (DukeException exception) {
            throw exception;
        } catch (IOException ignore) {
            return taskList;
        }
    }

    private void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(this.file.getAbsolutePath());
        fw.write(text);
        fw.close();
    }

    public void saveToDisk(TaskList taskList) throws DukeException {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            String storageTask = this.storageParser.convertTaskToStorage(task);
            sb.append(storageTask);
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException exception) {
            throw new DukeException("There were some problems when writing to the file. "
                    + exception.getMessage());
        }
    }
}
