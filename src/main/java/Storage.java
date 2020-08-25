import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Storage {

    public void createDirectory(String dirName) {
        File file = new File(dirName);

        if (!file.exists()) {
            file.mkdir();
        }

    }

    public void createToDo(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(Task myTask, int todoNum) {

        createToDo("ToDo/item" + todoNum + ".txt");

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ToDo/item" + todoNum + ".txt"));
            out.writeObject(myTask);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task readFromFile(String fileDir) {
        Task myTask = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileDir));
            myTask = (Task) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myTask;

    }

    public void updateDirectory(TaskList myTaskList) {
        // deleting all files in directory
        File dir = new File("ToDo");
        File[] myItems = dir.listFiles();
        for (File child : myItems) {
            if (child.toString().substring(0, 9).equals("ToDo/item")) {
                Path path = FileSystems.getDefault().getPath(child.toString());
                try {
                    Files.delete(path);
                } catch (NoSuchFileException x) {
                    System.err.format("%s: no such" + " file or directory%n", path);
                } catch (IOException x) {
                    System.err.println(x);
                }
            }
        }

        // repopulating directory with that in arraylist taks
        for (int i = 0; i < myTaskList.getTasks().size(); i++) {
            writeToFile(myTaskList.getTasks().get(i), i);
        }
    }


    public void populateList(TaskList myUi) {
        File dir = new File("ToDo");
        File[] myItems = dir.listFiles();
        for (File child : myItems) {

            if (child.toString().substring(0, 9).equals("ToDo/item")) {
                myUi.getTasks().add(readFromFile(child.toString()));
            }
            // Do something with child
        }

    }
}
