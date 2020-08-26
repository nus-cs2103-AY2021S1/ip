package main.java.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Storage {

    /** Directory of local file to store tasks */
    public String fileDirectory;

    /** Name of the local file to store tasks */
    public String fileName;

    /** The File object to enable file writing and editing */
    public File memoFile;

    /** Parser object */
    public Parser parser;


    /**
     * Constructor of Storage class.
     * Initialize class members fileDirectory, fileName, parser.
     *
     * @param fileDirectory  Directory of local file to store tasks.
     * @param fileName  Name of local file to store tasks.
     */
    public Storage(String fileDirectory, String fileName) {
        this.fileDirectory = fileDirectory;
        this.fileName = fileName;
        this.parser = new Parser();
    }


    /**
     * Initialize the File object to enable writing memory.
     * Create valid directory and file if not present.
     */
    public void reachFile() {
        File dataFolder = new File(fileDirectory);
        if (!dataFolder.exists() && !dataFolder.isDirectory()) {
            dataFolder.mkdirs();
        }
        File taskListFile = new File(fileDirectory + fileName);
        if (!taskListFile.exists()) {
            try {
                taskListFile.createNewFile();
            } catch (Exception e) {
                HandleException.handleException(DukeException.ExceptionType.READ_FILE);
            }
        }
        this.memoFile = taskListFile;
    }


    /**
     * Return List of Task objects read and processed from local memory.
     *
     * @return List of Task objects each time the chatbot is run.
     */
    public List<Task> readMemoTasks() {
        reachFile();
        List<Task> taskCollections = new ArrayList<>();
        Scanner sc;

        try {
            sc = new Scanner(memoFile);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, the memory cannot be read successfully.");
            return taskCollections;
        }

        while (sc.hasNextLine()) {
            String currTask = sc.nextLine();
            if (currTask.equals("") || currTask.isBlank()) {
                continue;
            }

            String[] taskInfo = this.parser.memoTaskParser(currTask);
            switch (taskInfo[0]) {
            case "T":
                taskCollections.add(
                        new Todo(taskInfo[2], taskInfo[1].equals("0") ? false : true));
                break;
            case "E":
                taskCollections.add(
                        new Event(taskInfo[2], taskInfo[3],
                                taskInfo[1].equals("0") ? false : true));
                break;
            case "D":
                taskCollections.add(
                        new Deadline(taskInfo[2], taskInfo[3],
                                taskInfo[1].equals("0") ? false : true));
                break;
            }
        }
        return taskCollections;
    }


    /**
     * Return String of a Task object for storage in local memory.
     *
     * @param t  Task object for conversion.
     * @return  Task String to be stored in memory.
     */
    public String taskToMemoStr(Task t) {
        String memoStr = "";
        String[] info = t.getInfo();
        memoStr += "\n" + info[0] + SpecialFormat.SPLIT_NOTN + info[1] + SpecialFormat.SPLIT_NOTN + info[2];
        if (t.getType().equals("D") || t.getType().equals("E")) {
            memoStr += SpecialFormat.SPLIT_NOTN + info[3];
        }
        return memoStr;
    }


    /**
     * Overwrite local memory with the current List of Task objects.
     *
     * @param task_list  Current List of Task objects.
     */
    public void write_memory(List<Task> task_list) {
        try {
            FileWriter fw = new FileWriter(fileDirectory + fileName);
            String textToAppend = "";
            Iterator taskIter = task_list.iterator();
            while (taskIter.hasNext()) {
                Task t = (Task) taskIter.next();
                textToAppend += taskToMemoStr(t);
            }
            fw.write(textToAppend);
            fw.close();
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.READ_FILE);
        }
    }


    /**
     * Write local memory with a newly added Task object (not overwriting).
     *
     * @param filePath  Path of the file to be modified.
     * @param t  Task object to be added.
     */
    public void appendToFile(String filePath, Task t) {
        try {
            FileWriter fw = new FileWriter(filePath, true); // appending instead of overwriting
            fw.write(taskToMemoStr(t));
            fw.close();
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.READ_FILE);
        }
    }

}