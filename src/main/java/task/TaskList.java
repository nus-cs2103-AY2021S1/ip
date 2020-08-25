package task;


import exceptions.DukeException;
import exceptions.InvalidTaskIndexException;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        if (isIndexInRange(index))
            return this.taskList.remove(index - 1);

        throw new DukeException("Oh no! Task number does not exist in task list.");
    }

    public int size() {
        return this.taskList.size();
    }

    public Task getTask(int index) throws DukeException {
        if (isIndexInRange(index))
            return this.taskList.get(index - 1);


        throw new DukeException("Oh no! Task number does not exist in task list.");



    }


    public void forEach(Consumer<Task> consumer) {
        this.taskList.forEach(consumer);
    }

    public boolean isIndexInRange(int index) {
        return index <= this.taskList.size() && index > 0;
    }

}
