package tasklist;

import java.io.File;
import java.util.ArrayList;

import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Contains the list of tasks.
 */
public class TaskList {

    /** The list of tasks */
    private ArrayList<Task> tasks;

    /** Storage for storing user's data */
    private Storage storage;

    private String fileName = "cait_data.txt";

    /**
     * Constructs a new TaskList object.
     * @param storage the storage to save and load data from
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load();
    }

    /**
     * Gets the storage for storing data.
     * @return the storage for storing data
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Adds a task to the task list.
     * @param task the tasks to be added to the task list
     */
    protected void addToList(Task task) {
        tasks.add(task);
    }

    public Task getFromList(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Gets the number of tasks in the task list.
     * @return the number of tasks in the task list.
     */
    public int getNumList() {
        return this.tasks.size();
    }

    /**
     * Checks if the text has a specified keyword
     * @param text the string of text to be searched
     * @param keyword the keyboard to search for
     * @return true if the text has the specified keyword
     */
    public boolean containsWord(String text, String keyword) {
        return text.contains(keyword);
    }

    /**
     * Checks if any tasks in the list has the specified keyword
     * @param keyword the keyword to find in the tasks
     * @return true if there are any tasks that has the keyword
     */
    public String findInList(String keyword) {
        String reply = "";
        int i = 1;
        for (Task task : tasks) {
            if (containsWord(task.getTaskName(), keyword)) {
                reply += i + ". " + task + "\n";
                i++;
            }
        }
        return reply;
    }

    /**
     * Updates the task in the list to be done.
     * @param index the index of task in the list
     */
    public void setDoneList(int index) {
        //exceptions are already caught in handleDone() method in Parser class
        try {
            Task doneTask = tasks.get(index - 1);
            assert index > 0;
            doneTask.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            ;
        } catch (NumberFormatException e) {
            ;
        }

    }

    /**
     * Deletes a task in the task list
     * @param index the index of task in the list
     */
    public void deleteList(int index) {
        //exceptions are already caught in handleDelete() method in Parser class
        try {
            tasks.remove(index - 1);
            assert index > 0;
        } catch (IndexOutOfBoundsException e) {
            ;
        } catch (NumberFormatException e) {
            ;
        }

    }

    /**
     * Processes the tasks to be saved into the data file in the correct format.
     * @param task the tasks to be saved into the data file
     * @return the tasks in the correct format
     */
    protected static String processTasks(Task task) {
        String result = "";
        int isDone = task.getIsDone().equals("[\u2713] ") ? 1 : 0;
        if (task instanceof Todo) {
            result = "T | " + isDone + " | " + task.getTaskName();
        } else if (task instanceof Deadline) {
            result = "D | " + isDone + " | " + task.getTaskName() + " | " + ((Deadline) task).getByDate();
        } else if (task instanceof Event) {
            result = "E | " + isDone + " | " + task.getTaskName() + " | " + ((Event) task).getAtDate();
        }
        return result;
    }

    /**
     * Adds a task to the data file and to the task list.
     * @param task the task to be added
     */
    public String addToFile(Task task) {
        String reply = "";
        String taskString = processTasks(task);
        storage.saveData(taskString);
        addToList(task);

        reply += "New task added!\n";
        reply += task + "\n";
        reply += "You now have " + getNumList() + " tasks.";
        return reply;
    }

    /**
     * Prints out all the tasks in the task list.
     */
    public String readList() {
        tasks = storage.load();
        String reply = "";
        if (tasks.isEmpty()) {
            reply = "Looks like you don't have any tasks! Go on and add some!";
            File currFile = new File(fileName);
            currFile.delete();
        } else {
            reply += "Here's all your tasks to complete:\n";
            int i = 1;
            for (Task task : tasks) {
                reply += i + ". " + task + "\n";
                i++;
            }
            reply += "Time to get to work! :D";
        }
        return reply;
    }

}
