package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

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

    public TaskList viewAll(LocalDate date) {
        return new TaskList(
                this.taskList
                        .stream()
                        .filter(x ->
                                (x instanceof Event && ((Event) x).getDate().equals(date))
                                        || (x instanceof Deadline && ((Deadline) x).getDate().equals(date)))
                        .collect(Collectors.toList()));
    }

    /**
     * Find all tasks that contain a specific keyword
     * 
     * @param keyword user-inputted keyword
     * @return a Tasklist containing all tasks that contain a specific keyword
     */
    public TaskList find(String keyword) {
        return new TaskList(
                this.taskList
                        .stream()
                        .filter(x -> x.getDescription().contains(keyword))
                        .collect(Collectors.toList()));
    }
}
