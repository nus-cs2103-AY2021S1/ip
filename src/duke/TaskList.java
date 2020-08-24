package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * List of tasks for adding, removing of tasks etc.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() { }

    /**
     * Creates a TaskList from a String array.
     *
     * @param taskList String array with each string representing a line of Task.plaintext().
     * @throws DukeException if newTaskTime cannot be parsed to DateTime format.
     */
    public TaskList(String[] taskList) throws DukeException {
        for (String plainText : taskList) {
            String[] plainTextSplit = plainText.split(" \\| ");
            String newTaskCategory = plainTextSplit[0];
            String newTaskStatus = plainTextSplit[1];
            String newTaskDescription = plainTextSplit[2];
            String newTaskTime = plainTextSplit.length > 3 ? plainTextSplit[3] : "";

            switch (newTaskCategory) {
            case "T":
                addTask(new ToDo(newTaskDescription, Boolean.parseBoolean(newTaskStatus)));
                break;
            case "E":
                addTask(new Event(newTaskDescription, Boolean.parseBoolean(newTaskStatus), newTaskTime));
                break;
            case "D":
                addTask(new Deadline(newTaskDescription, Boolean.parseBoolean(newTaskStatus), newTaskTime));
                break;
            }
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumberToMark the position of the task to delete within the tasklist.
     * @return the task that was deleted.
     */
    public Task deleteTask(int taskNumberToMark) {
        Task taskToMark = this.taskList.remove(taskNumberToMark - 1);
        return taskToMark;
    }

    /**
     * Marks a task from the task list as done.
     *
     * @param taskNumberToMark the position of the task to mark as done within the tasklist.
     * @return the task that was marked done.
     */
    public Task markTask(int taskNumberToMark) {
        Task taskToMark = this.taskList.get(taskNumberToMark - 1);
        taskToMark.markAsDone();
        return taskToMark;
    }

    /**
     * Adds a task to the taskList.
     *
     * @param newTask the task to be added.
     * @return the task that was added.
     */
    public Task addTask(Task newTask) {
        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Gets the size of the tasklist.
     *
     * @return size of the tasklist.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Gets the task at the specified index in the tasklist.
     *
     * @param index the index of the task to return.
     * @return the task at the specified index in the tasklist.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Gets a concatenated string of all the tasks in plainText for saving to file.
     *
     * @return a concatenated string of all the tasks in plainText.
     */
    public String getAllTasksPlainText() {
        String[] taskArr = new String[this.getSize()];
        for (int k = 0; k < this.getSize(); k++) {
            taskArr[k] = this.getTask(k).getPlainText();
        }
        String fileData = String.join("\n",
                taskArr);
        return fileData;
    }
}