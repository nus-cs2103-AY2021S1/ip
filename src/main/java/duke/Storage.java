package duke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class has access to duke.ser file for reading and writing of data.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Accesses duke.ser file in order to read and load in data as a List of Tasks.
     * @return List of stored Tasks
     * @throws Exception FIle not found
     */
    @SuppressWarnings("unchecked")
    public List<Task> loadData() throws Exception {
        List<Task> list = new ArrayList<>();
        try {
            FileInputStream readData = new FileInputStream(filePath);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ArrayList<Task> loadedList = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            list = loadedList;
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("☹ OOPS!!! I can't find your file leh!");
        } catch (Exception ex) {
            throw new Exception("☹ OOPS!!! I can't load your file leh...");
        }
        return list;
    }

    /**
     * Stores modified List of tasks and writes onto duke.ser for future access.
     * @param list List of stored Tasks
     */
    public void writeData(List<Task> list) {
        try {
            FileOutputStream writeData = new FileOutputStream(filePath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(list);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
