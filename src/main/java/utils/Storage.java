package utils;

import exceptions.DukeIOException;
import tasks.Task;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Storage {

    private FileInputStream fileIn;

    private ObjectInputStream objIn;

    private String path;

    private Storage() {

    }

    public static Storage createStorage(String directoryPath, String fileName) throws DukeIOException {
        Path dirPath = Paths.get(directoryPath);

        File directory = new File(dirPath.normalize().toString());
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                ResourceBundle strings = ResourceBundle.getBundle("resources.StringsBundle", Locale.ENGLISH);
                throw new DukeIOException(strings.getString("error.dir"), directoryPath);
            }
        }

        try {
            Storage storage = new Storage();
            storage.fileIn = new FileInputStream(dirPath.normalize() + "/tasks.ser");
            storage.objIn = new ObjectInputStream(storage.fileIn);
            storage.path = dirPath.normalize() + "/tasks.ser";
            return storage;
        } catch (IOException e) {
            throw new DukeIOException(e.getMessage(), directoryPath);
        }

    }

    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() throws DukeIOException {

        //Solution below adapted from https://www.javatpoint.com/serialization-in-java
        try {
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
