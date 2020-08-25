import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <h1> DukeFile Class (Saving Tasks)</h1>
 * Dukefile class is the class that contains the methods
 * that manages the task within the single initialisation of the bot
 * and extracts the current tasks and stores it in a text file.
 *
 * @author Lee Penn Han.
 * @version 1.0.
 * @since 2020-25-08.
 */
public class DukeFile {
    private String fileName;
    private ArrayList<String> recordArrayLst;

    private DukeFile(String fileName) {
        this.fileName = fileName;
        this.recordArrayLst = new ArrayList<>();
    }


    public File retrieveFile(String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * Saves the current list of task to a textfile.
     * By iterating through the records Arraylist,
     * the method will save each element as a single task
     * in the text file.
     *
     * @author Lee Penn Han.
     * @return Nothing.
     * @throws IOException
     */
    public void saveToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        for (String s : recordArrayLst) {
            fileWriter.write(s + "\n");
            System.out.println("*****************************************\n" + "Text: " + s + " saved into " + fileName + "\n*****************************************");
        }

        fileWriter.close();
    }

    /**
     * Stores the current task (Record) into the arraylist
     * as a String.
     *
     * @author Lee Penn Han.
     * @return Nothing.
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
     * @return Nothing.
     * @param record Latest Task Status.
     * @param index The index of the task in the list.
     */
    public void updateRecord(String record, int index) {
        int i = index -1;
        recordArrayLst.set(i, record);
    }

    /**
     * Deletes the element in the Record ArrayList by using the
     * index from User Input to identify the position.
     *
     * @author Lee Penn Han.
     * @return Nothing.
     * @param index The index of the targeted task in the list.
     */
    public void deleteRecord(int index) {
        int i = index - 1;
        recordArrayLst.remove(i);
    }

    /**
     * Instantiates a DukeFile object.
     *
     * @author Lee Penn Han.
     * @param fileName This is the filename that the tasks will be saved under.
     * @return DukeFile object.
     */
    public static DukeFile createDukeFile(String fileName) {
        return new DukeFile(fileName);
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

