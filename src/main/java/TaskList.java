import java.util.ArrayList;

/**
 * The TaskList class holds and manages the current list of Tasks stored
 * by the user.
 */
public class TaskList {
    public static ArrayList<Task> list;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.list = loadedTasks;
    }

    /**
     * Prints a list of current tasks.
     */
    void printList() {
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println(i + ". " + this.list.get(i - 1));
        }
    }

    /**
     * Removes a task from the list of current tasks.
     * @param taskNo Index of task to remove.
     */
    void removeFromList(int taskNo) {
        Task removedTask = this.list.remove(taskNo);
        System.out.println("Well, if you insist. I've removed:");
        System.out.println(removedTask);
    }

    /**
     * Adds a task to the list of current tasks.
     * @param task Index of task to add.
     */
    void addToList(Task task) {
        System.out.println("Alright matey, I've added this task:");
        this.list.add(task);
        System.out.println(task);
        System.out.println("Looks like you have " + this.list.size() + " tasks in total.");
    }

    /**
     * Marks a task in list of current tasks as completed.
     * @param taskNo Index of task to mark.
     */
    void taskDone(int taskNo) {
        Task toBeDone = this.list.get(taskNo);
        toBeDone.markAsDone();
        this.list.set(taskNo, toBeDone);
        System.out.println("Good Job, this task is now done:");
        System.out.println(toBeDone);
    }

}
