package duke;

import duke.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage handles reading and writing of data into Duke.txt file.
 * It allows user to save and load their added tasks.
 */
public class Storage {

    List<Task> tasks = new ArrayList<>();
    File dataFolder = new File("./data");
    File data = new File("./data/duke.txt");

    public Storage() {
        if (!dataFolder.exists()) dataFolder.mkdir();
        try {
            boolean isNew = data.createNewFile();
            if (!isNew) read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends the new task data to Duke.txt file.
     *
     * @param task
     */
    public void add(Task task) {
        try {
            FileWriter fw = new FileWriter(data, true);
            fw.write(task.toData() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads Duke.txt and add all existing tasks to taskList.
     *
     * @throws IOException
     */
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

    /**
     * Overwrite Duke.txt with the current taskList in data format.
     */
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
