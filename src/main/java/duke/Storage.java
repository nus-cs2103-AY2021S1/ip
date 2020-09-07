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
    private final String DATA_SEPARATOR = "\uff5c";

    /** File representation of the file specified by the pathname */
    private final File FILE;

    /** Pathname of the file. Note that loading and saving is done on the same file. */
    private final String PATH_NAME;

    /**
     * Constructor
     *
     * @param pathName pathname of the file
     */
    Storage(String pathName) {
        PATH_NAME = pathName;
        FILE = new File(PATH_NAME);
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
     * Loads data from file specified by pathName into a task list.
     * Task list is empty if loading of data was unsuccessful.
     *
     * @return TaskList represented by the data in the file specified by pathName.
     */
    TaskList loadTasks() {
        TaskList tasks = new TaskList();
        if (FILE.exists()) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(FILE));
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

    /**
     * Clears all the contents in the file.
     */
    public void resetFile() {
        try {
            FILE.delete();
            FILE.createNewFile();
        } catch (IOException ignored) {
            // If the file fails to reset, no further action
        }
    }

    /**
     * Saves data in current task list into file specified by pathName.
     *
     * @param taskList Task list to be saved
     */
    public void saveTaskList(TaskList taskList) {
        assert taskList != null;
        try {
            FileWriter csvWriter = new FileWriter(FILE);
            for (int i = 0; i < taskList.getNumOfTasks(); i++) {
                String[] dataStringArray = taskList.getTaskAt(i).getDataStrings();
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
