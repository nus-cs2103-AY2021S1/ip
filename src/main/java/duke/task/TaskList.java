package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
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
        List<Task> filtered = list.stream().filter(task -> task.getDate().equals(date)).collect(Collectors.toList());
        if (filtered.size() == 0) {
            return "There are no tasks happening on: " + date.format(DateTimeFormatter.ofPattern("MMMM d yyyy"));
        }
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks happening on: ").append(date).append("\n");

        for (int i = 0; i < filtered.size(); i++) {
            output.append(String.format("\t %d. %s" + (i == filtered.size() - 1 ? "" : "\n"), i + 1, filtered.get(i)));
        }

        return output.toString();
    }

    public String showMatchingTasks(String keyword) {
        List<Task> matchingTasks = list.stream().filter(task -> task.includesKeyword(keyword)).collect(Collectors.toList());
        if (matchingTasks.size() == 1) {
            return "There are no tasks containing keyword: " + keyword;
        }
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks containing keyword: ").append(keyword).append("\n");

        for (int i = 0; i < matchingTasks.size(); i++) {
            output.append(String.format("\t %d. %s" + (i == matchingTasks.size() - 1 ? "" : "\n"), i + 1, matchingTasks.get(i)));
        }

        return output.toString();
    }

    public void deleteTask(int index) {
        this.list.remove(index - 1);
    }
}
