package duke;

import duke.task.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Utility class to read and write data from and to the hard disk.
 */
public class Storage {

    File dataDirectory;
    String dataDirectoryPath;
    File dataFile;
    String dataFilePath;

    /**
     * Initialises a new Storage object.
     */
    public Storage() {
        dataDirectoryPath = Paths.get("data").toString();
        dataDirectory = new File(dataDirectoryPath);
        dataFilePath = Paths.get("data", "duke.txt").toString();
        dataFile = new File(dataFilePath);
    }
    
    private void alertDirectoryNotFound() {
        System.out.println("Cannot access data directory.");
    }

    private void alertCorruptedData() {
        System.out.println("Data file is corrupted.");
    }

    /**
     * Populates a TaskList with data saved in the hard disk. If the data directory or file does not exist,
     * it will be created.
     * @param taskList List to be populated.
     */
    public void loadData(TaskList taskList) {
        dataDirectory.mkdirs();
        boolean toLoadFromDataFile;
        
        try {
            toLoadFromDataFile = !dataFile.createNewFile();
        } catch (IOException e) {
            alertDirectoryNotFound();
            toLoadFromDataFile = false;
        }
        
        if (toLoadFromDataFile) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(dataFilePath));
                String line;
                while ((line = br.readLine()) != null) {

                    String[] taskData = line.split("\\|");
                    String taskType = taskData[1];
                    Task task = null;

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
                    }
                    taskList.addTask(task);
                }
            } catch (IOException | ArrayIndexOutOfBoundsException e) {
                alertCorruptedData();
            }
        }
    }

    private void appendData(String data)  {
        try {
            FileWriter writer = new FileWriter(dataFilePath, true);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            alertCorruptedData();
        }
    }

    private void overwriteData(String data) {
        try {
            FileWriter writer = new FileWriter(dataFilePath);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            alertCorruptedData();
        }
    }

    public void saveTodo(ToDo task) {
        String line = task.getUniqueId() + "|" + task.getTaskType() + "|" + task.isDone() + "|"
                + task.getDescription() + "\n";
        appendData(line);
    }

    public void saveDeadline(Deadline task)  {
        String line = task.getUniqueId() + "|" + task.getTaskType() + "|" + task.isDone() + "|"
                + task.getDescription() + "|" + task.getTime() + "\n";
        appendData(line);
    }

    public void saveEvent(Event task) {
        String line = task.getUniqueId() + "|" + task.getTaskType() + "|" + task.isDone() + "|"
                + task.getDescription() + "|" + task.getTime() + "\n";
        appendData(line);
    }

    public void doneTask(Task task) {
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
            alertCorruptedData();
        }

    }

    public void deleteTask(Task task) {
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
            alertCorruptedData();
        }
    }
}