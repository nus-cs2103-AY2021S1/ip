package duke;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void addATask(Task t) {
        tasks.add(t);
    }

    public Task removeATask(int ind) throws DukeException {
        if (ind < 0 || ind >= tasks.size()) {
            throw new DukeException("Task "+ ind +" does not exist!");
        }
        return tasks.remove(ind);
    }

    public Task completeTask(int ind) throws DukeException {
        if (ind < 0 || ind >= tasks.size()) {
            throw new DukeException("Task "+ ind +" does not exist!");
        }
        Task t = tasks.get(ind);
        t.completeTask();
        return t;
    }
    public int getNumberOfTasks() {
        return tasks.size();
    }

    public String printTask(int ind) throws DukeException {
        if (ind < 0 || ind >= tasks.size()) {
            throw new DukeException("Task "+ ind +" does not exist!");
        }
        return tasks.get(ind).toString();
    }

    public String printTasks() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        int i=0;
        for(Task task:tasks){
            str.append(String.format("%d.%s", ++i, task));
            if (i != tasks.size()) {
                str.append("\n");
            }
        }
        return str.toString();
    }
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

}
