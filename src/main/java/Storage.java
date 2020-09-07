import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * handles the storing and loading of information (task list)
 */
public class Storage {
    private final long serialVersionUID = 1089785654636785606L;

    /**
     * loads the tasklist that user keyed in before terminating the bot
     * hence, previous tasklist made available when the user runs the bot
     *
     * @return the tasklist in Duke.txt file
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static TaskList load() throws IOException, ClassNotFoundException {
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

    /**
     * stores the tasks (user inputs) into Duke.txt file
     *
     * @param taskList
     */
    public static void store(TaskList taskList) {
        try {
            FileOutputStream fos = new FileOutputStream("Duke.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
