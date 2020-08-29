import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private final File file;

    public Storage(String filepath) {
        this.file = new File(filepath);
        file.getParentFile().mkdir();
        try {
            file.createNewFile(); // returns true if filepath does not exist and a new file is created
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> load() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            List<Task> taskList = new ArrayList<>();
            while (sc.hasNextLine()) {
                String loadTask = sc.nextLine();
                Task task;
                if (loadTask.startsWith("T")) {
                    task = ToDo.load(loadTask);
                } else if (loadTask.startsWith("D")) {
                    task = Deadline.load(loadTask);
                } else if (loadTask.startsWith("E")) {
                    task = Event.load(loadTask);
                } else {
                    break;
                }
                taskList.add(task);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Sorry!!! File not found.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry!!! Previous tasks not saved properly.");
        }
    }

    public void save(List<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                int isFinished = task.isDone ? 1 : 0;
                fw.write(task.save(isFinished) + "\n"); // write the task onto txt file with the | format
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry!!! Encountered difficulties when saving tasks.");
        }
    }
}