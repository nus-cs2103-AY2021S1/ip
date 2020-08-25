package utils;

import exceptions.DukeIOException;
import tasks.Task;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The storage handler. Handles saving and loading of data using
 * <code>Serializable</code> objects.
 */
public class Storage {

    private FileInputStream fileIn;

    private ObjectInputStream objIn;

    private String path;

    private Storage() {

    }

    /**
     * Factory method for creating a <code>Storage</code> instance.
     *
     * @param directoryPath the directory path
     * @param fileName      the file name of the ser database
     * @return the storage instance
     * @throws DukeIOException when unable to create directory
     */
    public static Storage createStorage(String directoryPath, String fileName) throws DukeIOException {
        Path dirPath = Paths.get(directoryPath);

        File directory = new File(dirPath.normalize().toString());
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                ResourceBundle strings = ResourceBundle.getBundle("resources.StringsBundle", Locale.ENGLISH);
                throw new DukeIOException(strings.getString("error.dir"), directoryPath);
            }
        }
        Storage storage = new Storage();
        storage.path = dirPath.normalize() + "/" + fileName;
        try {
            storage.fileIn = new FileInputStream(storage.path);
            storage.objIn = new ObjectInputStream(storage.fileIn);
            return storage;
        } catch (IOException ignored) {
            return storage;
        }

    }

    /**
     * Load the saved list from the database.
     *
     * @return the array list
     * @throws DukeIOException when data is corrupted
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() throws DukeIOException {

        //Solution below adapted from https://www.javatpoint.com/serialization-in-java
        try {
            if (objIn == null) return new ArrayList<>();
            ArrayList<Task> ret = (ArrayList<Task>) objIn.readObject();

            objIn.close();
            fileIn.close();

            return ret;
        } catch (ClassNotFoundException e) {
            throw new DukeIOException(e.getMessage(), "");
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Save a given list into the database.
     *
     * @param tasks the <code>Task</code> list to save
     * @throws DukeIOException when unable to write to database
     */
    public void save(ArrayList<Task> tasks) throws DukeIOException {

        //Solution below adapted from https://www.javatpoint.com/serialization-in-java
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(tasks);
            objOut.flush();
            objOut.close();
            fileOut.close();
        } catch (IOException e) {
            throw new DukeIOException(e.getMessage(), "");
        }
    }

}
