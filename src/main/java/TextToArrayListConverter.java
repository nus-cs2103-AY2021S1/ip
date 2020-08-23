import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class TextToArrayListConverter {

    public static List<Task> readFile() {
        try {
            FileInputStream readData = new FileInputStream("./taskdata.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ArrayList<Task> taskList = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            return taskList;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
