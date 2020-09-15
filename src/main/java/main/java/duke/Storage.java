package main.java.duke;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that encapsulates all the the reading and writing of files to the system. The user task list is written as
 * a text file to the local device.
 */
public class Storage {

    private File file;

    private FileWriter appendFileWriter;
    private BufferedWriter bufferedAppendWriter;

    private ArrayList<Task> startupTaskList = new ArrayList<>();

    /**
     * Creates an instance of Storage, reads the file at the directory filepath and populates the
     * list of tasks for other entities of Duke to gain access to.
     * @param filepath the string path where a .txt file containing the list of tasks is stored.
     */
    public Storage(String filepath) {
        // what about the IOException
        file = new File(filepath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                populateTaskList(file);
            }
            // appendFileWriter appends new text whenever we write to the file
            // to maintain the previous entries.
            appendFileWriter = new FileWriter(file, true);
            assert appendFileWriter != null : "appendFileWriter not initialized properly";
            bufferedAppendWriter = new BufferedWriter(appendFileWriter);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Reads the line of string stored in the text file and
     * determines the priority to mark the task with.
     * @param taskDesc task stored as a line of string in the text file
     * @param toMark task whose priority needs may be marked if priority is present.
     */
    public void markPriority(String taskDesc, Task toMark) {
        if (taskDesc.contains("[LOW]")) {
            toMark.setPriority(Priority.LOW);
        }
        if (taskDesc.contains("[MEDIUM]")) {
            toMark.setPriority(Priority.MEDIUM);
        }
        if (taskDesc.contains("[HIGH]")) {
            toMark.setPriority(Priority.HIGH);
        }
    }

    /**
     * Returns the corresponding task according to the task stored in the line of String in the text file.
     * @param taskDesc string representation of the task
     * @return the Task object.
     */
    public Task recreateTask(String taskDesc) {
        // lineComponents[0] contains the [TaskType][isCompleted][Priority]
        // lineComponents[1] contains the description of the task, to be passed
        // to the Task object's constructor.
        String[] lineComponents = taskDesc.split(" ", 2);
        if (taskDesc.contains("[T]")) {
            return new Todo(lineComponents[1]);
        } else if (taskDesc.contains("[D]")) {
            return new Deadline(lineComponents[1]);
        } else if (taskDesc.contains("[E]")) {
            return new Event(lineComponents[1]);
        } else {
            return null;
        }
    }

    /**
     * Iterates through a file object using a scanner object, interprets the lines of text and
     * populates the task list with the corresponding task objects.
     * @param file File object that contains the list of tasks in text file format.
     */
    public void populateTaskList(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            // while there is still a line of string to read, populate the tasklist
            while(fileScanner.hasNextLine()) {
                // dissect the line of String to create Task objects.
                String taskDesc = fileScanner.nextLine();
                Task toAdd = recreateTask(taskDesc);
                if (toAdd == null) {
                    System.out.println("Task is empy!");
                }
                if (taskDesc.contains("\u2713")) {
                    // description has a tick
                    toAdd.markDone();
                }
                if (toAdd != null) {
                    markPriority(taskDesc, toAdd);
                    startupTaskList.add(toAdd);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
    }

    /**
     * Writes a string input to the text file.
     * @param input
     */
    public void writeToFile(String input) {
        try {
            bufferedAppendWriter.write(input + System.lineSeparator());
            bufferedAppendWriter.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Overrides the existing text file using the overriding bufferedWriter.
     * @param taskList
     */
    public void changeFileContents(TaskList taskList) {
        try {
            // the FileWriter and BufferedWriters are created on demand here
            // as they tend to change over time.
            FileWriter overrideFileWriter = new FileWriter(file);
            BufferedWriter bufferedOverrideWriter = new BufferedWriter(overrideFileWriter);
            for (int i = 0; i < taskList.size(); i++) {
                Task toWrite = taskList.getTask(i);
                assert toWrite != null : "task to be written into .txt storage file is null";
                bufferedOverrideWriter.write(toWrite.toString() + System.lineSeparator());
            }
            bufferedOverrideWriter.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Returns the task list saved previously in the text file.
     * @return
     */
    public ArrayList<Task> getStartupTaskList() {
        return startupTaskList;
    }

}