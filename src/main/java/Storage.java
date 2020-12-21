import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    private String filepath;
    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads up an ArrayList of Tasks from an existing filepath
     * Returns an empty ArrayList if the file does not exist
     * @return the specified ArrayList of Tasks or an empty one
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
            ) {
            return (ArrayList<Task>) ois.readObject();
        } catch (EOFException | FileNotFoundException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Takes a task and an ArrayList of Tasks as arguments
     * and stores the task into that particular ArrayList.
     * @param list
     * @param task
     * @throws FileNotFoundException
     */
    public void save(ArrayList<Task> list, Task task) throws FileNotFoundException {

        try (FileOutputStream tasks = new FileOutputStream(filepath);
             ObjectOutputStream oos = new ObjectOutputStream(tasks)) {
            list.add(task);
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes an ArrayList of Tasks and saves it into the hard disk
     * @param list
     * @throws FileNotFoundException
     */
    public void save(ArrayList<Task> list) throws FileNotFoundException {
        try (FileOutputStream tasks = new FileOutputStream(filepath);
             ObjectOutputStream oos = new ObjectOutputStream(tasks)) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
