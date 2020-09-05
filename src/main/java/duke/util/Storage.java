package duke.util;

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
     * @param taskList List to be populated.
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
                    String taskType = taskData[1];
                    Task task;

                    switch (taskType) {
                    case "T":
                        task = new ToDo(taskData[0], Boolean.parseBoolean(taskData[2]), taskData[3]);
                        break;
                    case "D":
                        task = new Deadline(taskData[0], Boolean.parseBoolean(taskData[2]), taskData[3], taskData[4]);
                        break;
                    case "E":
                        task = new Event(taskData[0], Boolean.parseBoolean(taskData[2]), taskData[3], taskData[4]);
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

    private void appendData(String data) throws DukeException {
        try {
            FileWriter writer = new FileWriter(dataFilePath, true);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write to data file.");
        }
    }

    private void overwriteData(String data) throws DukeException {
        try {
            FileWriter writer = new FileWriter(dataFilePath);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write to data file");
        }
    }

    public void saveTodo(ToDo task) throws DukeException {
        String line = task.getUniqueId() + "|" + task.getTaskType() + "|" + task.isDone() + "|"
                + task.getDescription() + "\n";
        appendData(line);
    }

    public void saveDeadline(Deadline task) throws DukeException {
        String line = task.getUniqueId() + "|" + task.getTaskType() + "|" + task.isDone() + "|"
                + task.getDescription() + "|" + task.getTime() + "\n";
        appendData(line);
    }

    public void saveEvent(Event task) throws DukeException {
        String line = task.getUniqueId() + "|" + task.getTaskType() + "|" + task.isDone() + "|"
                + task.getDescription() + "|" + task.getTime() + "\n";
        appendData(line);
    }

    public void doneTask(Task task) throws DukeException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFilePath));
            StringBuilder newData = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(task.getUniqueId())) {
                    line = line.replaceFirst("false", "true");
                }
                newData.append(line).append("\n");
            }
            br.close();
            overwriteData(newData.toString());
        } catch (Exception e) {
            throw new DukeException("Failed to update task in data.");
        }

    }

    public void deleteTask(Task task) throws DukeException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFilePath));
            StringBuilder newData = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.contains(task.getUniqueId())) {
                    newData.append(line).append("\n");
                }
            }
            br.close();
            overwriteData(newData.toString());
        } catch (Exception e) {
            throw new DukeException("Failed to delete task in data.");
        }
    }
}
