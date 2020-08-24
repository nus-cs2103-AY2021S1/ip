package main.java;

import java.io.*;

class Storage {

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
            return taskList;
        } else {
            return null;
        }
    }

    /**
     * Clears all the contents in the file
     * @return true if the contents are cleared successfully, false otherwise
     */
    boolean resetFile() {
        try {
            return this.file.delete() && this.file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Saves data in current task list into file specified by pathName
     * @param taskList Task list to be saved
     * @return true if saving is successful, false otherwise
     */
    boolean saveTaskList(TaskList taskList) {
        try {
            FileWriter csvWriter = new FileWriter(this.file);
            for (int i = 0; i < taskList.getNumOfTasks(); i++) {
                String[] dataStringArray = taskList.getTaskAt(i).getDataString();
                for (String s : dataStringArray) {
                    csvWriter.append(s);
                    csvWriter.append(this.dataSeparator);
                }
            }
            csvWriter.append("\n");
            return true;
        } catch (IOException e) {
            return false;
        }

    }


}
