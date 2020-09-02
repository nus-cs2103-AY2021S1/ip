import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {

    public void createDirectory(String dirName) {
        File file = new File(dirName);

        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateDirectory(TaskList tasks) {

        File fileDir = new File("TaskList");
        File[] fileList = fileDir.listFiles();
        for (File f : fileList) {
            if (f.toString().substring(0, 13).equals("TaskList/Task")) {
                Path path = FileSystems.getDefault().getPath(f.toString());
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            writeToFile(tasks.getTasks().get(i), i);
        }
    }

    public void createFile(String fileName) {

        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(Task task, int index) {


        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ToDo/item" + todoNum + ".txt"));
            out.writeObject(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task readFromFile(String fileDir) {
        Task task = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileDir));
            task = (Task) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return task;

    }

    public void updateList(TaskList tasks) {
        File fileDir = new File("TaskList");
        File[] fileList = fileDir.listFiles();
        for (File f : fileList) {
            if (f.toString().substring(0, 13).equals("TaskList/Task")) {
                tasks.getTasks().add(readFromFile(f.toString()));
            }
        }
    }
}


