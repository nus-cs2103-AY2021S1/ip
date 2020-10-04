package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Utility class to read and write data from and to the hard disk.
 */
public class Storage {

    private final File dataDirectory;
    private final File dataFile;
    private final String dataFilePath;

    /**
     * Initialises a new {@code Storage} object.
     */
    public Storage() {
        String dataDirectoryPath = Paths.get("data").toString();
        dataDirectory = new File(dataDirectoryPath);
        dataFilePath = Paths.get("data", "duke.txt").toString();
        dataFile = new File(dataFilePath);
    }

    /**
     * Populates a {@link TaskList} with data saved in the hard disk. If the data directory or file does not exist,
     * it will be created.
     *
     * @param taskList List to be populated.
     * @throws DukeException If the data fails to load.
     */
    public void loadData(TaskList taskList) throws DukeException {
        dataDirectory.mkdirs();
        boolean toLoadFromDataFile;

        try {
            toLoadFromDataFile = !dataFile.createNewFile();
        } catch (IOException e) {
            toLoadFromDataFile = false;
        }

        if (toLoadFromDataFile) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(dataFilePath));
                String line;
                while ((line = br.readLine()) != null) {

                    String[] taskData = line.split("\\|");
                    String taskType = taskData[0];
                    Task task;

                    switch (taskType) {
                    case "T":
                        task = ToDo.loadFromData(taskData);
                        break;
                    case "D":
                        task = Deadline.loadFromData(taskData);
                        break;
                    case "E":
                        task = Event.loadFromData(taskData);
                        break;
                    default:
                        throw new DukeException("Invalid argument detected in data file");
                    }
                    taskList.addTask(task);
                }
            } catch (IOException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves the contents of a {@link TaskList} to the data saved in the hard disk.
     *
     * @param tasks List to be saved.
     * @throws DukeException If the data fails to save.
     */
    public void saveData(TaskList tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(dataFilePath);
            writer.write(tasks.serializeList());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write to data file.");
        }
    }
}
