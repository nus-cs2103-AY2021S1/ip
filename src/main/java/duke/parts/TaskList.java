package duke.parts;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    static String LINE = "    ____________________________________________________________";
    static String INDENT = "    ";

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
}
