package duke.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.command.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents the actual class for objects that executes reading data from and writing data into a storage file.
 */
public class ActualStorage implements Storage {
    public static final char TODO_INDICATOR = 'T';
    public static final char DEADLINE_INDICATOR = 'D';
    public static final char EVENT_INDICATOR = 'E';
    public static final String INPUT_FILE_FORMAT_ERROR = "Input file format error!";
    private File file;
    private final TaskList list;

    /**
     * Creates a Storage object, and initializes the list of tasks with data in the file.
     * @param filePath the file path holding the targeted data
     */
    public ActualStorage(String filePath) {
        list = new TaskList();
        file = new File(filePath);
        try {
            file.createNewFile();
        } catch (Exception e) {
            createFile();
        }
        try {
            readFile(file);
        } catch (Exception e) {
            assert false : INPUT_FILE_FORMAT_ERROR;
        }
    }

    private void createFile () {
        new File("data").mkdir();
        try {
            file = new File("data/tasks.txt");
            file.createNewFile();
        } catch (Exception e) {
            assert false : "Creation unsuccessful!.";
        }
    }

    private void readFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            readNextTask(sc);
        }
    }

    private void readNextTask(Scanner sc) {
        String[] taskInfo = sc.nextLine().split(Storage.SPLITTER);
        String taskType = taskInfo[0];
        try {
            Task toAdd = getTask(taskType, taskInfo);
            list.add(toAdd);
        } catch (Exception e) {
            assert false : INPUT_FILE_FORMAT_ERROR;
        }
    }

    private Task getTask(String taskType, String[] taskInfo) throws InvalidCommandException {
        Task toAdd;
        if (isToDo(taskType)) {
            toAdd = new ToDo(taskInfo);
        } else {
            if (isDeadline(taskType)) {
                toAdd = new Deadline(taskInfo);
            } else if (isEvent(taskType)) {
                toAdd = new Event(taskInfo);
            } else {
                assert false : INPUT_FILE_FORMAT_ERROR;
                toAdd = new ToDo("error");
            }
        }
        return toAdd;
    }

    private boolean isEvent(String taskType) {
        return taskType.charAt(0) == EVENT_INDICATOR;
    }

    private boolean isDeadline(String taskType) {
        return taskType.charAt(0) == DEADLINE_INDICATOR;
    }

    private boolean isToDo(String taskType) {
        return taskType.charAt(0) == TODO_INDICATOR;
    }

    @Override
    public TaskList getList() {
        return list;
    }

    @Override
    public void addToList(Task task) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(task.outputToFile());
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    @Override
    public void reWrite(TaskList list) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : list) {
                fw.write(task.outputToFile());
            }
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}
