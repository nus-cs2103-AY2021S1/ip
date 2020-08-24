import java.io.*;
import java.util.List;

public class Storage {
    //store and load tasks (store tasklist)
    private final static long serialVersionUID = 7526472295622776147L;

    public static TaskList load() throws IOException, ClassNotFoundException { //load a tasklist from the text file
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
            //System.out.println("Task List is saved in Duke.txt file!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
