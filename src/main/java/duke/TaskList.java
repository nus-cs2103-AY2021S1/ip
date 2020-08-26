package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public ArrayList<Task> findTasks(String userInput) {
        String keyword = userInput.substring(5);
        ArrayList<Task> findings = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTask().contains(keyword)) {
                findings.add(task);
            }
        }
        return findings;
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }


}
