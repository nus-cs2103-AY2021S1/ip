import java.util.Iterator;
import java.util.ArrayList;

/**
 * contains the task list and has operations to alter the task list
 */

public class TaskList {
    private Storage storage;
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = this.storage.getTaskList();
    }

    public String numberOfTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("you have [")
                .append(this.tasks.size()).append("] task(s) in your list");
        return sb.toString();
    }

    public String returnList() {
        if (this.tasks.size() == 0) {
            return "there are no tasks in the list";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("here are the tasks in your list:\n");
            Iterator i = this.tasks.iterator();

            int counter = 1;
            sb.append(counter + ". ").append(i.next());
            while (i.hasNext()) {
                counter++;
                sb.append("\n").append(counter + ". ").append(i.next());
            }
            return sb.toString();
        }
    }

    public String done(int taskNumber) {
        Task task = this.tasks.get(taskNumber - 1);
        task.markAsDone();
        StringBuilder sb = new StringBuilder();
        sb.append("yay! i have marked this task as done: \n    ")
                .append(task)
                .append("\n" + this.numberOfTasks());

        this.storage.updateFile();
        return sb.toString();
    }

    public String delete(int taskNumber) {
        Task task = this.tasks.remove(taskNumber - 1);

        StringBuilder sb = new StringBuilder();
        sb.append("sure thing. i have removed this task: \n    ")
                .append(task).append("\n")
                .append(this.numberOfTasks());

        this.storage.updateFile();
        return sb.toString();
    }

    public String add(Task task) {
        this.tasks.add(task);

        StringBuilder sb = new StringBuilder();
        sb.append("got it! i have added the following task to your list:\n    ")
                .append(task)
                .append("\n" + this.numberOfTasks());

        this.storage.updateFile();
        return sb.toString();
    }
}
