import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Storage {
    protected String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    void createFile() throws IOException {
        File file = new File(this.filePath);
        File directory = file.getParentFile();
        if (!(directory.exists())) {
            directory.mkdir();
        }

        file.createNewFile();
    }

    public List<Task> loadFile() throws IOException {
        File file = new File(this.filePath);
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);

            while (s.hasNext()) {
                tasks.add(readData(s.nextLine()));
            }

            s.close();
            return tasks;
        } catch (FileNotFoundException e) {
            createFile();
        }
        return tasks;
    }

    Task readData(String s) {
        String[] arr = s.split(" \\| ");
        Task task;
        if (arr[0].equals("T")) {
            task = new Todo(arr[2]);
        } else if (arr[0].equals("D")) {
            task = new Deadline(arr[2], arr[3]);
        } else {
            task = new Event(arr[2], arr[3]);
        }

        if (arr[1].equals("1")) {
            task.completeTask();
        }

        return task;
    }

    public void saveFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        String textToAdd = "";

        for (Task task: tasks.getList()) {
            textToAdd += task.formatTaskForFile() + "\n";
        }

        fw.write(textToAdd);
        fw.close();
    }
}
