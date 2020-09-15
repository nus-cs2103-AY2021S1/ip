package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds an task object to this TaskList object
     * Returns a string saying the task has been added and how many there are total
     * Or alternatively task is not added because it has a duplicate
     *
     * @param task
     * @return information string
     */
    public String addTask(Task task) {
        int duplicateTaskNum = findDuplicate(task);
        if (duplicateTaskNum == -1) {
            taskList.add(task);
            return ("Got it. I've added this task:\n  " +
                    task.toString() + "\n" +
                    "Now you have " + size() + " tasks in the list.");
        } else {
            return ("Task already exists:\n" +
                    taskList.get(duplicateTaskNum).toString() + "\n" +
                    "No new task is added");
        }
    }

    /**
     * Adds an task object to this TaskList object
     * Used for loading from save file
     *
     * @param task
     */
    public void loadInTask(Task task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Sends all the tasks in this list back in a string
     *
     * @return A header followed by a list of all the tasks
     */
    public String listOutTasks() {
        StringBuilder temp = new StringBuilder("Here are the tasks in your list:");
        int counter = 1;
        for (Task item: taskList) {
            temp.append("\n");
            temp.append(counter).append(". ").append(item.toString());
            counter++;
        }
        return temp.toString();
    }

    /**
     * Finds and returns all tasks with descriptions that contains the string provided
     *
     * @param string
     * @return A header followed by a list of matching tasks
     */
    public String search(String string) {
        StringBuilder temp = new StringBuilder("Here are the matching tasks in your list:");
        int counter = 1;
        for (Task item: taskList) {
            if (item.getDescription().contains(string)) {
                temp.append("\n");
                temp.append(counter).append(". ").append(item.toString());
                counter++;
            }
        }
        return temp.toString();
    }

    /**
     * Constructs a string from tasks that can be saved into a text file and can be read by FileManager
     *
     * @return A long string of all the tasks to be saved
     */
    public String allSaveString() {
        if (taskList.size() == 0) {
            return "";
        } else {
            StringBuilder temp = new StringBuilder();
            for (Task task : taskList) {
                temp.append("\n");
                temp.append(task.saveString());
            }
            return temp.substring(1);
        }
    }

    /**
     * Marks a task as done with a given position of the task in the list
     *
     * @param position of task in list
     * @return information string
     * @throws IndexOutOfBoundsException indicating position out of bounds of list
     */
    public String markDone(int position) throws IndexOutOfBoundsException {
        try {

            if (taskList.get(position - 1).markDone()) {
                return ("beri gude, finish that thing liao\n  " +
                        taskList.get(position - 1).toString());
            } else {
                return ("Task alr finish liao\n  " +
                        taskList.get(position - 1).toString());
            }

        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    /**
     * Deletes a task at a given position in the list
     *
     * @param position of task in list
     * @return information string
     * @throws IndexOutOfBoundsException indicating position out of bounds of list
     */
    public String deleteTask(int position) throws IndexOutOfBoundsException {
        try {

            Task task = taskList.get(position - 1);
            taskList.remove(position - 1);
            return ("Noted. I've removed this task:\n  " +
                    task.toString() + "\n" +
                    "Now you have " + size() + " tasks in the list.");

        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    // below are private methods
    private int findDuplicate(Task task) {
        for (int i = 0; i < taskList.size(); i++) {
            if (task.equals(taskList.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
