package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Encapsulates the storage of data from duke to a file in the local hard drive
 */
public class Storage {

    /** Data separator to separate different data in the same entry */
    static final String DATA_SEPARATOR = ",";

    /** File representation of the main data file specified by the pathname */
    private final File MAIN_DATA_FILE;

    /** Pathname of the main data file. Note that loading and saving is done on the same file. */
    private final String MAIN_DATA_PATH_NAME;

    /** Pathname of the archive data file. Note that loading and saving is done on the same file. */
    private final String ARCHIVE_DATA_PATH_NAME;

    /** File representation of the archive data file specified by the pathname */
    private final File ARCHIVE_DATA_FILE;

    /**
     * Constructor
     *
     * @param mainDataPathName pathname of the main data file
     * @param archiveDataPathName pathname of the archive data file
     */
    Storage(String mainDataPathName, String archiveDataPathName) {
        MAIN_DATA_PATH_NAME = mainDataPathName;
        MAIN_DATA_FILE = new File(MAIN_DATA_PATH_NAME);
        ARCHIVE_DATA_PATH_NAME = archiveDataPathName;
        ARCHIVE_DATA_FILE = new File(ARCHIVE_DATA_PATH_NAME);
    }

    /**
     * Creates new tasks based on the input strings read from the file and adds these tasks to the task list.
     *
     * @param tasks Current task list
     * @param strings Input strings read from the file
     */
    void addTaskFromData(TaskList tasks, String[] strings) {
        assert tasks != null;
        assert strings != null;

        if (strings.length == 0) {
            return;
        }

        String taskType = strings[0];
        if (taskType.equals("todo") && strings.length == 3) {
            tasks.addTodo(strings[2], Boolean.parseBoolean(strings[1]));
        } else if (taskType.equals("deadline") && strings.length == 4) {
            tasks.addDeadline(strings[2], Boolean.parseBoolean(strings[1]), Parser.genDate(strings[3]));
        } else if (taskType.equals("event") && strings.length == 4) {
            tasks.addEvent(strings[2], Boolean.parseBoolean(strings[1]), Parser.genDate(strings[3]));
        }
    }

    /**
     * Loads data from specified file.
     * Task list is empty if loading of data was unsuccessful.
     *
     * @return TaskList represented by the data in the file specified by pathName.
     */
    private TaskList loadTasks(File file) {
        TaskList tasks = new TaskList();
        if (file.exists()) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(file));
                String eachDataEntry;
                while ((eachDataEntry = csvReader.readLine()) != null) {
                    String[] strings = eachDataEntry.split(DATA_SEPARATOR);
                    addTaskFromData(tasks, strings);
                }
            } catch (IOException ignored) {
                // If file fails to load, empty task list is returned
            }
        }
        return tasks;
    }

    TaskList loadTasksFromMainData() {
        return loadTasks(MAIN_DATA_FILE);
    }

    TaskList loadTasksFromArchiveData() {
        return loadTasks(ARCHIVE_DATA_FILE);
    }

    /**
     * Clears all the contents in the main data file.
     */
    public void resetMainDataFile() {
        resetFile(MAIN_DATA_FILE);
    }

    /**
     * Clears all the contents in the archive data file.
     */
    public void resetArchiveDataFile() {
        resetFile(ARCHIVE_DATA_FILE);
    }

    /**
     * Clears all the contents in the file.
     */
    public void resetFile(File file) {
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException ignored) {
            // If the file fails to reset, no further action
        }
    }

    /**
     * Saves data in specified task list into archive data file.
     *
     * @param tasks Task list to be saved
     */
    public void saveArchive(TaskList tasks) {
        saveTasks(tasks, ARCHIVE_DATA_FILE);
    }

    /**
     * Saves data in specified task list into main data file.
     *
     * @param tasks Task list to be saved
     */
    public void saveMainTaskList(TaskList tasks) {
        saveTasks(tasks, MAIN_DATA_FILE);
    }

    /**
     * Saves data in current task list into file specified by pathName.
     *
     * @param tasks Task list to be saved
     */
    private void saveTasks(TaskList tasks, File file) {
        assert tasks != null;
        try {
            FileWriter csvWriter = new FileWriter(file);
            for (int i = 0; i < tasks.getNumOfTasks(); i++) {
                String[] dataStringArray = tasks.getTaskAt(i).getDataStrings();
                for (String s : dataStringArray) {
                    csvWriter.append(s);
                    csvWriter.append(DATA_SEPARATOR);
                }
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            // If the task list fails to save, no further action
        }
    }
}
