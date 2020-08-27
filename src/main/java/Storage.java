import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String path) {
        this.filePath = path;
    }

    public TaskList load() throws AlisonException {
        TaskList tasks = new TaskList();
        try {
            File dataFile = new File(filePath);
            Scanner sc = new Scanner(dataFile);

            while (sc.hasNextLine()) {
                Task saved = Parser.parseTask(sc.nextLine());
                if (saved != null) {
                    tasks.add(saved);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw AlisonException.loadingException();
        }
        return tasks;
    }

    public void update(TaskList tasks) throws AlisonException {
        try {
            FileWriter writer = new FileWriter("./data/tasks.txt", false);

            for (Task task : tasks) {
                writer.write(task.savedFormat() + '\n');
            }
            writer.close();

        } catch (IOException e) {
            throw AlisonException.writingException();
        }
    }

}
