/**
 * Handles persisting task data to disk.
 */
package sg.christopher.duke.io;

import sg.christopher.duke.entities.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class DataManager {
    public static final File dataFile = new File("data", "data.txt");

    /**
     * Writes the task list to disk.
     *
     * Creates the file and it's parent folders if it doesn't exist.
     *
     * @param inList task list to write to disk
     */
    public static void writeList(List<Task> inList) {
        try {
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();
        } catch (IOException ioe) {
            System.err.println("Unable to create data directory or file");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(inList);
        } catch (IOException ioe) {
            System.err.println("Unable to save Duke data to disk");
            ioe.printStackTrace();
        }
    }

    /**
     * Reads the task list from disk.
     *
     * @return task list, or null if file doesn't exist
     */
    public static List<Task> readList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            return (List<Task>) ois.readObject();
        } catch (FileNotFoundException fnfe) {
            return null;
        } catch (Exception e) {
            System.err.println("Unable to read Duke data from disk");
            e.printStackTrace();
        }
        return null;
    }

}
