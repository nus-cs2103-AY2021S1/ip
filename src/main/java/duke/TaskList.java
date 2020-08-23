package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
    List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task done(int index) {
        Task task = tasks.get(index);
        task.markDone();
        return task;
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }


    @Override
    public Iterator iterator() {
        return tasks.iterator();
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < tasks.size(); i++) {
            string += (i + 1) + ". " + tasks.get(i);

            if (i != tasks.size() - 1) {
                string += "\n";
            }
        }

        return string;
    }

}
