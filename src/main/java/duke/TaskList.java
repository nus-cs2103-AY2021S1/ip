package duke;

import duke.tasks.Task;
import duke.text.TextBuilder;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds an task object to this TaskList object
     * Returns a string saying the task has been added and how many there are total
     * Or alternatively task is not added because it has a duplicate
     *
     * @param task task to be added to list
     * @return information string
     */
    public String addTask(Task task) {
        int duplicateTaskNum = findDuplicate(task);
        if (duplicateTaskNum == -1) {
            taskList.add(task);
            return TextBuilder.buildTaskAddedSuccessMsg(task, taskList.size());
        } else {
            return TextBuilder.buildDuplicateTaskMsg(taskList.get(duplicateTaskNum), duplicateTaskNum);
        }
    }

    /**
     * Adds an task object to this TaskList object
     * Used for loading from save file
     *
     * @param task task to be loaded to list
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
     * @return a ArrayList of all the tasks
     */
    public ArrayList<Task> allTasks() {
        return taskList;
    }

    /**
     * Finds and returns all tasks with descriptions that contains the string provided
     *
     * @param string search term
     * @return a ArrayList of all the matching tasks
     */
    public ArrayList<Task> search(String string) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task item: taskList) {
            if (item.getDescription().contains(string)) {
                temp.add(item);
            }
        }
        return temp;
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
        assert position < taskList.size() && position >= 0 : " IndexOutOfBounds when marking a task done";
        Task toBeMarked = taskList.get(position - 1);
        return TextBuilder.buildMarkDoneMsg(toBeMarked, position);
    }

    /**
     * Deletes a task at a given position in the list
     *
     * @param position of task in list
     * @return information string
     * @throws IndexOutOfBoundsException indicating position out of bounds of list
     */
    public String deleteTask(int position) throws IndexOutOfBoundsException {
        assert position < taskList.size() && position >= 0 : " IndexOutOfBounds when deleting task";
        Task task = taskList.get(position - 1);
        taskList.remove(position - 1);
        return TextBuilder.buildDeleteTaskMsg(task, position, taskList.size());

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
