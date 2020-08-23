package storage;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import data.task.Task;
import tasklist.TaskList;

public class Storage {

    private String path;

    /**
     * constructor
     * @param path string path to save file
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * saves stored tasks to a local file
     * @param storage the place tasks are stored
     */
    public void save(ArrayList<Task> storage) {
        try {
            FileWriter writer = new FileWriter(path);
            for (Task task : storage) {
                writer.write(task.toString());
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            File folder = new File("./data");
            if (folder.mkdir()) {
                save(storage);
            } else {
                System.out.println("folder does not exist, while making folder failed");
            }
        }
    }

    /**
     * Loads contents of storage text file into storage array
     */
    public void load(TaskList tl) {
        try {
            File data = new File("./data/duke.txt");
            Scanner dataScan = new Scanner(data);
            while (dataScan.hasNextLine()) {
                tl.store(dataScan.nextLine());
            }
        } catch (Exception e) {
            System.out.println("save file does not exist, nothing loaded");
        }
    }
}
