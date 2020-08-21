import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String storagePath;

    public Storage(String storagePath) {
        this.storagePath = storagePath;
    }

    public List<Task> getTasks() {
        File storageFile = new File(storagePath);
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(storageFile);
            while (scanner.hasNext()) {
                tasks.add(parseStorageString(scanner.nextLine()));
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("Storage file not found");
            return tasks;
        }
    }

    public void updateTasks(List<Task> taskList) {
        try {
            FileWriter storageWriter = new FileWriter(storagePath, true);
            clearTasks();
            for (Task t : taskList) {
                addTask(storageWriter, t);
            }
            storageWriter.close();
        } catch (IOException e) {
            System.out.println("Problem accessing storage file");
        }
    }

    private Task parseStorageString(String storageString) {
        String[] taskComponents = storageString.trim().split(" \\| ");
        final String type = taskComponents[0];
        final boolean isCompleted = taskComponents[1].equals("1");
        final String description = taskComponents[2];
        if (type.equals("T")) {
            return new Todo(description, isCompleted);
        } else {
            final String date = taskComponents[3];
            return type.equals("E")
                    ? new Event(description, date, isCompleted)
                    : new Deadline(description, date, isCompleted);
        }
    }

    private void clearTasks() throws IOException {
        FileWriter storageWriter = new FileWriter(storagePath, false);
        // replace the original content with an empty string
        storageWriter.write("");
        storageWriter.close();
    }

    private void addTask(FileWriter storageWriter, Task task) throws IOException {
        storageWriter.write(task.getStorageString());
        storageWriter.write('\n');
    }

}
