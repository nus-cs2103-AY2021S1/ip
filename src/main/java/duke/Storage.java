package duke;

import duke.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    protected List<Task> tasks = new ArrayList<>();
    protected File dataFolder = new File("./data");
    protected File data = new File("./data/duke.txt");

    public Storage() {
        if (!dataFolder.exists()) dataFolder.mkdir();
        try {
            boolean isNew = data.createNewFile();
            if (!isNew) read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public void add(Task task) {
        try {
            FileWriter fw = new FileWriter(data, true);
            fw.write(task.toData() + "\n");
            fw.close();
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
    };

    // UDPATE
    public void update() {
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
}
