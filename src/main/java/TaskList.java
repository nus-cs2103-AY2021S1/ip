import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public String replyNumTasks() {
        return String.format(
                "Now you have %d %s in the list.%n",
                getSize(),
                getSize() <= 1 ? "task" : "tasks"
        );
    }

    @Override
    public String toString() {
        int size = this.tasks.size();
        String response = size <= 1
            ? "Here is the task in your list:\n"
            : "Here are the tasks in your list:\n";

        for (int i = 0; i < size; i++) {
            response += String.format(
                "%d. %s%n",
                i + 1,
                this.tasks.get(i).toString()
            );
        }

        return response;
    }
}
