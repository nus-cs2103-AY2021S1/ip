package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Encapsulates commands received by Mrs Dino.
 */
public abstract class Command {
    /**
     * Checks whether the command is a terminal command.
     *
     * @return boolean determining whether the command will cause Mrs Dino to terminate.
     */
    public abstract boolean isExit();

    /**
     * Executes the corresponding command and returns the result of the command as CommandResult.
     *
     * @param taskList the task list object that tracks the current task list.
     * @param ui the instance of UI class that prints messages to the console.
     * @param storage the instance of Storage class that stores the changed data.
     * @return An instance of CommandResult that represents the result of the command execution.
     * @throws DukeException Encapsulates exceptions specific to Mrs Dino.
     */
    public abstract CommandResult execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Generates a string representation of the contents in the task list for the GUI to display.
     *
     * @param todoList Target todoList for the string representation.
     * @return A string representation of the target todoList.
     */
    public String taskListToString(ArrayList<Task> todoList) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < todoList.size(); i++) {
            int index = i + 1;
            str.append(index).append(". ").append(todoList.get(i).toString());
            if (i != todoList.size() - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }

    /**
     * Generates a String representation of the message to users after adding a task.
     *
     * @param targetTask Corresponding task that is added.
     * @param size New size of the arraylist of tasks
     * @return String representation of the message to users after adding a task..
     */
    public String addTaskToString(Task targetTask, int size) {
        StringBuilder str = new StringBuilder();
        str.append("Got it. I've added this task:\n");
        str.append(targetTask.toString()).append("\n");
        str.append("Now you have ").append(size).append(" tasks in the list.");
        return str.toString();
    }

    /**
     * Generates a String representation of the message to users after deleting a task.
     *
     * @param targetTask Corresponding task that is deleted.
     * @param size New size of the arraylist of tasks
     * @return String representation of the message to users after adding a task..
     */
    public String deleteTaskToString(Task targetTask, int size) {
        StringBuilder str = new StringBuilder();
        str.append("Noted. I've removed this task:\n");
        str.append(targetTask.toString()).append("\n");
        str.append("Now you have ").append(size).append(" tasks in the list.");
        return str.toString();
    }

    /**
     * Generates a String representation of the message to users after rescheduling a task.
     *
     * @param oldTask the task that we want to modify, with outdated date and time.
     * @param newTask the task after modification, with updated date and time.
     * @return String representation of the message to users after rescheduling the task.
     */
    public String rescheduleTaskToString(Task oldTask, Task newTask) {
        StringBuilder str = new StringBuilder();
        str.append("Got it. I've rescheduled this task:\n");
        str.append(oldTask.toString()).append("\n");
        str.append("To:\n");
        str.append(newTask.toString()).append("\n");
        return str.toString();
    }
}
