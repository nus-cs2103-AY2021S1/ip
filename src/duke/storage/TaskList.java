package duke.storage;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    protected final ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> list() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task complete(int index) {
        Task toComplete = tasks.get(index);
        toComplete.setCompleted();
        return toComplete;
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "The list is empty.";
        } else {
            String result = "";
            for (Task t : tasks) {
                result = result.concat(t.toString()).concat("\n");
            }
            return result;
        }
    }
}
