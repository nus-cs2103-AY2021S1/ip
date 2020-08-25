import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> list;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.list = loadedTasks;
    }

    void printList() {
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println(i + ". " + this.list.get(i - 1));
        }
    }

    void removeFromList(int taskNo) {
        Task removedTask = this.list.remove(taskNo);
        System.out.println("Well, if you insist. I've removed:");
        System.out.println(removedTask);
    }

    void addToList(Task task) {
        System.out.println("Alright matey, I've added this task:");
        this.list.add(task);
        System.out.println(task);
        System.out.println("Looks like you have " + this.list.size() + " tasks in total.");
    }

    void taskDone(int taskNo) {
        Task toBeDone = this.list.get(taskNo);
        toBeDone.markAsDone();
        this.list.set(taskNo, toBeDone);
        System.out.println("Good Job, this task is now done:");
        System.out.println(toBeDone);
    }

}
