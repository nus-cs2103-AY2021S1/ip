import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a List of tasks that the user wants to keep track of and methods to interact with the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(File file, Ui ui) {
        this.tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] components = sc.nextLine().split(" \\| ");

                switch (components[0]) {
                    case "T":
                        tasks.add(new Todo(components[2], components[1].equals("1") ? true : false));
                        break;
                    case "D":
                        tasks.add(new Deadline(components[2], components[3], components[1].equals("1") ? true : false));
                        break;
                    case "E":
                        tasks.add(new Event(components[2], components[3], components[1].equals("1") ? true : false));
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
