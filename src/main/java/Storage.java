import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class to handle logic related to reading and writing of files to storage
 */
public class Storage {

    // Attributes
    private final String filePathTasks;
    private final String filePathNotes;

    // Constructor
    public Storage(String filePathTasks, String filePathNotes) {
        this.filePathTasks = filePathTasks;
        this.filePathNotes = filePathNotes;
    }

    /**
     * Checks whether the file exists.
     *
     * @return Boolean representing whether the file exists.
     */
    public boolean doesFileExistTasks() {
        File file = new File(this.filePathTasks);
        return file.exists();
    }

    public boolean doesFileExistNotes() {
        File file = new File(this.filePathNotes);
        return file.exists();
    }

    public void createFileTasks() {
        try {
            File file = new File(this.filePathTasks);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFileNotes() {
        try {
            File file = new File(this.filePathNotes);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(TaskList tasks, NotesList notes) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.filePathTasks);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tasks);
            objectOutputStream.close();

            fileOutputStream = new FileOutputStream(this.filePathNotes);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(notes);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList readTasks() {
        try {
            if (!doesFileExistTasks()) {
                throw new UnsupportedOperationException("File does not exist");
            }
            FileInputStream fileInputStream = new FileInputStream(this.filePathTasks);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            TaskList tasks = (TaskList) objectInputStream.readObject();
            objectInputStream.close();
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
            return new TaskList();
        }
    }

    public NotesList readNotes() {
        try {
            if (!doesFileExistNotes()) {
                throw new UnsupportedOperationException("File does not exist");
            }
            FileInputStream fileInputStream = new FileInputStream(this.filePathNotes);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            NotesList notes = (NotesList) objectInputStream.readObject();
            objectInputStream.close();
            return notes;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
            return new NotesList();
        }
    }
}
