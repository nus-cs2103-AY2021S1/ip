package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

class Storage {

    /** Pathname of the file. Note that loading and saving is done on the same file. */
    private final String pathName;

    /** Data separator to separate different data in the same entry */
    private final String dataSeparator = "\uff5c";

    /**
     * Sole constructor
     * @param pathName pathname of the file
     */
    Storage(String pathName) {
        this.pathName = pathName;
    }

    /**
     * Saves data in current task list into file specified by pathName
     * @throws java.io.IOException If file save is unsuccessful
     */
    void saveTaskList(TaskList taskList) throws java.io.IOException {
        FileWriter csvWriter = new FileWriter(this.pathName);
        for (int i = 0; i < taskList.getNumOfTasks(); i++) {
            String[] dataStringArray = taskList.getTaskAt(i).getDataString();
            for (String s : dataStringArray) {
                csvWriter.append(s);
                csvWriter.append(this.dataSeparator);
            }
        }
        csvWriter.append("\n");
    }

    /**
     * Loads data from file specified by pathName into a task list
     * @return TaskList that reflects all the data in the file specified by pathName
     */
    TaskList loadTaskList() {
        File dukeData = new File(this.pathName);
        TaskList taskList = new TaskList();
        if (dukeData.exists()) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(dukeData));
                String eachRow;
                while ((eachRow = csvReader.readLine()) != null) {
                    String[] dataStringArray = eachRow.split(dataSeparator);
                    System.out.println(dataStringArray[0] + dataStringArray[1] + dataStringArray[2]);
                    Task eachTask = Duke.genTask(dataStringArray);
                    if (eachTask != null) {
                        taskList.addToList(eachTask);
                    }
                }
            } catch (java.io.FileNotFoundException e) {
                System.out.println("(FILE NOT FOUND) THIS MESSAGE SHOULD NEVER APPEAR");
            } catch (java.io.IOException e) {
                System.out.println("(ERROR READING FILE) THIS MESSAGE SHOULD NEVER APPEAR");
            }
        }
        return taskList;
    }
}
