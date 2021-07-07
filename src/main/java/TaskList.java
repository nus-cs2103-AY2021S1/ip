import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a List of tasks that the user wants to keep track of and methods to interact with the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initialises a TaskList object from a file containing saved tasks.
     * @param file .txt file that contains saved Duke task data
     * @param ui Ui to display results and errors to the user
     */

    public TaskList(File file, Ui ui) {
        assert file != null;
        assert ui != null;

        this.tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] components = sc.nextLine().split(" \\| ");
                String taskId = components[0];
                boolean isComplete = components[1].equals("1");
                Priority priority = Priority.valueOf(components[2]);
                String taskDescription = components[3];

                switch (taskId) {
                case "T":
                    tasks.add(new Todo(taskDescription, priority, isComplete));
                    break;
                case "D":
                    String deadlineBy = components[4];
                    tasks.add(new Deadline(taskDescription, priority, deadlineBy, isComplete));
                    break;
                case "E":
                    String eventAt = components[4];
                    tasks.add(new Event(taskDescription, priority, eventAt, isComplete));
                    break;
                default:
                    break;
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
        }
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int id) {
        return tasks.remove(id);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }
}
