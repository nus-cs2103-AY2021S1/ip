package storage;

import exception.DukeException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskStorage {
    private final File file;

    private static final String DEFAULT_FILEPATH = "src/main/java/storage/";
    private static final String DEFAULT_FILENAME = "taskstorage.txt";
    private static final String IS_COMPLETED = "Y";
    private static final String NOT_COMPLETED = "N";
    private static final String DELIMITER = ";";

    private TaskStorage(File file) {
        this.file = file;
    }

    public static TaskStorage createTaskStorage() {
        File f = new File(DEFAULT_FILEPATH);
        File actualFile = f.exists()
                ? new File(DEFAULT_FILEPATH + DEFAULT_FILENAME)
                : new File(DEFAULT_FILENAME);
        return new TaskStorage(actualFile);
    }

    private static String convertTaskToStorage(Task task) {
        String symbol = task.getTaskSymbol();
        String completed = task.isTaskCompleted()
                ? DELIMITER + IS_COMPLETED
                : DELIMITER + NOT_COMPLETED;
        String description = DELIMITER + task.getTaskDescription();
        String datetime = task.getTaskDatetime().map(d -> DELIMITER + d).orElse("");
        return symbol + completed + description + datetime + "\n";
    }

    private static Task convertStorageToTask(String storageTask) throws DukeException {
        String[] task = storageTask.split(DELIMITER);
        boolean isCompleted = task[1].equals(IS_COMPLETED);
        switch(task[0]) {
            case Todo.TODO_SYMBOL:
                return new Todo(task[2], isCompleted);
            case Deadline.DEADLINE_SYMBOL:
                return new Deadline(task[2], isCompleted, task[3]);
            case Event.EVENT_SYMBOL:
                return new Event(task[2], isCompleted, task[3]);
            default:
                String err = String.format("It appears this line '%s' is corrupted.", storageTask);
                throw new DukeException(err);
        }
    }

    public TaskList loadTaskList() {
        TaskList taskList = new TaskList();
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                Task task = convertStorageToTask(s.nextLine());
                taskList.addTask(task);
            }
            return taskList;
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
        } catch (IOException ignore) {}
        return new TaskList();
    }

    private void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(this.file.getAbsolutePath());
        fw.write(text);
        fw.close();
    }

    public void saveToDisk(TaskList taskList) throws DukeException {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            String storageTask = convertTaskToStorage(task);
            sb.append(storageTask);
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException exception) {
            throw new DukeException("There were some problems when writing to the file"
                    + exception.getMessage());
        }
    }
}
