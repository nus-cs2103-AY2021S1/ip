package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a storage object to handle read and write.
 *
 * @author Tee Kok Siang
 */
public class Storage {
    private static final String FILE_DIR = System.getProperty("user.dir") + "/";
    private static final int TASK_TYPE_POSITION = 0;
    private static final int TASK_DONE_POSITION = 1;
    private static final String TASK_DONE_INDICATOR = "1";
    private static final int TASK_PRIORITY_POSITION = 2;
    private static final int TASK_DESCRIPTION_POSITION = 3;
    private static final int TASK_TIME_POSITION = 4;

    private final List<String> dirs;
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath File path of file that stores task data.
     */
    public Storage(String filePath) {
        dirs = Arrays.asList(filePath.split("/"));
        this.filePath = FILE_DIR;
    }

    /**
     * Loads {@link Task} data from the file.
     * Creates the file if the file is not exist and returns an empty list of task.
     * Reads task information line by line and stores the task into the list of task.
     *
     * @return List of fetched {@link Task}.
     */
    public List<Task> load() {
        try {
            for (int i = 0; i < dirs.size(); i++) {
                if (i == dirs.size() - 1) {
                    filePath = filePath.concat(dirs.get(i));
                    break;
                }
                filePath = filePath.concat(dirs.get(i)).concat("/");
                File fileDir = new File(filePath);
                // if directory not exists, create directory
                if (!fileDir.exists()) {
                    fileDir.mkdir();
                }
            }
            File file = new File(filePath);
            // if file not exists, create file
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String currentLine;
            List<Task> tasks = new ArrayList<>();
            while ((currentLine = reader.readLine()) != null) {
                tasks.add(getTaskFromLine(currentLine));
            }
            reader.close();
            return tasks;
        } catch (IOException ioException) {
            System.out.println(String.format("IOException: %s", ioException.getLocalizedMessage()));
        } catch (DukeException dukeException) {
            System.out.println(dukeException.getLocalizedMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Writes {@link Task} data into the file.
     * Writes task information line by line into the file.
     */
    public void writeFile(TaskList taskList) {
        assert taskList != null : "TaskList should not be null";
        List<Task> tasks = taskList.getTasks();
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            String taskInfo = task.toFileString() + "\n";
            stringBuilder.append(taskInfo);
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException ioException) {
            System.out.println("IOException: " + ioException.getLocalizedMessage());
        }
    }

    private Task getTaskFromLine(String line) throws DukeException {
        assert line != null : "Line should not be null";
        List<String> taskInfoWords = Arrays.asList(line.split(" \\| "));
        Task task;
        String taskType = taskInfoWords.get(TASK_TYPE_POSITION);
        boolean isDeadline = taskType.equals(Deadline.DEADLINE_KEYWORD);
        boolean isEvent = taskType.equals(Event.EVENT_KEYWORD);
        boolean isTodo = taskType.equals(Todo.TODO_KEYWORD);
        if (isDeadline) {
            task = new Deadline(taskInfoWords.get(TASK_DESCRIPTION_POSITION),
                    taskInfoWords.get(TASK_TIME_POSITION));
        } else if (isEvent) {
            task = new Event(taskInfoWords.get(TASK_DESCRIPTION_POSITION),
                    taskInfoWords.get(TASK_TIME_POSITION));
        } else if (isTodo) {
            task = new Todo(taskInfoWords.get(TASK_DESCRIPTION_POSITION));
        } else {
            throw new DukeException("Invalid task type when reading task information from the file");
        }
        boolean isTaskDone = taskInfoWords.get(TASK_DONE_POSITION).equals(TASK_DONE_INDICATOR);
        if (isTaskDone) {
            task.markDone();
        }
        String priorityLevel = taskInfoWords.get(TASK_PRIORITY_POSITION);
        task.markPriority(Task.Priority.valueOf(priorityLevel));
        return task;
    }
}
