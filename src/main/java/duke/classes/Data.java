package duke.classes;

import duke.exceptions.DukeInvalidTimeException;
import duke.tasks.*;

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
        //System.out.println("Path being located");
        this.path = Paths.get("src/main/data/duke.txt").toAbsolutePath();
        if (Files.notExists(this.path)) {
            new File(String.valueOf(path)).createNewFile();
        }
    }

    /**
     * Method to read the data stored on local disk.
     *
     * @return List of tasks saved
     * @throws FileNotFoundException For missing files
     * @throws DukeInvalidTimeException For invalid times saved for event/deadline events
     * @throws ArrayIndexOutOfBoundsException For incorrectly typed descriptions for deadline/event tasks
     */
    public List<Task> loadData() throws FileNotFoundException, DukeInvalidTimeException, ArrayIndexOutOfBoundsException {
        List<Task> todoList = new ArrayList<>();
        Scanner scanner = new Scanner(path.toFile());

        for (int i = 1; scanner.hasNextLine(); i++) {
            Task currTask = null;
            String[] curr = scanner.nextLine().split("---");
            String task = curr[0];
            boolean isDone = Integer.parseInt(curr[1]) == 1;
            String activity = curr[2];
            String description;

            switch (task) {
            case "T":
                currTask = new Todo(activity, i, isDone);
                break;
            case "D":
                description = activity + " " + curr[3];
                currTask = new Deadline(description, i, isDone);
                break;
            case "E":
                description = activity + " " + curr[3];
                currTask = new Event(description, i, isDone);
                break;
            }
            todoList.add(currTask);
        }
        return todoList;
    }

    /**
     * Method that overrides the host file with data of the latest content in the list.
     *
     * @param tasks List of tasks
     * @throws IOException Missing file to write into
     */
    public void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(this.path.toString());

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
                line = String.format("%s---%d---%s---%s",
                        task.getType() == TaskType.DEADLINE ? "D" : "E", done, activity, timing);
                break;
            }
            writer.write(line + "\n");
        }
        writer.close();
    }

}
