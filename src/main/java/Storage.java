import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A Storage class that handles reading and writing files.
 */
public class Storage {

    /**
     * Create a directory for file storage.
     * @param dirName the directory name.
     * throw exception if directory exists.
     */
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

    /**
     * Update the directory for file storage.
     * @param tasks the list of task pending to be updated into directory.
     * throw IOException if directory has be deleted.
     */
    public void updateDirectory(TaskList tasks) {

        File fileDir = new File("TaskList");
        File[] fileList = fileDir.listFiles();
        for (File f : fileList) {
            if (f.toString().startsWith("TaskList/Task")) {
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

    /**
     * Create a new file.
     * @param fileName the file name.
     * throw IOException if file name exists.
     */
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

    /**
     * Write to a file.
     * @param task the task pending to be writen.
     * @param index the index of the file.
     */
    public void writeToFile(Task task, int index) {
        createFile("TaskList/Task" + index + ".txt");
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TaskList/Task" + index + ".txt"));
            out.writeObject(task);
        } catch (IOException e) {
            System.out.println("Writing to File");
        }
    }

    /**
     * Read from a file.
     * @param fileDir the file directory.
     * return the task extracted from the file.
     * throw IOException if no such file exist.
     */
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

    /**
     * Update the task list from files.
     * @param tasks Task list.
     */
    public void updateList(TaskList tasks) {
        File fileDir = new File("TaskList");
        File[] fileList = fileDir.listFiles();
        for (File f : fileList) {
            if (f.toString().startsWith("TaskList/Task")) {
                tasks.getTasks().add(readFromFile(f.toString()));
            }
        }
    }
}


