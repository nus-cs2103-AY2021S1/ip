package duke.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.task.Task;

public class TaskList {

    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void completeTask(int index) {
        this.tasks.get(index - 1).complete();
    }

    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public static TaskList parse(List<Task> tasks) {
        return new TaskList(tasks);
    }

    public List<Task> search(String keyword) {
        return tasks.stream()
                .filter(t -> Arrays.asList(t.getDescription().split(" "))
                        .contains(keyword))
                .collect(Collectors.toList());
    }

    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    public int size() {
        return this.tasks.size();
    }

}
