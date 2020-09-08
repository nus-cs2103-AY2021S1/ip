package duke.task;

import java.util.ArrayList;
import java.util.PriorityQueue;

import duke.command.DukeException;

public class TaskList {

    private ArrayList<Task> taskList;
    private PriorityQueue<Task> taskPriority;

    private TaskList() {
        this.taskList = new ArrayList<>();
        this.taskPriority = new PriorityQueue<>(new TaskComparator());
    }

    /**
     * Adds a task to the Task List
     * @param task The task as instantiated from Parser to a Task object
     * @return String representation of the Task object that is added
     */
    public String addTask(Task task) {
        taskList.add(task);
        taskPriority.add(task);
        return task.toString();
    }

    /**
     * Deletes a task from the Task List
     * @param taskNumber The index of the task in the Task List
     * @return String representation of the Task object that is deleted
     */
    public String deleteTask(int taskNumber) throws DukeException {
        assert !taskPriority.isEmpty() : "Cannot delete from an Empty Tasklist";
        if (taskNumber > getListSize()) {
            String message = "Index not found! Are you sure you are typing the correct index?";
            throw new DukeException(message);
        }
        Task removedTask = null;
        for (int i = 0; i < taskNumber; i++) {
            removedTask = taskPriority.poll();
        }
        this.taskList.remove(removedTask);
        this.taskPriority.clear();
        refillTaskPriorityQueue();
        assert removedTask != null;
        return removedTask.toString();
    }

    /**
     * Updates a task in the Task List to mark it as done
     * @param taskNumber The index of the task in the Task List
     * @return String representation of the Task object that is updated
     */
    public String updateTask(int taskNumber) throws DukeException {
        assert !taskPriority.isEmpty() : "Cannot delete from an Empty Tasklist";
        if (taskNumber > getListSize()) {
            String message = "Index not found! Are you sure you are typing the correct index?";
            throw new DukeException(message);
        }
        Task updatedTask = null;
        for (int i = 0; i < taskNumber; i++) {
            updatedTask = taskPriority.poll();
        }
        assert updatedTask != null;
        updatedTask.setDone();
        this.taskPriority.clear();
        refillTaskPriorityQueue();
        return updatedTask.toString();
    }

    /**
     * Marks a Task as Important by changing the boolean property isImportant from false to true
     * @param taskNumber The index of the task as shown on List
     * @return String
     * @throws DukeException on User input error for Task number
     */
    public String setTaskAsImportant(int taskNumber) throws DukeException {
        assert !taskPriority.isEmpty() : "Cannot delete from an Empty Tasklist";
        if (taskNumber > getListSize()) {
            String message = "Index not found! Are you sure you are typing the correct index?";
            throw new DukeException(message);
        }
        Task importantTask = null;
        for (int i = 0; i < taskNumber; i++) {
            importantTask = taskPriority.poll();
        }
        assert importantTask != null;
        importantTask.setAsImportant();
        this.taskPriority.clear();
        refillTaskPriorityQueue();
        return importantTask.toString();
    }


    /**
     * Refreshes the Tasklist arraylist to remove all tasks in it
     */
    public void refreshTasklist() {
        this.taskList = new ArrayList<>();
        this.taskPriority.clear();
    }

    /**
     * Returns the current size of the task list
     * @return Integer value of the size of task list
     */
    public int getListSize() {
        return this.taskList.size();
    }

    /**
     * Returns the task of highest priority in queue.
     * @return Task
     */
    public Task getTask() {
        return this.taskPriority.poll();
    }

    /**
     * Creates a tasklist object
     * @return TaskList Object
     */
    public static TaskList createTaskList() {
        return new TaskList();
    }

    /**
     * Refills an Empty Priority Queue with the Tasks to ensure
     * all tasks are prioritised accordingly
     */
    public void refillTaskPriorityQueue() {
        for (int i = 0; i < getListSize(); i++) {
            this.taskPriority.add(taskList.get(i));
        }
    }
}
