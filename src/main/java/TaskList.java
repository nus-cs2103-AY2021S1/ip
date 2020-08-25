import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
        }
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            String item = i + "." + tasks.get(i - 1).toString();
            System.out.println(item);
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        // inform user item has been added
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    // method to delete task
    public void deleteTask(int taskNo) throws DukeException {
        // verify task number exists, then delete
        if (taskNo - 1 < tasks.size()) {
            Task toDelete = tasks.get(taskNo - 1);
            tasks.remove(taskNo - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(toDelete.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            // task number does not exist
            throw new DukeException("Sorry, this task does not exist!\n");
        }
    }

    // method to mark task as done
    public void markTaskDone(int taskNo) throws DukeException {
        // verify task number exists, then mark as done
        if (taskNo - 1 < tasks.size()) {
            if (tasks.get(taskNo - 1).isDone) {
                // task marked as done already
                throw new DukeException("Task is already done! :)\n");
            } else {
                tasks.get(taskNo - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskNo - 1).toString());
            }
        } else {
            // task number does not exist
            throw new DukeException("Sorry, this task does not exist!\n");
        }
    }
}
