package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
     * Initializes class members fileDirectory, fileName, parser.
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
     * Initializes the File object to enable writing memory.
     * Creates valid directory and file if not present.
     *
     * @throws IOException if exception caught in createNewFile().
     */
    public void reachFile() throws IOException {
        assert (!fileDirectory.isBlank() && !fileName.isBlank());
        File dataFolder = new File(fileDirectory);
        if (!dataFolder.exists() && !dataFolder.isDirectory()) {
            dataFolder.mkdirs();
        }
        File taskListFile = new File(fileDirectory + fileName);
        if (!taskListFile.exists()) {
            taskListFile.createNewFile();
        }
        this.memoFile = taskListFile;
    }


    /**
     * Returns List of Task objects read and processed from local memory.
     *
     * @return List of Task objects each time the chatbot is started.
     * @throws IOException if exception caught in reachFile().
     */
    public List<Task> readMemoTasks() throws IOException {
        List<Task> taskCollections = new ArrayList<>();
        Scanner sc;

        reachFile();

        try {
            assert (memoFile != null);
            sc = new Scanner(memoFile);
        } catch (Exception e) {
            return taskCollections;
        }

        while (sc.hasNextLine()) {
            String currTask = sc.nextLine();
            if (currTask.isBlank()) {
                continue;
            }

            String[] taskInfo = parser.localFileTaskParser(currTask);
            assert (taskInfo.length > 1);
            String taskType = taskInfo[0];
            boolean isDone = taskInfo[1].equals("0") ? false : true;
            String taskAction = taskInfo[2];

            Task t = taskType.equals("T")
                    ? new Todo(taskAction, isDone)
                    : taskType.equals("E")
                        ? new Event(taskAction, taskInfo[3], isDone)
                        : new Deadline(taskAction, taskInfo[3], isDone);
            taskCollections.add(t);
        }

        return taskCollections;
    }


    /**
     * Returns String of a Task object for storage in local memory.
     *
     * @param t  Task object for conversion.
     * @return  Task String to be stored in memory.
     */
    public String taskToMemoStr(Task t) {
        String memoStr = "";
        String[] info = t.getInfo();
        memoStr += "\n" + info[0] + SpecialFormat.SPLIT_NOTN + info[1]
                + SpecialFormat.SPLIT_NOTN + info[2];
        if (t.getType().equals("D") || t.getType().equals("E")) {
            memoStr += SpecialFormat.SPLIT_NOTN + info[3];
        }
        return memoStr;
    }


    /**
     * Returns true if file is successfully read and written, false otherwise.
     * Overwrites local memory with the current List of Task objects.
     * If file does not exist, creates a new file with current task-list.
     *
     * @param taskList  Current List of Task objects.
     * @return  true if successfully read and written file, false if failure.
     */
    public boolean write_memory(List<Task> taskList) {
        try {
            if (!new File(fileDirectory + fileName).exists()) {
                reachFile();
            }
            FileWriter fw = new FileWriter(fileDirectory + fileName);
            Iterator taskIter = taskList.iterator();
            String textToAppend = "";
            while (taskIter.hasNext()) {
                Task t = (Task) taskIter.next();
                textToAppend += taskToMemoStr(t);
            }
            fw.write(textToAppend);
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    /**
     * Returns true if file is successfully modified, false otherwise.
     * Modifies local memory with a newly added Task object (not overwriting).
     * If local file not existing, create a new file.
     *
     * @param filePath  Path of the file to be modified.
     * @param t  Task object to be added.
     * @return  true if successful appending/overwriting, false if failure.
     */
    public boolean appendToFile(String filePath, Task t, List<Task> taskList) {
        if (!new File(filePath).exists()) {
            boolean success = write_memory(taskList);
            if (!success) {
                return false;
            }
        }
        try {
            FileWriter fw = new FileWriter(filePath, true); // appending instead of overwriting
            fw.write(taskToMemoStr(t));
            fw.close();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

}