import java.util.ArrayList;

/**
 * contains the task list and has operations to alter the task list
 */

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String numberOfTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("you have [")
                .append(this.tasks.size()).append("] task(s) in your list");
        return sb.toString();
    }

    public Task done(int taskNumber) throws InvalidIndexException {
        Task task = getTaskNumber(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    public Task delete(int taskNumber) throws InvalidIndexException {
        Task task = getTaskNumber(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        return task;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public ArrayList<Task> getMatchingTasks(String item) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();

        for (Task task : tasks) {
            if (task.toString().contains(item)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    private Task getTaskNumber(int taskNumber) throws InvalidIndexException {
        try {
            return this.tasks.get(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("sorry! the task number: ")
                    .append(taskNumber + 1)
                    .append(" does not exist in your list");

            throw new InvalidIndexException(sb.toString());
        }
    }
}
