import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> load() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            List<Task> tasks = new ArrayList<>();

            while (sc.hasNext()) {
                String taskDetails = sc.nextLine();
                Task task;
                if (taskDetails.startsWith("T")) {
                    task = ToDos.load(taskDetails);
                } else if (taskDetails.startsWith("D")) {
                    task = Deadlines.load(taskDetails);
                } else if (taskDetails.startsWith("E")) {
                    task = Events.load(taskDetails);
                } else {
                    throw new IllegalArgumentException();
                }
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Uh-oh! The file could not be found!");
        }
    }

    public void save(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("./data/tasks.txt");
            for (Task t : tasks) {
                fw.write(t.saveAs() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}