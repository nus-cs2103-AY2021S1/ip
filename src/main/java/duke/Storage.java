package duke;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Storage class to manage the saving of TaskList into hard disk
 */
public class Storage {

    private final Path filePath;
    private final Path folderPath;

    public Storage() {
        String currentRelativePath = Paths.get(".").toString();
        filePath = Paths.get(currentRelativePath, "data", "duke.txt");
        folderPath = Paths.get(currentRelativePath, "data");
    }

    /**
     * Loads the TaskList that is stored in <kbd>data/duke.text</kbd> file.
     * Handles the cases where either file or folder is not created.
     * @return a TaskList to be used by the program
     */
    public TaskList load() {
        TaskList taskList = new TaskList();
        try {
            createPath();
            List<String> tasksInFile = Files.readAllLines(filePath, Charset.defaultCharset());
            taskList = this.loadTaskList(tasksInFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Creates the directory and/or file if they have not been created yet
     * @throws IOException if folderPath or filePath are parsed wrongly
     */
    public void createPath() throws IOException {
        if (Files.notExists(folderPath)) {
            Files.createDirectory(folderPath);
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /**
     * Returns a loaded TaskList for program data storage.
     * An empty TaskList is return if content in file is empty;
     *
     * @param tasksInFile the encoded text used to store the TaskList
     * @return a TaskList based on the tasks recorded in the file
     */
    public TaskList loadTaskList(List<String> tasksInFile) {
        List<Task> tasksLoaded = new ArrayList<>();
        for (String s : tasksInFile) {
            String[] taskContentArr = s.split(" [|] ");
            String taskSchedule;
            boolean taskIsDone = taskContentArr[1].equals("1");
            switch(taskContentArr[0]) {
            case ("T"):
                tasksLoaded.add(new Todo(taskContentArr[2], taskIsDone));
                break;
            case ("D"):
                taskSchedule = taskContentArr[3];
                tasksLoaded.add(new Deadline(taskContentArr[2], taskIsDone, taskSchedule));
                break;
            case ("E"):
                taskSchedule = taskContentArr[3];
                tasksLoaded.add(new Event(taskContentArr[2], taskIsDone, taskSchedule));
                break;
            default:
                break;
            }
        }
        return new TaskList(tasksLoaded);
    }

    /**
     * Stores a Task List in a predetermined filePath
     * @param taskList the taskList object to be saved
     */
    public void save(TaskList taskList) {
        try {
            List<String> formattedTasks = taskList.formatTaskList();
            Files.write(this.filePath, formattedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
