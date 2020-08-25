package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        list = tasks;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void markDone(int index) {
        this.list.get(index - 1).markAsDone();
    }

    public int size() {
        return list.size();
    }

    public Task getTask(int index) {
        return list.get(index - 1);
    }

    public int getSize() {
        return list.size();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(list);
    }

    public String listTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            output.append("\t ").append(i + 1).append(".").append(list.get(i)).append(i == list.size() - 1 ? "" : "\n");
        }
        return output.toString();
    }

    public String showTasksOnDate(LocalDate date) {
        Stream<Task> filtered = list.stream().filter(task -> task.getDate().equals(date));
        AtomicInteger i = new AtomicInteger(1);
        StringBuilder output = new StringBuilder();
        filtered.forEach(task -> {
            output.append("\t ").append(i.getAndIncrement()).append(".").append(task).append("\n");
        });
        if (i.intValue() == 1) {
            return "\t No tasks found on " + date.format(DateTimeFormatter.ofPattern("MMMM d yyyy"));
        }
        return output.toString();
    }

    public void deleteTask(int index) {
        this.list.remove(index - 1);
    }
}
