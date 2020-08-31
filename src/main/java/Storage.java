import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {

    public void createDirectory(String dirName) {
        File file = new File(dirName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void updateDirectory(TaskList myTaskList) {

        File fileDir = new File("TaskList");
        File[] fileList = fileDir.listFiles();
        for (File f : fileList) {
            if (f.toString().substring(0, 8).equals("TaskList")) {
                Path path = FileSystems.getDefault().getPath(f.toString());
                try {
                    Files.delete(path);
                }  catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < myTaskList.getTasks().size(); i++) {
            writeToFile(myTaskList.getTasks().get(i), i);
        }
    }

    public void writeToFile(Task task, int todoNum) {
        createDirectory("TaskList" + todoNum + ".txt");
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TaskList" + todoNum + ".txt"));
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

    public void updateList(TaskList myList) {
        File fileDir = new File("TaskList");
        File[] fileList = fileDir.listFiles();
        for (File f : fileList) {
            if (f.toString().substring(0, 8).equals("TaskList")) {
                myList.getTasks().add(readFromFile(f.toString()));
            }
        }
    }


}