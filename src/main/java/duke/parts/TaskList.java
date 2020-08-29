package duke.parts;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void updateList(Storage storage) {
        storage.save(tasks);
    }

    public void addTask(Task task, Storage storage) {
        tasks.add(task);
        updateList(storage);
    }

    public Task deleteTask(int index) {
        Task removed = tasks.remove(index);
        return removed;
    }

    public int numTask() {
        return tasks.size();
    }

    public ArrayList<Task> find(String input) {
        ArrayList<Task> temp = new ArrayList<>();

        for(Task t: tasks) {
            if(t.contains(input)) {
                temp.add(t);
            }
        }
        return temp;
    }
}
