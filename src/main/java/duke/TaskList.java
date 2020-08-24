package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber - 1);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public TaskList getTaskListOnDate(LocalDate date) {
        TaskList result = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if ((task instanceof Deadline && ((Deadline) task).getBy().equals(date))
                    || task instanceof Event && ((Event) task).getAt().equals(date)) {
                result.addTask(task);
            }
        }
        return result;
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        return this.tasks.get(tasks.size() - 1);
    }

    public Task deleteTask(int taskNumber) {
        Task removedTask = this.tasks.get(taskNumber - 1);
        this.tasks.remove(taskNumber - 1);
        return removedTask;
    }

    public void markTaskAsDone(int taskNumber) {
        this.tasks.get(taskNumber - 1).markAsDone();
    }

    @Override
    public String toString() {
        String numberedList = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            numberedList += "\n\t" + (i + 1) + "." + this.tasks.get(i);
        }
        return numberedList;
    }
}
