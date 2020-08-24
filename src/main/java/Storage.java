import java.io.*;
import java.nio.file.Paths;

public class Storage {
    public static final File storage_file = Paths.get("tasks.ser").toFile();

    public Storage() {
    }

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

    public static TaskList read() {
        try {
            TaskList t = null;
            FileInputStream fileIn = new FileInputStream(storage_file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            t = (TaskList) in.readObject();
            in.close();
            fileIn.close();
            t.loadMessage = "Successfully loaded from storage. " + t.numTasks();
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