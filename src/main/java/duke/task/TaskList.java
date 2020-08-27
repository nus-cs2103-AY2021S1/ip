package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class is used to store different Task objects and
 * allows the user to modify the list of tasks through
 * this class.
 */
public class TaskList {

    private List<Task> list;
    private int activeTasks;
    private int completedTasks;

    public TaskList() {
        this.list = new ArrayList<>();
        this.activeTasks = 0;
        this.completedTasks = 0;
    }

    public List<Task> getList() {
        return this.list;
    }

    /**
     * Adds a Task to the list.
     * @param item Task object to be added on to the list.
     * @return String result that describes the item added
     * if successful.
     */
    public String addItem(Task item) {
        if (item == null) {
            return "Task is null! Nothing was added.";
        }
        this.list.add(item);
        if (item.done) {
            this.completedTasks += 1;
        } else {
            this.activeTasks += 1;
        }
        return "added: " + item + "\nActive Tasks: " +
                this.activeTasks + "\nCompleted Tasks: " + this.completedTasks;
    }

    /**
     * Deletes a Task from the list.
     * @param index The index of the Task object to be deleted.
     * @return String result that describes the deleted Task
     * if successful.
     */
    public String deleteItem(int index) {
        if (index >= this.list.size() || index < 0) {
            return "Please choose a valid task to delete";
        }
        Task deletedTask = this.list.remove(index);
        if (deletedTask.done) this.completedTasks -= 1; else this.activeTasks -= 1;
        return "Noted. I have deleted the following task: \n"
                + deletedTask + "\nActive Tasks: " +
                this.activeTasks + "\nCompleted Tasks: " + this.completedTasks;
    }

    /**
     * Marks a Task as completed.
     * @param index The index of the Task object to be marked as done.
     * @return String result that describes the completed Task
     * if successful.
     */
    public String markDone(int index) {
        if (index >= this.list.size() || index < 0) {
            return "Please choose a valid task to mark as done";
        }
        if (!this.list.get(index).done) {
            this.activeTasks -= 1;
            this.completedTasks += 1;
            return this.list.get(index).markDone() + "\nActive Tasks: " +
                    this.activeTasks + "\nCompleted Tasks: " + this.completedTasks;
        }
        return "The task is already done!";
    }

    /**
     * Marks a Task as not completed.
     * @param index The index of the Task object to be marked as not done.
     * @return String result that describes the uncompleted Task if successful.
     */
    public String revertDone(int index) {
        if (index >= this.list.size() || index < 0) {
            return "Please choose a valid task to mark as not done";
        }
        if (this.list.get(index).done) {
            this.activeTasks += 1;
            this.completedTasks -= 1;
            return this.list.get(index).revertDone() + "\nActive Tasks: " +
                    this.activeTasks + "\nCompleted Tasks: " + this.completedTasks;
        }
        return "The task is not yet done!";
    }

    /**
     * Custom toString representation of a TaskList which provides a view of the
     * current Tasks in the list along with additional information such as
     * number of active tasks and completed tasks.
     * @return String representation of current Tasks in the list.
     */
    @Override
    public String toString() {
        if (this.list.size() == 0) {
            return "There are currently no tasks.";
        }
        String result = "Current tasks:\n";
        for (int i = 1; i <= this.list.size(); i++) {
            result += i + ". " + this.list.get(i - 1) + "\n";
        }
        result += "\nActive Tasks: " +
                this.activeTasks + "\nCompleted Tasks: " + this.completedTasks;
        return result;
    }
}
