package duke.stub.task;

import duke.task.Task;
import duke.task.TaskList;

public class TaskListStub implements TaskList {
    protected Task lastAddedTask;

    public void add(Task t, boolean shouldUpdateStorage) {
        this.lastAddedTask = t;
    }

    public Task get(int i) {
        if (i == 7) {
            return new EventStub();
        } else if (i == 9) {
            return new DeadlineStub();
        } else {
            return new TodoStub();
        }
    }

    public Task getLastAddedTask() {
        return lastAddedTask;
    }

    public Task remove(int i) {
        return new DeadlineStub();
    }

    public int size() {
        return 10;
    }

    public void update(int i) {

    }

    public static Task[] fillerTasks() {
        Task[] fillerTasks = new Task[3];
        fillerTasks[0] = new TodoStub();
        fillerTasks[1] = new EventStub();
        fillerTasks[1].markAsDone();
        fillerTasks[2] = new DeadlineStub();
        return fillerTasks;
    }
}
