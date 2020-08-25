package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }


    public List<Task> getList() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public List<Task> find(String key) {
        return this.tasks.stream().filter(task -> task.getDesc().contains(key))
                .collect(Collectors.toList());
    }

    public Task setDone(int idx) {
        Task doneTask = tasks.get(idx).setDone();
        this.tasks.set(idx, doneTask);
        return doneTask;
    }

    public Task remove(int idx) {
        Task rmTask = tasks.get(idx);
        this.tasks.remove(idx);
        return rmTask;
    }

    public Task addTodo(String desc, boolean isDone) {
        Task newTask = new Todo(desc, isDone);
        this.tasks.add(newTask);
        return newTask;
    }

    public Task addTimedTask(String type, String desc, LocalDate date, boolean isDone) {
        Task newTask;
        if (type.equals("deadline")) {
            newTask = new Deadline(desc, date, isDone);
        } else {
            newTask = new Event(desc, date, isDone);
        }
        this.tasks.add(newTask);
        return newTask;
    }

    public Task addTimedTask(String type, String desc, LocalDate date, LocalTime time, boolean isDone) {
        Task newTask;
        if (type.equals("deadline")) {
            newTask = new Deadline(desc, date, time, isDone);
        } else {
            newTask = new Event(desc, date, time, isDone);
        }
        this.tasks.add(newTask);
        return newTask;
    }
}
