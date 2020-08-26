import java.time.LocalDate;
import java.util.ArrayList;

/** Contains the task list
 * e.g., it has operations to add/delete tasks in the list
 *
 * */
public class TaskList {
    /** Note: limit storage to 100 items **/
    private ArrayList<Task> tasks = new ArrayList<>(100);

    // Default Constructor
    public TaskList() {

    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    // Returns list of tasks
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    // Create To do Task
    public void createTodo(String command) {
        String[] instructions = command.split(" ", 2);
        Task t = new Todo(instructions[1]);
        tasks.add(t);
        System.out.println("added: " + t);
        System.out.println("There is now " + tasks.size() + " tasks in the list!\n");
    }

    // Create Deadline Task
    public void createDeadline(String command) {
        String[] instructions = command.split(" ", 2);
        String[] details = instructions[1].split(" /by ", 2);
        Task t = new Deadline(details[0], LocalDate.parse(details[1]));
        tasks.add(t);
        System.out.println("added: " + t);
        System.out.println("There is now " + tasks.size() + " tasks in the list!\n");
    }

    // Create Event Task
    public void createEvent(String command) {
        String[] instructions = command.split(" ", 2);
        String[] details = instructions[1].split(" /at ", 2);
        Task t = new Event(details[0], details[1]);
        tasks.add(t);
        System.out.println("added: " + t);
        System.out.println("There is now " + tasks.size() + " tasks in the list!\n");
    }

    // Marking Tasks as done
    public void markTaskDone(String command) throws DukeException {
        String[] instructions = command.split(" ", 2);
        int index = Integer.parseInt(instructions[1]) - 1;
        try {
            Task t = tasks.get(index);
            t.markedDone(true);
            System.out.println("Congratulations! I've helped you mark the task as done:");
            System.out.println("    " + t.toString() + "\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I don't think that's a valid index...");
        }
    }

    // Deleting Tasks
    public void deleteTask(String command) throws DukeException {
        String[] instructions = command.split(" ", 2);
        int index = Integer.parseInt(instructions[1]) - 1;
        try {
            Task t = tasks.get(index);
            System.out.println("Noted! I've helped you remove the following task:");
            System.out.print("    " + t.toString() + "\n");
            tasks.remove(index);
            System.out.println("    Now, there is " + tasks.size() + " tasks in the list!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I don't think that's a valid index...");
        }
    }

    public boolean isListEmpty() {
        if (tasks.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    public void printAllTasks() {
        // Prints all tasks in Duke's list
        for (int i = 0; i < tasks.size(); i++) {
            // Enumerator
            System.out.print((i+1) + ".");

            // Actual Task
            System.out.println(tasks.get(i));
        }
    }

}
