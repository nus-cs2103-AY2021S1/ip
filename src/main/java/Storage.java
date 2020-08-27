import java.io.*;
import java.util.ArrayList;

public class Storage {
    
    String filepath;
    
    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads up an ArrayList<Task> from an existing filepath
     * Returns an empty ArrayList<Task> if the file does not exist
     * @return the specified ArrayList or an empty one
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
        ) {
             return (ArrayList<Task>) ois.readObject();
        }
        catch (EOFException | FileNotFoundException | ClassNotFoundException e) {
            System.out.println("An error has occurred");
            return new ArrayList<>();
        }
        
    }

    /**
     * Takes a task and an ArrayList<Task> as arguments and stores the task into
     * that particular ArrayList.
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
    
    public void save(ArrayList<Task> list) throws FileNotFoundException {
        try (FileOutputStream tasks = new FileOutputStream(filepath);
             ObjectOutputStream oos = new ObjectOutputStream(tasks)) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
