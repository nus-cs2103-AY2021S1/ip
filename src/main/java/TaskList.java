import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int count() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int number) {
        tasks.remove(number);
    }

    public TaskList findTasks(String word) {
        TaskList matchingTasks = new TaskList();

        for(Task task: tasks) {
            if(task.getDescription().contains(word)) {
                matchingTasks.addTask(task);
            }
        }

        return matchingTasks;
    }
}
