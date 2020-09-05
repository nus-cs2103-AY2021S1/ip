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
            String[] curr = scanner.nextLine().split("---");
            String type = curr[0];
            boolean isDone = Integer.parseInt(curr[1]) == 1;
            String activity = curr[2];
            String description;

            switch (type) {
            case "T":
                currTask = new Todo(activity, i, isDone);
                break;
            case "D":
            case "E":
                description = activity + " " + curr[3];
                currTask = readTask(description, type, i, isDone);
                break;
            default:
                break;
            }
            tasks.add(currTask);
        }
        return tasks;
    }

    /**
     * Abstracts the process of reading deadline/event tasks.
     * @return Task - either Deadline/Event
     */
    private Task readTask(String description, String type, int index, boolean isComplete)
            throws DukeInvalidTimeException {
        return type.equals("D")
                ? new Deadline(description, index, isComplete)
                : new Event(description, index, isComplete);
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

            switch (task.getType()) {
            case TODO:
                line = String.format("T---%d---%s", done, description);
                break;
            case DEADLINE:
            case EVENT:
                int idx = description.indexOf('/');
                String activity = description.substring(0, idx - 1);
                String timing = description.substring(idx);
                line = formatTask(task, idx, activity, timing);
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
     * @param index Index of task
     * @param activity Content of task
     * @param timing Time of task
     * @return String saved into disk
     */
    private String formatTask(Task task, int index, String activity, String timing) {
        String type = task.getType() == TaskType.DEADLINE ? "D" : "E";
        return String.format("%s---%d---%s---%s", type, index, activity, timing);
    }

}
