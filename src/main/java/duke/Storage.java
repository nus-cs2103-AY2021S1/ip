package main.java.duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    /** Data separator to separate different data in the same entry */
    private final String dataSeparator = "\uff5c";

    private final File file;

    /** Pathname of the file. Note that loading and saving is done on the same file. */
    private final String pathName;

    /**
     * Sole constructor
     * @param pathName pathname of the file
     */
    Storage(String pathName) {
        this.pathName = pathName;
        this.file = new File(this.pathName);
    }

    /**
     * Loads data from file specified by pathName into a task list
     * @return TaskList represented by the data in the file specified by pathName. task list is empty if loading of
     * data was unsuccessful
     */
    TaskList loadTasks() {
        TaskList tasks = new TaskList();
        if (this.file.exists()) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(this.file));
                String eachDataEntry;
                while ((eachDataEntry = csvReader.readLine()) != null) {
                    String[] strings = eachDataEntry.split(this.dataSeparator);
                    this.addTaskFromData(tasks, strings);
                }
            } catch (IOException ignored) {
            }
        }
        return tasks;
    }

    void addTaskFromData(TaskList tasks, String[] strings) {
        if (strings.length > 0) {
            String taskType = strings[0];
            if (taskType.equals("todo") && strings.length == 3) {
                tasks.addTodo(strings[2], Boolean.parseBoolean(strings[1]));
            } else if (taskType.equals("deadline") && strings.length == 4) {
                tasks.addDeadline(strings[2], Boolean.parseBoolean(strings[1]), Parser.genDate(strings[3]));
            } else if (taskType.equals("event") && strings.length == 4) {
                tasks.addEvent(strings[2], Boolean.parseBoolean(strings[1]), Parser.genDate(strings[3]));
            }
        }
    }

    /**
     * Clears all the contents in the file
     * @return true if the contents are cleared successfully, false otherwise
     */
    public boolean resetFile() {
        try {
            this.file.delete();
            this.file.createNewFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Saves data in current task list into file specified by pathName
     * @param taskList Task list to be saved
     * @return true if saving is successful, false otherwise
     */
    public boolean saveTaskList(TaskList taskList) {
        try {
            FileWriter csvWriter = new FileWriter(this.file);
            for (int i = 0; i < taskList.getNumOfTasks(); i++) {
                String[] dataStringArray = taskList.getTaskAt(i).getDataString();
                for (String s : dataStringArray) {
                    csvWriter.append(s);
                    csvWriter.append(this.dataSeparator);
                }
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
