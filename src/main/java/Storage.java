import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String storagePath = "data/duke.txt";

    public String[] getTasks() {
        File storageFile = new File(storagePath);
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(storageFile);
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            return lines.toArray(new String[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Storage file not found");
            return new String[0];
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
        Task todo = new Task("hello", false);
        Task event = new Event("hello", "NEVER", true);
        s.addTask(todo);
        s.addTask(event);
        String[] result = s.getTasks();
        for (String str : result) {
            System.out.println(str);
        }
    }
}
