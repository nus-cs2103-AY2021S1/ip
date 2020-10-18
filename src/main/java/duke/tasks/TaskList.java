package duke.tasks;

import java.util.ArrayList;

/**
 * List of tasks.
 */
public class TaskList {

    private ArrayList<Task> listOfTasks = new ArrayList<>();

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Finds the size of the list of tasks.
     *
     * @return the size.
     */
    public int findListSize() {
        return listOfTasks.size();
    }

    /**
     * Prints out the tasks in the list one by one.
     */
    public String printTaskList() {

        String result = "Here are the tasks in your list: " + "\n";

        int number = listOfTasks.size();

        for (int i = 0; i < number; i++) {

            result = result + listOfTasks.get(i).showTask(i + 1);

        }
        return result;
    }

    /**
     * Removes the task from the list.
     *
     * @param index The position index of the task in the list.
     */
    public String deleteTask(int index) throws Exception {

        Task toBeDeletedTask = listOfTasks.get(index - 1);

        int sizeOfList = listOfTasks.size();

        listOfTasks.remove(index - 1);

        assert listOfTasks.size() < sizeOfList : "OOPS! Deleting task failed.";

        int size = listOfTasks.size();

        String result = "Noted. I've removed this task:" + "\n";

        result = result + toBeDeletedTask.toString() + "\n";

        if (size == 0) {
            result = result + "Now your task list is empty." + "\n";
        } else if (size == 1) {
            result = result + "Now you have 1 task in the list." + "\n";
        } else {
            result = result + "Now you have " + size + " tasks in the list" + "\n";
        }

        return result;

    }

    /**
     * Finds the task that fit the keyword.
     *
     * @param keyWord The keyword to be used for searching
     * @return An ArrayList of tasks that meet the searching requirements
     */
    public String findTask(String keyWord) {
        String result = "";
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (Task task : listOfTasks) {
            String[] words = task.name.split(" ");
            for (String word : words) {
                if (word.contains(keyWord)) {
                    tasksFound.add(task);
                    break;
                }
            }
        }
        if (tasksFound.size() == 0) {
            result = result + "Sorry, no task related to " + keyWord + " is found." + "\n";
        } else {
            result = result + "Here are the tasks found: " + "\n";
            for (Task task : tasksFound) {
                result = result + task.toString() + "\n";
            }
        }
        return result;
    }

    /**
     * Adds the task to the list.
     *
     * @param task The task to be added.
     */
    public String addTask(Task task) {

        listOfTasks.add(task);

        int initialSize = listOfTasks.size();

        int taskSize = listOfTasks.size();

        assert taskSize > initialSize : "OOPS! Adding task failed.";

        String result = "Got it. I've added this task:" + "\n" + task.toString() + "\n"
                + "Now you have " + taskSize + " tasks in the list." + "\n"
                + "Good luck with the quest!\n";

        return result;
    }

    /**
     * Marks the task as done.
     *
     * @param index The position index of the task.
     */
    public String markAsDone(int index) {

        return listOfTasks.get(index - 1).markAsDone();

    }

    /**
     * Gets the list of tasks.
     * @return The list of tasks
     */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * Describes the list of the tasks.
     * @return The description of each task in the list
     */
    public String toString() {
        String result = "";
        for (Task task : listOfTasks) {
            result = result + task.toString() + "\n";
        }
        return result;
    }

}
