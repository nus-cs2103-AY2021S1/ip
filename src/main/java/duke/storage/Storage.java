package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1> Storage Class (Saving Tasks)</h1>
 * Dukefile class is the class that contains the methods
 * that manages the task within the single initialisation of the bot
 * and extracts the current tasks and stores it in a text file.
 *
 * @author Lee Penn Han.
 * @version 1.0.
 * @since 2020-25-08.
 */
public class Storage {
    private File file;
    private ArrayList<String> recordArrayLst;

    private Storage(String fileName) {
        this.file = new File(fileName);
        this.recordArrayLst = new ArrayList<>();
    }

    /**
     * Loads the saved tasks that have not been archived.
     * @param file the TaskList saved text file
     * @return an ArrayList of the past Tasks
     * @throws IOException on input error.
     */
    public ArrayList<String> loadFile(File file) throws IOException {
        Scanner sc = new Scanner(file);
        ArrayList<String> stringArr = new ArrayList<>();
        while (sc.hasNextLine()) {
            stringArr.add(sc.nextLine());
        }
        return stringArr;
    };

    /**
     * Saves the current list of task to a textfile.
     * By iterating through the records Arraylist,
     * the method will save each element as a single task
     * in the text file.
     *
     * @author Lee Penn Han.
     * @throws IOException on input error.
     */
    public String saveToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        for (String s : recordArrayLst) {
            fileWriter.write(s + "\n");
        }
        fileWriter.close();
        return "Total number of Tasks saved: " + recordArrayLst.size();
    }

    /**
     * Refreshes the TaskList saved file by deleting all content.
     * @return A String representation of the TaskList refreshing action
     * @throws IOException on input error
     */
    public String overwriteFile() throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.close();
        return "TaskList refreshed";
    }

    /**
     * Stores the current task (Record) into the arraylist
     * as a String.
     *
     * @author Lee Penn Han.
     * @param record This is the Task to be saved.
     */
    public void saveRecord(String record) {
        recordArrayLst.add(record);
    }

    /**
     * Updates the element in the Record ArrayList by using the
     * index from User Input to identify the position.
     *
     * @author Lee Penn Han.
     * @param record Latest Task Status.
     * @param index The index of the task in the list.
     */
    public void updateRecord(String record, int index) {
        int i = index - 1;
        recordArrayLst.set(i, record);
    }

    /**
     * Deletes the element in the Record ArrayList by using the
     * index from User Input to identify the position.
     *
     * @author Lee Penn Han.
     * @param index The index of the targeted task in the list.
     */
    public void deleteRecord(int index) {
        int i = index - 1;
        recordArrayLst.remove(i);
    }

    /**
     * Instantiates a Storage object.
     *
     * @author Lee Penn Han.
     * @param fileName This is the filename that the tasks will be saved under.
     * @return Storage object.
     */
    public static Storage createDukeFile(String fileName) {
        return new Storage(fileName);
    }

    /**
     * Gets the arraylist of Records to be saved.
     *
     * @author Lee Penn Han.
     * @return Arraylist of Records to be saved.
     */
    public ArrayList<String> getRecords() {
        return this.recordArrayLst;
    }
}

