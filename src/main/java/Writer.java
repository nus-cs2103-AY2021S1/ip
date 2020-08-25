import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Writer {
    static void writeListToFile(TaskList taskList, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(taskList);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Alright, your list has been saved!");
        } catch (IOException e) {
            System.err.println("Error saving task list: " + e);
        }
    }
}
