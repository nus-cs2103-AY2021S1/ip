import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserData {

    List<Task> tasks = new ArrayList<>();
    File dataFolder = new File("./data");
    File data = new File("./data/duke.txt");

    public UserData() {
        if (!dataFolder.exists()) dataFolder.mkdir();
        try {
            boolean isNew = data.createNewFile();
            if (!isNew) read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public void create(Task task) {
        try {
            FileWriter fw = new FileWriter(data, true);
            fw.write(task.toData() + "\n");
            fw.close();
            tasks.add(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // READ
    private void read() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(data));
        String st;
        while ((st = br.readLine()) != null) {
            Task task = Task.fromData(st);
            tasks.add(task);
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int taskSize() {
        return tasks.size();
    }

    // UPDATE
    public Task markAsDone(int taskId) {
        Task task = tasks.get(taskId).markAsDone();
        tasks.set(taskId, task);
        update();
        return task;
    }

    private void update() {
        try {
            FileWriter fw = new FileWriter(data);
            for (Task task : tasks) {
                fw.write(task.toData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public Task delete(int taskId) {
        Task task = tasks.get(taskId);
        tasks.remove(taskId);
        update();
        return task;
    }

}
