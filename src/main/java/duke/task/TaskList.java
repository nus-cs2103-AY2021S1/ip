package duke.task;

import duke.DukeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public String[] getTasks() {
        int numTasks = tasks.size();
        String[] taskStrings = new String[numTasks];
        for (int i = 0; i < numTasks; i++) {
            taskStrings[i] = tasks.get(i).toString();
        }
        return taskStrings;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTaskAsDone(int index) throws DukeException {
        try {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
        }
    }

    public Task deleteTask(int index) throws DukeException {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
        }
    }
}
