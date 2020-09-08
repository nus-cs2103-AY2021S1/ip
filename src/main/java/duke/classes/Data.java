package duke.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeInvalidTimeException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskType;
import duke.tasks.Todo;

/**
 * Data class that handles the storage and loading of the tasks stored
 * on the list by writing its contents onto local disk.
 */
public class Data {
    final Path path;

    /**
     * Constructor that reads file from the path directory, else it creates a file on the directory instead.
     *
     * @param path File path for the data stored
     * @throws IOException For missing files
     */
    public Data(String path) throws IOException {
        this.path = Paths.get("src/main/data/duke.txt").toAbsolutePath();
        if (Files.notExists(this.path)) {
            new File(String.valueOf(path)).createNewFile();
        }
    }

    /**
     * Constructor that creates file and directory, if directory is not found.
     *
     * @throws IOException For missing files
     */

    Data() throws IOException {
        // Initialise new default path from scratch
        path = Path.of("src/main/data/duke.txt");
        new File("src/main/data").mkdirs();
        new File(path.toString()).createNewFile();
    }

    /**
     * Method to read the data stored on local disk.
     *
     * @return List of tasks saved
     * @throws FileNotFoundException For missing files
     * @throws DukeInvalidTimeException For invalid times saved for event/deadline events
     * @throws ArrayIndexOutOfBoundsException For incorrectly typed descriptions for deadline/event tasks
     */
    public List<Task> loadData() throws FileNotFoundException,
            DukeInvalidTimeException, ArrayIndexOutOfBoundsException {
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(path.toFile());
        // Reading tasks from file
        for (int i = 1; scanner.hasNextLine(); i++) {
            Task currTask = null;
            // Assertion: The data has been saved in expected format on disk already
            assert scanner.nextLine().substring(1, 4).equals("---") : "Data has not been saved in proper format";
            String[] currStrings = scanner.nextLine().split("---");
            String type = currStrings[0];
            boolean isDone = Integer.parseInt(currStrings[1]) == 1;
            String activity = currStrings[2];
            String description;
            String tag;
            // Loading tasks from file
            switch (type) {
            case "T":
                tag = currStrings[3];
                currTask = new Todo(activity, i, isDone, tag);
                break;
            case "D":
            case "E":
                tag = currStrings[4];
                description = activity + " " + currStrings[3];
                currTask = readTask(description, type, i, isDone, tag);
                break;
            default:
                break;
            }
            assert currTask != null;
            boolean isTodo = currTask.getType() == TaskType.TODO;
            boolean isDeadline = currTask.getType() == TaskType.DEADLINE;
            boolean isEvent = currTask.getType() == TaskType.EVENT;
            // Assertion: Tasks belong to the three main defined types
            assert isTodo || isDeadline || isEvent : "Task(s) don't belong to the main types of tasks";
            tasks.add(currTask);
        }
        return tasks;
    }

    /**
     * Abstracts the process of reading deadline/event tasks.
     * @return Task - either Deadline/Event
     */
    private Task readTask(String description, String type, int index, boolean isComplete, String tag)
            throws DukeInvalidTimeException {
        return type.equals("D")
                ? new Deadline(description, index, isComplete, tag)
                : new Event(description, index, isComplete, tag);
    }

    /**
     * Method that overrides the host file with data of the latest content in the list.
     *
     * @param tasks List of tasks
     * @throws IOException Missing file to write into
     */
    public void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(path.toString());
        // Writing tasks into disk
        for (Task task : tasks) {
            String line = "";
            int done = task.hasDone() ? 1 : 0;
            String description = task.getDescription();
            String tag = task.getTag();
            // Saving tasks to disk in specific format
            switch (task.getType()) {
            case TODO:
                line = String.format("T---%d---%s---%s", done, description, tag);
                break;
            case DEADLINE:
            case EVENT:
                int idx = description.indexOf('/');
                String activity = description.substring(0, idx - 1);
                String timing = description.substring(idx);
                line = formatTask(task, done, activity, timing, tag);
                break;
            default:
                break;
            }
            writer.write(line + "\n");
        }
        writer.close();
    }

    /**
     * Outputs the format of string for deadline/event tasks that are saved into disk.
     * @param task Task
     * @param done Status of task
     * @param activity Content of task
     * @param timing Time of task
     * @return String saved into disk
     */
    private String formatTask(Task task, int done, String activity, String timing, String tag) {
        String type = task.getType() == TaskType.DEADLINE ? "D" : "E";
        return String.format("%s---%d---%s---%s---%s", type, done, activity, timing, tag);
    }

}
