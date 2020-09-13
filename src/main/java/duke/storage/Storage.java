package duke.storage;

import static duke.util.Keyword.BASE_DIRECTORY;
import static duke.util.Keyword.CSV_FORMAT;
import static duke.util.Keyword.CSV_HEADER;
import static duke.util.Keyword.FILE_NAME;
import static duke.util.Keyword.FOLDER_NAME;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import duke.exception.FileUpdateFailException;
import duke.exception.InvalidFileFormatException;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Handles the interactions with the user's CSV file.
 * Includes creating, updating file and retrieving list of tasks from CSV file.
 */
public class Storage {



    private final String dataDirectory;
    private final String filePath;

    /**
     * Initializes the storage object and create a new file.
     */
    public Storage() {
        dataDirectory = System.getProperty(BASE_DIRECTORY) + FOLDER_NAME;
        filePath = dataDirectory + FILE_NAME;
        createFile();
    }

    /**
     * Creates the tasklist.csv file in the storage.
     * Creation works by creating the folder first (if not found), then afterwards creating the file (if it does not
     * already exist).
     */
    private void createFile() {
        try {
            File newDirectory = new File(dataDirectory);
            newDirectory.mkdir();
            File newFile = new File(filePath);
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the list of tasks from the tasklist CSV file, if any.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String header = br.readLine();

            if (header != null) {
                String line = br.readLine();
                while (line != null) {
                    Task newTask = CsvConverter.parseToTask(line);
                    taskList.add(newTask);
                    line = br.readLine();
                }
            }
            return taskList;
        } catch (IOException | InvalidFileFormatException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return taskList;
        }
    }

    /**
     * Updates the task list.
     *
     * @param tasks Task list.
     */
    public void update(TaskList tasks) throws FileUpdateFailException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            StringBuilder stringBuilder = new StringBuilder(CSV_HEADER);
            for (Task task : tasks.getTasks()) {
                stringBuilder.append(convertToCsvFormat(task));
            }
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new FileUpdateFailException();
        }
    }

    /**
     * Converts the task to a csv format to be stored in a .csv file.
     *
     * @param task Task object.
     * @return String representation of the task in .csv format.
     */
    private String convertToCsvFormat(Task task) {
        return String.format(CSV_FORMAT,
            task.getTaskName(), task.getDescription(), task.getTimeFrame(), task.getTime(), task.getStatus());
    }
}
