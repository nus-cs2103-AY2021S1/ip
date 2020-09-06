package duke.model;

import duke.exceptions.DukeException;
import duke.model.task.Task;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public void markTaskDone(int index) throws DukeException {
        try {
            Task completedTask = taskList.get(index);
            completedTask.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index!");
        }
    }

    public void deleteTask(int index) throws DukeException {
        try {
            Task toDeleteTask = taskList.get(index);
            taskList.remove(toDeleteTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index!");
        }
    }

    public Task getTask(int index) throws DukeException {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index!");
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public ArrayList<Task> findTasks(String searchTerm) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getDescription().contains(searchTerm)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public int size() {
        return taskList.size();
    }
}
