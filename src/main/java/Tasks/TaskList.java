package tasks;

import storage.Storage;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

/**
 * Contains the list of tasks.
 */
public class TaskList {

    /** The list of tasks */
    protected ArrayList<Task> tasks;

    /** Storage for storing user's data */
    protected Storage storage;

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
    public void addToFile(Task task) {
        String taskString = processTasks(task);
        storage.saveData(taskString);
        addToList(task);

        System.out.println("New task added!");
        System.out.println(task);
        System.out.println("You now have " + getNumList() + " tasks.");
    }

    /*public void readList() {
        if (storage.getNumOfTasks() == 0) {
            System.out.println("Looks like you don't have any tasks! Go on and add some!");
        } else {
            System.out.println("Here's all your tasks to complete:");
            storage.readFile();
            System.out.println("Time to get to work! :D");
        }
    }*/

    /**
     * Prints out all the tasks in the task list.
     */
    public void readList() {
        if (tasks.isEmpty()) {
            System.out.println("Looks like you don't have any tasks! Go on and add some!");
        } else {
            System.out.println("Here's all your tasks to complete:");
            int i = 1;
            for (Task task : tasks) {
                System.out.println(i + ". " + task);
                i++;
            }
            System.out.println("Time to get to work! :D");
        }
    }

}
