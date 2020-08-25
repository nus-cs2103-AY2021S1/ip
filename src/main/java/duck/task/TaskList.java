package duck.task;

import duck.Colour;
import duck.exception.DuckException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public String[] getStatuses() {
        String[] statuses = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            statuses[i] = "" + (i + 1) + ". " + this.tasks.get(i).getStatus();
        }
        return statuses;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public Task markDone(int index) throws DuckException {
        if (index > this.tasks.size()) {
            throw new DuckException("No such task with that number!");
        } else {
            Task task = this.tasks.get(index - 1);
            task.markDone();
            return task;
        }
    }

    public Task deleteTask(int index) throws DuckException {
        if (index > this.tasks.size()) {
            throw new DuckException("No such task with that number!");
        } else {
            Task task = this.tasks.get(index - 1);
            this.tasks.remove(index - 1);
            return task;
        }
    }
}
