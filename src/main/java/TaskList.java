import java.util.ArrayList;

/**
 * Responsible for storing a list of Tasks and interaction with the Tasks.
 * A <code>TaskList</code> contains an <code>ArrayList</code> object.
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
     * Prints the list of tasks in order.
     *
     * @param ui A Ui object.
     */
    public String printList(Ui ui) {
        if (tasks.size() == 0) {
            String currentlyEmpty = "Your task list is currently empty.";
            ui.say(currentlyEmpty);
            return currentlyEmpty;
        }
        else {
            String currentList = "Here is your task list.";
            ui.say(currentList);
            currentList += "\n";
            int count = 1;
            for (Task task : tasks) {
                currentList += count + ". " + task + "\n";
                count++;
            }
            System.out.println(currentList);
            return currentList;
        }
    }

    /**
     * Marks the (number - 1)th task in the list as done.
     *
     * @param number Task number.
     * @param ui A Ui object.
     */
    public String markTaskDone(int number, Ui ui) {
        Task task = tasks.get(number - 1);
        task.markDone();
        String markedDone = "I have marked it as done!";
        ui.say(markedDone);
        markedDone += "\n" + task;
        System.out.println(task);
        return markedDone;
    }

    /**
     * Deletes the (number - 1)th task in the list.
     *
     * @param number Task number.
     * @param ui A Ui object.
     */
    public String deleteTask(int number, Ui ui) {
        Task task = tasks.get(number - 1);
        tasks.remove(number - 1);
        String deletedTask = "I have deleted this task!";
        ui.say(deletedTask);
        deletedTask += "\n" + task + "\n";
        System.out.println(task);
        String currentSize = "You have " + getListSize() + " items in your task list now.";
        ui.say(currentSize);
        return deletedTask + currentSize;
    }

    /**
     * Adds a task to the back of the list.
     *
     * @param task The Task to be added.
     * @param ui A Ui object.
     */
    public String addTask(Task task, Ui ui) {
        tasks.add(task);
        String addedTask = "You have " + getListSize() + " items in your task list now.";
        ui.say(addedTask);
        return addedTask;
    }

    /**
     * Finds a task that matches the String body parameter.
     *
     * @param body String to search for.
     * @param ui A Ui object.
     */
    public String findTask(String body, Ui ui) {
        if (tasks.size() > 0) {
            String matchingTasks = "Here are the matching tasks in your list:";
            ui.say(matchingTasks);
            matchingTasks += "\n";
            boolean noneFound = true;
            for (Task task : tasks) {
                if (task.toString().contains(body)) {
                    matchingTasks += task + "\n";
                    System.out.println(task);
                    noneFound = false;
                }
            }
            if (noneFound) {
                String noMatching = "No matching tasks";
                ui.say(noMatching);
                return noMatching;
            }
            return matchingTasks;
        } else {
            String noTasks = "You have no tasks yet.";
            ui.say(noTasks);
            return noTasks;
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