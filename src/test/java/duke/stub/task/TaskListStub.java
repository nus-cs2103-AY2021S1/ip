package duke.stub.task;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Arrays;
import java.util.function.Consumer;

public class TaskListStub implements TaskList {
    public void add(Task t) {
        // nothing
    }

    public Task get(int i) {
        return new TodoStub();
    }

    public Task remove(int i) {
        return new TodoStub();
    }

    public int size() {
        return 0;
    }

    public void forEach(Consumer<Task> action) {
        Task[] fillerTasks = fillerTasks();
        Arrays.stream(fillerTasks).forEach(action);
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
