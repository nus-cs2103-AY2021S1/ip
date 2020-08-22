package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getList() {
        return tasks;
    }

    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void printTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid index given");
        }
        System.out.println((index + 1) + ". " + tasks.get(index));
    }

    public void printList(Predicate<Task> filter) {
        for (int i = 0; i < tasks.size(); i++) {
            if (filter.test(tasks.get(i))) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid index given");
        }
        return tasks.remove(index);
    }

    public Task completeTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid index given");
        }
        tasks.get(index).completeTask();
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }
}
