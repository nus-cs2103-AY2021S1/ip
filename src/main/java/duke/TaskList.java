package duke;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.task.Task;

public class TaskList {

    private ArrayList<Task> taskStore;

    public TaskList() {
        this.taskStore = new ArrayList<>();
    }

    public TaskList(ArrayList<String> data) throws DukeException {
        this.taskStore = new ArrayList<>();
        for (String s : data) {
            Task t = Parser.parseTaskData(s);
            taskStore.add(t);
        }
    }

    public void add(Task newTask) {
        taskStore.add(newTask);
    }

    public Task markTaskAsDone(int i) throws DukeException {
        Task doneTask = taskStore.get(i - 1).markAsDone();
        taskStore.set(i - 1, doneTask);
        return doneTask;
    }

    public Task delete(int i) {
        Task deletedTask = taskStore.remove(i - 1);
        return deletedTask;
    }

    public ArrayList<String> find(String keyword) {
        return mapToRepr(filterTasks(taskStore, task -> task.contains(keyword)), Task::toString);
    }

    public ArrayList<String> getListRepr() {
        return mapToRepr(taskStore, Task::toString);
    }

    public ArrayList<String> getData() {
        return mapToRepr(taskStore, Task::getData);
    }

    public String getListStatus() {
        int storeSize = taskStore.size();
        return "There " + (storeSize > 1 ? "are " : "is ") + "now " + storeSize + " " +
                (storeSize > 1 ? "tasks " : "task ") + "in your list!";
    }

    private static ArrayList<String> mapToRepr(ArrayList<Task> taskStore, Function<Task, String> mapper) {
        return taskStore.stream().map(mapper).collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<Task> filterTasks(ArrayList<Task> taskStore, Predicate<Task> pred) {
        return taskStore.stream().filter(pred).collect(Collectors.toCollection(ArrayList::new));
    }
}
