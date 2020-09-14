package duke.storage;

import static duke.util.Keyword.BASE_DIRECTORY;
import static duke.util.Keyword.CSV_FORMAT;
import static duke.util.Keyword.CSV_HEADER;
import static duke.util.Keyword.FILE_NAME;
import static duke.util.Keyword.FOLDER_NAME;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exception.FileUpdateFailException;
import duke.exception.InvalidFileFormatException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Handles the interactions with the user's CSV file.
 * Includes creating, updating file and retrieving list of tasks from CSV file.
 */
public class Storage {

    private final String dataDirectory;
    private final String filePath;
    private final Ui ui;

    /**
     * Initializes the storage object and create a new file.
     */
    public Storage() {
        dataDirectory = System.getProperty(BASE_DIRECTORY) + FOLDER_NAME;
        filePath = dataDirectory + FILE_NAME;
        ui = new Ui();
        createFile();
    }

    /**
     * Creates the tasklist.csv file in the storage.
     * Creation works by creating the folder first (if not found), then afterwards creating the file (if it does not
     * already exist).
     */
    private void createFile() {
        makeFolder();
        makeFile();
    }

    /**
     * Creates the folder in storage (if it does not already exists).
     */
    private void makeFolder() {
        File newDirectory = new File(dataDirectory);
        newDirectory.mkdir();
    }

    /**
     * Creates the file in storage (if it does not already exists).
     */
    private void makeFile() {
        try {
            File newFile = new File(filePath);
            newFile.createNewFile();
        } catch (IOException e) {
            ui.fileCreationError();
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
            assert !filePath.isBlank();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            loadTasks(taskList, bufferedReader);
            return taskList;
        } catch (IOException | InvalidFileFormatException e) {
            System.out.println(e.getMessage());
            return taskList;
        }
    }

    /**
     * Loads the tasks from the taskList if header is not empty.
     *
     * @param taskList Task List.
     * @param bufferedReader BufferedReader.
     * @throws IOException If there is an error when reading the file.
     * @throws InvalidFileFormatException File is formatted wrongly.
     */
    private void loadTasks(ArrayList<Task> taskList, BufferedReader bufferedReader) throws IOException,
        InvalidFileFormatException {

        String header = bufferedReader.readLine();
        if (header != null) {
            readFile(taskList, bufferedReader);
        }
    }

    /**
     * Reads the storage file and stores each task into the task list.
     *
     * @param taskList Task list.
     * @param bufferedReader BufferedReader.
     * @throws IOException If there is an error when reading the file.
     * @throws InvalidFileFormatException File is formatted wrongly.
     */
    private void readFile(ArrayList<Task> taskList, BufferedReader bufferedReader) throws IOException,
        InvalidFileFormatException {

        String line = bufferedReader.readLine();
        while (line != null) {
            Task newTask = CsvConverter.parseToTask(line);
            taskList.add(newTask);
            line = bufferedReader.readLine();
        }
    }

    /**
     * Updates the task list in the storage.
     *
     * @param taskList Task list.
     */
    public void updateFile(TaskList taskList) throws FileUpdateFailException {
        try {
            assert !filePath.isBlank();
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            ArrayList<Task> taskArrayList = taskList.getTasks();
            writeToFile(taskArrayList, bufferedWriter);
        } catch (IOException e) {
            throw new FileUpdateFailException();
        }
    }

    /**
     * Saves the tasks to the storage file.
     *
     * @param taskArrayList List of Tasks.
     * @param bufferedWriter BufferedWriter which writes into storage file.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void writeToFile(ArrayList<Task> taskArrayList, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(CSV_HEADER);
        for (Task task : taskArrayList) {
            String taskInCsvFormat = convertToCsvFormat(task);
            bufferedWriter.append(taskInCsvFormat);
        }
        bufferedWriter.close();
    }

    /**
     * Converts the task to a csv format to be stored in a .csv file.
     *
     * @param task Task object.
     * @return String representation of the task in .csv format.
     */
    private String convertToCsvFormat(Task task) {
        assert task != null;
        return String.format(CSV_FORMAT,
            task.getTaskName(), task.getDescription(), task.getTimeFrame(), task.getTime(), task.getStatus());
    }
}
