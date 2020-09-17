package duke.logic;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.CommonString;
import duke.exception.DukeFileNotFoundException;
import duke.exception.DukeIoException;
import duke.task.DeadlineTask;
import duke.task.DukeTask;
import duke.task.EventTask;
import duke.task.TodoTask;



/**
 * Represents the Storage of Duke.
 * Manages the storing/loading of data from data file.
 */
public class StorageManager {

    // FILE AND DATA STORAGE
    private static final String FILE_DATA_SEPARATOR = "|";
    private final String filePath;

    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    public StorageManager() {
        this.filePath = CommonString.DUKE_FILE_PATH.toString();
    }

    /**
     * Loads user's <code>DukeTask</code> from the data file.
     * Location of the <code>DukeTask</code> is extracted from the <code>filePath</code>.
     *
     * @return ArrayList denoting the list of <code>DukeTask</code>
     * @throws DukeFileNotFoundException If the file to load from does not exist
     */
    public ArrayList<DukeTask> loadData() throws DukeFileNotFoundException {
        ArrayList<DukeTask> dataList = new ArrayList<>();

        File dataFile = new File(filePath);
        // load parent files
        File directory = new File(dataFile.getParentFile().getAbsolutePath());
        directory.mkdirs();

        // generate data file
        try {
            dataFile.createNewFile();
            Scanner fileScanner = new Scanner(dataFile);
            while (fileScanner.hasNextLine()) {
                // regenerate the DukeTasks
                String savedTask = fileScanner.nextLine();
                String[] taskData = savedTask.split("\\|");
                DukeTask task;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                        CommonString.DUKE_DATETIME_FORMAT.toString());
                switch (taskData[0]) {
                case "T":
                    task = new TodoTask(taskData[2]);
                    break;
                case "E":
                    task = new EventTask(taskData[2], LocalDateTime.parse(taskData[3], formatter));
                    break;
                default: // "D"
                    task = new DeadlineTask(taskData[2], LocalDateTime.parse(taskData[3], formatter));

                }
                if (taskData[1].equals("1")) {
                    task.markAsDone();
                }
                dataList.add(task);
            }
        } catch (IOException e) {
            throw new DukeFileNotFoundException("Problem with creating data file\n" + e.getMessage());
        }
        return dataList;
    }

    /**
     * Saves user's <code>DukeTask</code> into the data file.
     * Location of the <code>DukeTask</code> is extracted from the <code>filePath</code>
     * FORMAT OF DATA IS GIVEN BY:
     * [TYPE]|[DONE]|[DESCRIPTION]|[DATETIME (if applicable)].
     * TYPE: T,E,D. DONE: 1 or 0
     *
     * @throws DukeIoException If saving of the data fails
     */
    public void saveData(ArrayList<DukeTask> dataList) throws DukeIoException {
        assert dataList != null : "saveDate input dataList cannot be null";
        String output = generateDataString(dataList);
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            throw new DukeIoException(e.getMessage());
        }
    }

    /**
     * Generates the String representing all data in the datafile
     * @param dataList List containing all DukeTask
     * @return String denoting output
     */
    private String generateDataString(ArrayList<DukeTask> dataList) {
        StringBuilder dataString = new StringBuilder();
        dataList.forEach(task ->
                dataString.append(generateAdditionString(task)).append(System.lineSeparator()));
        return dataString.toString();
    }

    /**
     * Generates the String to be added to the datafile
     * @param task DukeTask
     * @return String denoting output
     */
    private String generateAdditionString(DukeTask task) {
        String output = (task.getDoneStatus() ? "1" : "0") + FILE_DATA_SEPARATOR + task.getDescription();
        if (task instanceof TodoTask) {
            output = "T" + FILE_DATA_SEPARATOR + output;
        } else if (task instanceof EventTask) {
            output = "E" + FILE_DATA_SEPARATOR + output + FILE_DATA_SEPARATOR
                    + ((EventTask) task).getDateTimeString();
        } else {
            output = "D" + FILE_DATA_SEPARATOR + output + FILE_DATA_SEPARATOR
                    + ((DeadlineTask) task).getDateTimeString();
        }
        return output;
    }
}
