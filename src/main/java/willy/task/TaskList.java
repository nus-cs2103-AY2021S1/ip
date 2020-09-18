package willy.task;

import willy.exceptions.WillyException;
import willy.store.TaskStore;
import willy.ui.Response;
import willy.ui.Willy;

import java.util.ArrayList;

/**
 * Handles the different actions that can be done by the bot.
 * Bot can add tasks, remove tasks, read list of tasks,
 * update tasks that are done and find tasks.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;
    private ArrayList<Task> keyList;
    private TaskStore storage;

    public TaskList(ArrayList<Task> listOfTasks, TaskStore storage) {
        this.listOfTasks = listOfTasks;
        this.storage = storage;
    }

    public ArrayList<Task> getList() {
        return listOfTasks;
    }

    public ArrayList<Task> getKeyList() {
        return keyList;
    }

    /**
     * Add task given by user into list the bot use to keep track of the task.
     *
     * @param task Task that the user wants the bot to keep track of.
     */
    public String addToList(Task task) {
        listOfTasks.add(task);
        storage.updateStorage(listOfTasks);
        String willyResponse = Willy.response(
                Response.ADD_TASK
                        + "  " + task + "\n"
                        + "Now you have " + listOfTasks.size()
                        + " task(s), please don't forget!");

        return willyResponse;
    }

    /**
     * Remove task instructed by user from the list that bot use to keep track of the task.
     *
     * @param taskNum Task number that the user wants the bot to remove.
     */
    public String removeTask(int taskNum) {
        assert taskNum > 0 : "Please insert a task number greater than 0";
        try {
            int i = taskNum - 1;
            Task task = listOfTasks.get(i);
            listOfTasks.remove(i);
            storage.updateStorage(listOfTasks);
            String willyResponse = Willy.response(
                    Response.REMOVE_TASK
                            + "  " + task + "\n"
                            + "Now you have " + listOfTasks.size()
                            + " task(s) left ~");

            return willyResponse;

        } catch (Exception e) {
            WillyException error = new WillyException(Response.NO_TASK.toString());
            return error.toString();
        }
    }

    public String updateTask(int taskNum, Task editedTask) {
        assert taskNum > 0 : "Please insert a task number greater than 0";
        try {
            int i = taskNum - 1;
            listOfTasks.set(i, editedTask);
            storage.updateStorage(listOfTasks);
            String willyResponse = Willy.response(
                    Response.UPDATE_TASK
                            + "  " + editedTask + "\n"
                            + "Now you have " + listOfTasks.size()
                            + " task(s), please don't forget!");

            return willyResponse;

        } catch (Exception e) {
            WillyException error = new WillyException(Response.NO_TASK.toString());
            return error.toString();
        }
    }

    /**
     * Prints out all the tasks in the list.
     *
     * @return All the tasks in the list.
     */
    public String readList() {
        String list = "\n";
        if (listOfTasks.size() == 0) {
            list += Response.EMPTY_LIST;
        } else {
            list += Response.NON_EMPTY_LIST;

            for (int i = 0; i < listOfTasks.size(); i++) {
                Task task = listOfTasks.get(i);
                list = list + (i + 1) + ". " + task + "\n";
            }
        }
        return list;
    }

    /**
     * Update task in the list that is done by the user.
     *
     * @param taskNum Index of the task according to the list shown to the user.
     */
    public String setTaskDone(int taskNum) {
        assert taskNum > 0 : "Please insert a task number greater than 0";
        try {
            int i = taskNum - 1;
            Task task = listOfTasks.get(i);
            task.setTaskDone(true);
            storage.updateStorage(listOfTasks);
            String willyResponse = Willy.response(Response.DONE_TASK + "\n   " + task);

            return willyResponse;

        } catch (Exception e) {
            WillyException error = new WillyException(Response.NO_TASK.toString());
            return error.toString();
        }
    }

    /**
     * Filter out list of tasks that contains the keyword.
     *
     * @param keyword The word that the user wants to use to find related tasks.
     * @return The list of related tasks.
     */
    public String findTask(String keyword) {
        keyList = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task tempTask = listOfTasks.get(i);
            if (tempTask.getTask().contains(keyword)) {
                keyList.add(tempTask);
            }
        }

        String filteredList = "\n";
        if (keyList.size() == 0) {
            filteredList = filteredList + Response.NON_MATCHING_TASK + "\n";
        } else {
            for (int i = 0; i < keyList.size(); i++) {
                Task task = keyList.get(i);
                filteredList = filteredList + Response.MATCHING_TASK + "\n"
                        + (i + 1) + "." + task + "\n";
            }
        }
        return filteredList;
    }
}
