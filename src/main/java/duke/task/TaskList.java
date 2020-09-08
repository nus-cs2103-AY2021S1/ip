package duke.task;

import duke.DukeException;
import duke.backend.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Responsible for storing a list of Tasks and interaction with the Tasks.
 * A <code>duke.task.TaskList</code> contains an <code>ArrayList</code> object.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        tasks = taskList;
    }

    @Override
    public String toString() {
        String listString = "";
        for (Task task : tasks) {
            listString += task + "\n";
        }
        return listString;
    }

    /**
     *
     */
    public String getList(Ui ui) {
        if (tasks.size() == 0) {
            return ui.sayCurrentListIsEmpty();
        }
        String currentList = "";
        int count = 1;
        for (Task task : tasks) {
            currentList += count + ". " + task + "\n";
            count++;
        }
        return ui.sayCurrentList(currentList);
    }

    /**
     *
     */
    public String markTaskDone(Ui ui, int taskNumber, Storage storage) throws IOException {
        assert taskNumber > 0 : "Number should be greater than 0";
        Task task = tasks.get(taskNumber - 1);
        task.markDone();
        storage.writeFile(this);
        return ui.sayMarkedAsDone(task);
    }

    /**
     *
     */
    public String deleteTask(Ui ui, int taskNumber, Storage storage) throws IOException {
        assert taskNumber > 0 : "Number should be greater than 0";
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        storage.writeFile(this);
        return ui.sayDeletedTask(task, getListSize());
    }

    public String snoozeTask(Ui ui, int taskNumber, Storage storage) throws DukeException, IOException {
        assert taskNumber > 0 : "Number should be greater than 0";
        Task task = tasks.get(taskNumber - 1);
        if (task.type == Task.Type.TODO) {
            throw new DukeException("TODO cannot be snoozed");
        }
        TimedTask newTask = (TimedTask) task;
        newTask.snooze();
        tasks.set(taskNumber - 1, newTask);
        storage.writeFile(this);
        return ui.sayTaskSnoozed(newTask);
    }

    /**
     * Adds a task to the back of the list.
     *
     * @param task The Task to be added.
     * @param ui A Ui object.
     */
    public String addTask(Task task, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        storage.writeFile(this);
        return ui.sayAddedTask(task, getListSize());
    }

    /**
     *
     */
    public String findTask(Ui ui, String[] parsedInput) {
        if (tasks.size() <= 0) {
            return ui.sayCurrentListIsEmpty();
        }
        String body = parsedInput[1];
        String foundTasks = "";
        boolean noneFound = true;
        for (Task task : tasks) {
            if (task.toString().contains(body)) {
                foundTasks += task + "\n";
                noneFound = false;
            }
        }
        if (noneFound) {
            return ui.sayNoMatchingFileFound();
        } else {
            return ui.sayFoundTasks(foundTasks);
        }
    }

    /**
     * Gets the size of the list.
     *
     * @return The list size.
     */
    public int getListSize() {
        return tasks.size();
    }
}
