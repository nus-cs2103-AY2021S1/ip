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
    @SuppressWarnings("checkstyle:MissingSwitchDefault")
    public TaskList(File file, Ui ui) {
        assert file != null;
        assert ui != null;

        this.tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] components = sc.nextLine().split(" \\| ");
                String taskId = components[0];
                String taskDescription = components[2];
                boolean isComplete = components[1].equals("1");

                //noinspection CheckStyle
                switch (taskId) {
                case "T":
                    tasks.add(new Todo(taskDescription, isComplete));
                    break;
                case "D":
                    String deadlineBy = components[3];
                    tasks.add(new Deadline(taskDescription, deadlineBy, isComplete));
                    break;
                case "E":
                    String eventAt = components[3];
                    tasks.add(new Event(taskDescription, eventAt, isComplete));
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
