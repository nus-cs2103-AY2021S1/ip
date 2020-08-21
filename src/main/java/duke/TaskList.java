package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task add(Task task) {
        taskList.add(task);
        return task;
    }

    public Task remove(int taskNumber) {
        return taskList.remove(taskNumber);
    }

    public Task markAsDone(int taskNumber) {
        taskList.get(taskNumber).markAsDone();
        return taskList.get(taskNumber);
    }

    public String show(int taskNumber) {
        return taskList.get(taskNumber).showTask();
    }
    
    public List<Task> getAll() {
        return this.taskList;
    }

    public int size() {
        return this.taskList.size();
    }

    public TaskList viewAll(String dateStr) throws DateTimeException {
        LocalDate date = LocalDate.parse(dateStr);
        return new TaskList(
                this.taskList
                        .stream()
                        .filter(x ->
                                (x instanceof Event && ((Event) x).at.equals(date))
                                        || (x instanceof Deadline && ((Deadline) x).by.equals(date)))
                        .collect(Collectors.toList()));
    }
}
