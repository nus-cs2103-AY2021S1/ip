package duke;

import java.util.LinkedList;
import java.util.List;

import exception.DeleteOutOfBoundException;
import exception.DoneOutOfBoundException;


/**
 * An object which consist of list to save all the task.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }


    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * A function to print out all the task.
     */
    public String printList() {
        String s = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            s += (i + 1) + ". " + this.taskList.get(i) + "\n";
        }
        return s;
    }

    /**
     * A function to mark a task as done.
     * @param index which is the task to be marked as done.
     * @throws DoneOutOfBoundException if there are no such task.
     */
    public String changeIsDone(int index) throws DoneOutOfBoundException {
        try {
            this.taskList.get(index - 1).changeIsDone();
            String s = "This task has been mark as done.";
            s += this.taskList.get(index - 1);
            return s;
        } catch (IndexOutOfBoundsException e) {
            throw new DoneOutOfBoundException();
        }
    }

    /**
     * A function to delete a task from the list.
     * @param index the index of the task to be deleted.
     * @throws DeleteOutOfBoundException if there are no such task.
     */
    public String delete(int index) throws DeleteOutOfBoundException {
        try {
            Task cur = this.taskList.get(index - 1);
            this.taskList.remove(index - 1);
            String s = "This task has been deleted.";
            s += cur;
            return s;
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
    }

    /**
     * A function to find word in the task list.
     * @param search the key to search.
     */
    public String find(String search) {
        int len = this.taskList.size();
        String s = "";
        int index = 1;
        for (int i = 0; i < len; i++) {
            if (this.taskList.get(i).toString().contains(search)) {
                s += index + ". " + this.taskList.get(i).toString() + "\n";
                index++;
            }
        }
        return s;
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
