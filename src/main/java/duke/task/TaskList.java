package duke.task;

import duke.DukeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getNumTasks() {
        return this.tasks.size();
    }

    public String[] getTasks() {
        String[] taskStrings = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            taskStrings[i] = this.tasks.get(i).toString();
        }
        return taskStrings;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task markTaskAsDone(int index) throws DukeException {
        try {
            Task task = this.tasks.get(index - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
        }
    }

    public Task deleteTask(int index) throws DukeException {
        try {
            return this.tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
        }
    }
}
