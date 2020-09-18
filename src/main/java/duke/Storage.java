package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Stores all storage related methods and variables.
 */
public class Storage {

    private List<Task> tasks = new ArrayList<>();
    private Path filePath;
    private File dataFile;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from file.
     * @return List of tasks in file.
     * @throws DukeException if file cannot be created or reader cannot read next line.
     */
    public List<Task> load() throws DukeException {

        Path directoryPath = Paths.get("data");
        boolean directoryExists = Files.exists(directoryPath);
        dataFile = new File(filePath.toString());
        boolean fileExists = Files.exists(Paths.get("data", "duke.txt"));

        if (!directoryExists) {
            File directory = new File(directoryPath.toString());
            directory.mkdir();
            try {
                dataFile.createNewFile();
                return tasks;
            } catch (IOException e) {
                throw new DukeException("File cannot be created");
            }
        } else if (!fileExists) {
            try {
                dataFile.createNewFile();
                return tasks;
            } catch (IOException e) {
                throw new DukeException("File cannot be created");
            }
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()));
                String line = reader.readLine();
                while (line != null) {
                    readDataToList(tasks, line);
                    line = reader.readLine();
                }
                return tasks;
            } catch (IOException e) {
                throw new DukeException("BufferedReader I/O Exception");
            }
        }
    }

    /**
     * Converts lines in text file to task objects and adds them to list.
     * @param tasks list of objects to add the new task objects to.
     * @param line Line to be converted to a task object.
     */
    private void readDataToList(List<Task> tasks, String line) {

        String[] stringArray = line.split(" \\| ");
        String taskType = stringArray[0];
        boolean isDone = stringArray[1].equals("1");
        Task task = null;

        switch (taskType) {
        case "T":
            task = new Todo(stringArray[2]);
            break;
        case "D":
            task = new Deadline(stringArray[2], stringArray[3]);
            break;
        case "E":
            task = new Event(stringArray[2], stringArray[3]);
            break;
        default:
            break;
        }

        tasks.add(task);

        if (isDone) {
            task.markAsDone();
        }

    }

    /**
     * Saves list of tasks of users to file.
     * @param tasks List of tasks of user.
     * @throws DukeException if unable to write to file.
     */
    public void saveListToFile(List<Task> tasks) throws DukeException {

        try {
            FileWriter writer = new FileWriter(dataFile, false);

            for (Task t : tasks) {
                writer.write(t.convertToFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Writing error");
        }
    }
}
