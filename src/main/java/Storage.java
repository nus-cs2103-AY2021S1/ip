import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a storage object to deal with storing and retrieving tasks.
 */
public class Storage {
    private File file;

    /**
     * Creates a file path.
     *
     * @param filePath path to the file
     */
    Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Reads tasks from hard disk and return a TaskList.
     *
     * @return a task list
     * @throws IOException exception for loading file
     */
    public TaskList load() throws IOException {
        TaskList list = new TaskList();
        if (!file.createNewFile()) {
            readTask(list);
        }
        return list;
    }

    /**
     * Reads a tasks that is stored.
     *
     * @param list a task list
     * @throws IOException exception for accessing file
     */
    public void readTask(TaskList list) throws IOException {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            String description = task.substring(7);

            // check the 5th char is tick or cross
            boolean isDone = task.charAt(4) == '\u2713';
            Task newTask;

            // check the second character is T/D/E
            if (task.charAt(1) == 'T') {
                newTask = new Todo(description, isDone);
            } else if (task.charAt(1) == 'D') {
                newTask = new Deadline(description, isDone);
            } else {
                newTask = new Event(description, isDone);
            }
            list.addTask(newTask);
        }
        sc.close();
    }

    /**
     * Writes existing tasks to a local file.
     *
     * @param list a task list
     * @throws IOException exception for accessing file
     */
    public void writeToFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(file);
        List<Task> ls = list.getList();
        for (Task task : ls) {
            fw.write(task + "\n");
        }
        fw.close();
    }
}
