package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.parser.StorageParser;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class TaskStorage {
    private final File file;
    private final StorageParser storageParser;

    private static final String DEFAULT_FILEPATH = "src/main/java/duke/storage/";
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

    public TaskList loadTaskList(Ui ui) {
        TaskList taskList = new TaskList();
        Scanner s;
        try {
            s = new Scanner(this.file);
        } catch (IOException ignore) {
            return taskList;
        }

        while (s.hasNext()) {
            try {
                Task task = this.storageParser.convertStorageToTask(s.nextLine());
                taskList.addTask(task);
            } catch (DukeException exception) {
                ui.showStatus(exception.getMessage());
            }
        }
        return taskList;
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
