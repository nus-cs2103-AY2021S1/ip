import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    public final List<Task> list;

    public Storage(String filePath) throws FileNotFoundException, InvalidCommandException {
        this.filePath = filePath;
        list = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String taskType = sc.next();
            int done = sc.nextInt();
            String description = sc.next();
            description = sc.nextLine();
            Task toAdd;
            if (taskType == "T") {
                toAdd = new ToDo(description);
            } else {
                int position = description.indexOf('|');
                String time = description.substring(position + 6);
                description = description.substring(0, position - 1);
                if (taskType == "D") {
                    toAdd = new Deadline(description, time);
                } else if (taskType == "E") {
                    toAdd = new Event(description, time);
                } else {
                    throw new InvalidCommandException("Invalid input file format");
                }
            }
            if (done == 1) {
                toAdd.markAsDone();
            }
            list.add(toAdd);
        }
    }

    public void addToList(Task task) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.output());
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    public void deleteTask(List<Task> list) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : list) {
                fw.write(task.output());
            }
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}
