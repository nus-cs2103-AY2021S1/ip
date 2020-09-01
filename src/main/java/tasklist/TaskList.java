package tasklist;

import static java.lang.Integer.parseInt;

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

    /**
     * Gets the number of tasks in the task list.
     * @return the number of tasks in the task list.
     */
    protected int getNumList() {
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
     * @param command the user's input
     */
    public void setDoneList(String command) {
        try {
            int index = parseInt(command.split(" ")[1]);
            Task doneTask = tasks.get(index - 1);
            doneTask.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            ;
        } catch (NumberFormatException e) {
            ;
        }

    }

    /**
     * Deletes a task in the task list
     * @param command the user's input
     */
    public void deleteList(String command) {
        try {
            int index = parseInt(command.split(" ")[1]);
            tasks.remove(index - 1);
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
        String reply = "";
        if (tasks.isEmpty()) {
            reply = "Looks like you don't have any tasks! Go on and add some!";
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
