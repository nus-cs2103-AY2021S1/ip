package duke.data;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.ToDo;

public class DukeTaskListStub extends DukeTaskList {

    public final Task taskAdded = ToDo.createToDo("read book");
    public final Task taskDeleted = ToDo.createToDo("read book");
    public final int size = 1;
    public final ArrayList<Task> currentTasks = new ArrayList<>();

    private DukeTaskListSideEffects taskListSideEffects = DukeTaskListSideEffects.getInstance();

    public DukeTaskListStub() {
        super();
    }

    @Override
    public Task getTask(int index) {
        taskListSideEffects.getTask = true;
        return taskAdded;
    }

    @Override
    public void addTask(Task task) {
        taskListSideEffects.addTask = true;
    }

    @Override
    public Task deleteTask(int index) {
        taskListSideEffects.deleteTask = true;
        return taskDeleted;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setTasks(ArrayList<Task> tasks) {
        taskListSideEffects.setTasks = true;
    }

    @Override
    public ArrayList<Task> getTasks() {
        return currentTasks;
    }

    @Override
    public ArrayList<Task> findTasks(String keyword) {
        taskListSideEffects.findTasks = true;
        return currentTasks;
    }
}
