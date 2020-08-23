package duke.util;

import duke.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
