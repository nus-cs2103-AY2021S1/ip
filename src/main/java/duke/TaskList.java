package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasks) {
        this.tasklist = tasks;
    }

    public ArrayList<Task> getTasklist() {
        return this.tasklist;
    }

    public Task addToDo(String description) throws DukeException {
        Task t = new Todo(description);
        this.tasklist.add(t);
        return t;
    }

    public Task addDeadline(String description, String by) throws DukeException {
        Task t = new Deadline(description, by);
        this.tasklist.add(t);
        return t;
    }

    public Task addEvent(String description, String at) throws DukeException {
        Task t = new Event(description, at);
        this.tasklist.add(t);
        return t;
    }

    public Task deleteTask(int taskIndex) throws DukeException {
        try {
            Task t = this.tasklist.get(taskIndex - 1);
            this.tasklist.remove(taskIndex - 1);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    public Task doneTask(int taskIndex) throws DukeException {
        try {
            Task t = tasklist.get(taskIndex - 1);
            t.markAsDone();
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    public Task get(int index) {
        return tasklist.get(index);
    }

}
