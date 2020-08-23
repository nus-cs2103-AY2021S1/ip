package duke.storage;

import duke.task.*;

import java.util.ArrayList;

/**
 * Encapsulates the list of tasks that the user currently stores. Allows
 * for the deletion and addition of all types of supported tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public void completeTask(int index) {
        taskList.get(index).finishTask();
    }

    public void addDeadline(Deadline deadline) {
        taskList.add(deadline);
    }

    public void addEvent(Event event) {
        taskList.add(event);
    }

    public void addTodo(ToDo todo) {
        taskList.add(todo);
    }

}
