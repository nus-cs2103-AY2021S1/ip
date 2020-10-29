package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a Storage class that handles saving and retrieving of stored data, to initialise the task list.
 */
public class Storage {

    /**
     * Path of the root directory.
     */
    private String rootPath;

    /**
     * Path of the directory where the storage file resides.
     */
    private String directoryPath;

    /**
     * Path of the storage file.
     */
    private String filePath;

    /**
     * New line string that depends on user's operating system.
     */
    private String lineSeparator = System.getProperty("line.separator");

    /**
     * Constructs a new Storage object.
     */
    public Storage() {
        this.rootPath = System.getProperty("user.dir");
        this.directoryPath = this.rootPath + File.separator + "data";
        this.filePath = this.directoryPath + File.separator + "data.txt";
    }

    /**
     * Creates a new file to store data if it does not exist yet.
     */
    public void createFile() {
        File dataFolder = new File(this.directoryPath);
        try {
            // Make directory if it doesn't exist yet
            dataFolder.mkdir();
            File dataFile = new File(this.directoryPath, "data.txt");
            // Make file if it doesn't exist yet
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves data in the current taskList to the storage file.
     *
     * @param taskList Target taskList whose data we want to store.
     * @throws DukeException Encapsulates exceptions specific to Mrs Dino.
     */
    public void saveTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter("data/data.txt");
            for (Task task: taskList.getList()) {
                int bit = task.isCompleted() ? 1 : 0;
                String taskType = task.getType();
                String lineToWrite;
                switch (taskType) {
                case("T"): {
                    lineToWrite = taskType + " | " + bit + " | " + task.getTaskName();
                    break;
                }
                case ("E"):
                case ("D"):
                    lineToWrite = taskType + " | " + bit + " | " + task.getTaskName() + " | " + task.getDateAndTime();
                    break;
                default: {
                    throw new DukeException("Write to file error");
                }
                }
                writer.write(lineToWrite + this.lineSeparator);
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Save data error");
        }
    }

    /**
     * Retrieves data store in the storage file and loads them into the task list.
     *
     * @param taskList TaskList that we want to load the data into.
     * @throws FileNotFoundException Exception when file is not found.
     */
    public void loadFromTextFile(TaskList taskList) throws FileNotFoundException {
        File file = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] details = currentLine.split("\\|");
            switch(details[0].trim()) {
            // Trims away whitespaces at the start and end of string
            case "T":
                taskList.addTodo(details[1].trim(), details[2].trim());
                break;
            case "D":
                taskList.addDeadline(details[1].trim(), details[2].trim(), details[3].trim());
                break;
            case "E":
                taskList.addEvent(details[1].trim(), details[2].trim(), details[3].trim());
                break;
            default:
                break;
            }
        }
        s.close();
    }
}
