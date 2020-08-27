import java.io.*;
import java.util.ArrayList;

public class Storage {
    
    String filepath;
    
    Storage(String filepath) {
        this.filepath = filepath;
    }
    
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
