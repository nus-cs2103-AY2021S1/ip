package duke.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Responsible for local disk file operations.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a list of tasks from disk.
     * @return TaskList with loaded list.
     */
    @SuppressWarnings("unchecked")
    public TaskList load() {
        List<Task> lst;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            lst = (List<Task>) ois.readObject();
            ois.close();

        } catch (Exception e) {
            lst = new ArrayList<>();
        }
        return new TaskList(lst);
    }

    /**
     * Saves a list of tasks to disk.
     * @param lst The TaskList to save to disk.
     */
    public void save(TaskList lst) {
        try {
            new File(filePath).getParentFile().mkdirs();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(lst.getList());
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
