package main.java;

import java.util.LinkedList;
import java.util.List;
import Exception.DoneOutOfBoundException;
import Exception.DeleteOutOfBoundException;

/**
 * An object which consist of list to save all the task.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new LinkedList<>();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void printList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println( (i + 1) + ". " + this.taskList.get(i));
        }
    }

    /**
     * A function to mark a task as done.
     * @param index which is the task to be marked as done.
     * @throws DoneOutOfBoundException if there are no such task.
     */
    public void changeIsDone(int index) throws DoneOutOfBoundException {
        try {
            this.taskList.get(index - 1).changeIsDone();
            System.out.println("This task has been mark as done.");
            System.out.println(this.taskList.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new DoneOutOfBoundException();
        }
    }

    /**
     * A function to delete a task from the list.
     * @param index the index of the task to be deleted.
     * @throws DeleteOutOfBoundException if there are no such task.
     */
    public void delete(int index) throws DeleteOutOfBoundException {
        try {
            Task cur = this.taskList.get(index - 1);
            this.taskList.remove(index - 1);
            System.out.println("This task has been deleted.");
            System.out.println(cur);
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteOutOfBoundException();
        }
    }

    /**
     * A function to add a task to the list.
     * @param task task to be added to the list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("Added new task " + task);
    }

    /**
     * A function to return the number of all task.
     * @return int which is the size of the list.
     */
    public int size() {
        return this.taskList.size();
    }

    public void removeAll() {
        this.taskList.clear();
    }
}
