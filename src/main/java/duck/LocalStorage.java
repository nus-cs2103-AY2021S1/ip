package duck;

import duck.exception.DuckException;
import duck.task.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LocalStorage implements Storage{
    private String filePath;

    public LocalStorage(String filePath) {
        this.filePath = filePath;
    }

    private void ensureFileExists() throws IOException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            File directories = new File(file.getParentFile().getAbsolutePath());
            directories.mkdirs();
            file.createNewFile();
        }

    }



    public void save(TaskList taskList) throws DuckException {
        try {
            ensureFileExists();
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(taskList);
            out.close();
            file.close();
        } catch (IOException i) {
            System.out.println(i);
            throw new DuckException("Failed to save file");
        }
    }

    public TaskList load() throws DuckException {
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            TaskList taskList = (TaskList) in.readObject();
            return taskList;
        } catch (IOException | ClassNotFoundException e) {
            throw new DuckException("Failed to load file");
        }
    }
}
