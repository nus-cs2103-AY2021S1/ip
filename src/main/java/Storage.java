import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

/**
 * Class that provides file storage utilities.
 */
public class Storage {
    public static final File storage_file = Paths.get("tasks.ser").toFile();

    public Storage() {
    }

    /**
     * Serializes a TaskList and writes it to file.
     * @param t tasklist to be stored.
     */
    public static void store(TaskList t) {
        try {
            //noinspection ResultOfMethodCallIgnored
            storage_file.createNewFile(); //creates file if it does not exist
            FileOutputStream fileOut = new FileOutputStream(storage_file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(t);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a serialized TaskList.
     * @return tasklisk read from file.
     */
    public static TaskList read() {
        try {
            TaskList t;
            FileInputStream fileIn = new FileInputStream(storage_file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            t = (TaskList) in.readObject();
            in.close();
            fileIn.close();
            t.loadMessage = "Successfully loaded from storage. " + t.numTasks() + t.toString();
            return t;
        } catch (FileNotFoundException i) {
            return new TaskList();
        } catch (IOException i) {
            i.printStackTrace();
            return new TaskList();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return new TaskList();
        }
    }
}