package duke;

import java.time.LocalDateTime;

import java.util.ArrayList;

import task.Task;
import task.Event;
import task.Todo;
import task.Deadline;

/**
 * TaskList contains the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the String representation of all the tasks in the list with numbering.
     *
     * @return List of all tasks
     */
    public String listTasks() {
        String listOfTasks = "";

        for (int i = 0; i < this.tasks.size(); i++) {
            listOfTasks = listOfTasks + (i + 1) + ". " + this.tasks.get(i).toString()
                    + (i == this.tasks.size() - 1 ? "" : "\n");
        }

        return listOfTasks;
    }

    /**
     * Creates a new Task object to represent the task together with all the information provided.
     *
     * @param typeOfTask The type of tasks to create.
     * @param description Brief description of the task.
     * @param timing The deadline/event time if applicable.
     * @param isDone The status of the task.
     * @return The respective Task object
     */
    public Task createTask(TaskType.TypeOfTask typeOfTask, String description, LocalDateTime timing, boolean isDone) {

        switch (typeOfTask) {
        case TODO:
            return new Todo(description, isDone);
        case DEADLINE:
            return new Deadline(description, timing, isDone);
        case EVENT:
            return new Event(description, timing, isDone);
        default:
            return new Task(description, isDone);
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param task The task number to be removed.
      */
    public void removeTask(int task) {
        this.tasks.remove(task);
    }

    /**
     * Returns the full list of the tasks.
     *
     * @return All the tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the list of tasks that match the keyword.
     *
     * @param keyword keyword to find.
     * @return Tasks matching keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>(this.tasks);
        foundTasks.removeIf(task -> !task.getTaskDescription().contains(keyword));
        return foundTasks;
    }
}
