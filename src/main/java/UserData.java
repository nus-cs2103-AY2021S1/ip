import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserData {

    List<Task> tasks;
    File dataFolder = new File("./data");
    File data = new File("./data/duke.txt");

    public UserData() throws IOException {
        if (!dataFolder.exists()) dataFolder.mkdir();
        boolean isNew = data.createNewFile();
        if (!isNew)
            read();
        else
            tasks = new ArrayList<>();
    }

    // CREATE
    public void create(Task task) {

    }

    // READ
    public void read() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(data));
        String st;
        while ((st = br.readLine()) != null) {
            Task task = Task.fromData(st);
            System.out.println(task.toString());
        }
    }

    // UPDATE

}
