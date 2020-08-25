package duke.task;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

/**
 * A class contains the task list and deal with the command run on the task list.
 */
public class TaskList {

    List<Task> taskList;

    /**
     * Construct a new TaskList containing a empty task list.
     */
    public TaskList() { this(new ArrayList<>()); }

    /**
     * Construct a new TaskList containing a given task list.
     * @param taskList the given List of task.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Execute the command on the list of tasks.
     * @param commands an Array of String which contains command and its arguments.
     * @param ui the Ui to deal with the interactions with users.
     * @param storage the Storage to deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException thrown if the storage process goes wrong.
     */
    public void runCommand(String[] commands, Ui ui, Storage storage) throws DukeException {
        switch (commands[0]) {
            case "list": {
                printList(ui);
                break;
            }
            case "done": {
                markAsDone(ui, Integer.parseInt(commands[1]) - 1);
                break;
            }
            case "delete": {
                delete(ui, Integer.parseInt(commands[1]) - 1);
                break;
            }
            case "todo":
            case "deadline":
            case "event":
                addTask(ui, commands[0], commands[1], commands[2]);
        }
        storage.store(taskList);
    }

    /**
     * Print the list of task through the Ui.
     * @param ui the Ui to deal with the interactions with users.
     */
    public void printList(Ui ui) {
        ui.printList(this.taskList);
    }

    /**
     * Mark done the task in the list of task of a certain index and print through the Ui.
     * @param ui the Ui to deal with the interactions with users.
     * @param index the index of the task in the task list.
     */
    public void markAsDone(Ui ui, int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        ui.printDone(task);
    }

    /**
     * Delete the task in the list of task of a certain index and print through the Ui.
     * @param ui the Ui to deal with the interactions with users.
     * @param index the index of the task in the task list.
     */
    public void delete(Ui ui, int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        ui.printDelete(task, taskList.size());
    }

    /**
     * Add a new task of a certain type with description and time if needed,
     * and print through the Ui.
     * @param ui the Ui to deal with the interactions with users.
     * @param type the String denote the type of the task.
     * @param description the String denote the String of the task.
     * @param time the String denote the time of the task, or null.
     */
    public void addTask(Ui ui, String type, String description, String time) {
        Task task;
        switch (type) {
            case "todo": {
                task = new Todo(description);
                break;
            }
            case "deadline": {
                task = new Deadline(description, time);
                break;
            }
            case "event": {
                task = new Event(description, time);
                break;
            }
            default: task = null;
        }
        taskList.add(task);
        ui.printAdd(task, taskList.size());
    }
}
