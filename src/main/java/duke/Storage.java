package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores all storage related methods and variables.
 */
public class Storage {

    List<Task> tasks = new ArrayList<>();
    String filePath;
    File dataFile;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from file.
     * @return List of tasks in file.
     * @throws DukeException if file cannot be created or reader cannot read next line.
     */
    public List<Task> load() throws DukeException{

        Path directoryPath = Paths.get("data");
        boolean directoryExists = Files.exists(directoryPath);
        dataFile = new File(filePath);
        boolean fileExists = Files.exists(Paths.get("data", "duke.txt"));

        if (!directoryExists) {
            File directory = new File(directoryPath.toString());
            directory.mkdir();
            try {
                dataFile.createNewFile();
                return null;
            } catch (IOException e) {
                throw new DukeException("WHAT WHATS GOING ON");
            }
        } else if (!fileExists) {
            try {
                dataFile.createNewFile();
                return null;
            } catch (IOException e) {
                throw new DukeException("WHAT WHATS GOING ON");
            }
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()));
                String line;
                while ((line = reader.readLine()) != null) {
                    convertLineToTasks(tasks, line);
                }
                return tasks;
            } catch (IOException e) {
                throw new DukeException("WHAT WHATS GOING ON");
            }
        }
    }

    /**
     * Converts lines in text file to task objects and adds them to list.
     * @param tasks list of objects to add the new task objects to.
     * @param line Line to be converted to a task object.
     */
    private void convertLineToTasks(List<Task> tasks, String line) {

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
                writer.write(t.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Writing error");
        }
    }
}
