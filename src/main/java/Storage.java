import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String storagePath = "data/duke.txt";

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

    public void addTask(Task task) {
        try {
            FileWriter storageWriter = new FileWriter(storagePath, true);
            storageWriter.write('\n');
            storageWriter.write(task.getStorageString());
            storageWriter.close();
        } catch (IOException e) {
            System.out.println("Storage file not found");
        }
    }

    public static void main(String[] args) {
        // temporary tests
        Storage s = new Storage();
        List<Task> result = s.getTasks();
        for (Task task : result) {
            System.out.println(task.toString());
        }
    }
}
