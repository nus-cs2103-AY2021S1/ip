package storage;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Storage storage = (Storage) o;

        return Objects.equals(path, storage.path);
    }

    @Override
    public int hashCode() {
        return path != null ? path.hashCode() : 0;
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
            File data = new File(path);
            Scanner dataScan = new Scanner(data);
            while (dataScan.hasNextLine()) {
                tl.store(dataScan.nextLine());
            }
        } catch (Exception e) {
            System.out.println("save file does not exist, nothing loaded");
        }
    }
}
