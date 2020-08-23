import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DukeStorage {
    private String filePath;

    public DukeStorage(String filePath) {
        this.filePath = filePath;
        createStorage();
    }

    public void createStorage() {
        try {
            File file = new File(filePath);
//            new File("data").mkdirs();
            file.getParentFile().mkdirs();
            if (file.createNewFile()) {
                System.out.println("Duke storage is ready to use :-)");
            } else {
                System.out.println("Duke storage already exists, good to go!");
            }
        } catch (IOException ex) {
            System.out.println("Error!");
            ex.printStackTrace();
        }
    }

    public void reloadStorage(List<Task> tasks) throws FileNotFoundException, DukeException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            // decode and add the task
            String line = sc.nextLine();
            tasks.add(DukeInterpreter.decode(line));
        }
    }

    public void saveStorage(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(DukeInterpreter.encode(task) + "\n");
        }
        writer.close();
    }
}
