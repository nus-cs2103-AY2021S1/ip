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
