package duke;

import java.io.*;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task list text file from relative file path provided
     */
    public TaskList load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        TaskList lst = (TaskList) ois.readObject();
        ois.close();
        return lst;
    }


    /**
     * Saves task list text file to relative file path provided
     *
     * @param lst version of list to be saved onto the text file
     */
    public void save(TaskList lst) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(lst);
        oos.close();
    }
}
