import java.io.*;
import java.util.List;

public class Storage {
    private final static long serialVersionUID = 7526472295622776147L;

    public static TaskList load() throws FileNotFoundException, IOException, ClassNotFoundException { //load a tasklist from the text file
        try {
            FileInputStream fin = new FileInputStream("Duke.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            TaskList taskList = (TaskList) ois.readObject();
            ois.close();
            return taskList;
        } catch (FileNotFoundException exception) {
            System.out.println("Sorry, Duke.txt file does not exist :( \n Created a new file!");
            TaskList newTaskList = new TaskList();
            Storage.store(newTaskList);
            return newTaskList;

        }
    }

    public static void store(TaskList taskList) { //writing the tasklist to a text file
        try {
            FileOutputStream fos = new FileOutputStream("Duke.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
