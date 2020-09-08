package willy.task;

import willy.exceptions.WillyException;
import willy.store.TaskStore;
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
    private final String NON_EXISTENT_TASK_MESSAGE = "Task does not exist, please check the list again or add the task first ~";
    private final String TASK_DONE_MESSAGE = "Niceee I've marked this task as done!";
    private final String NO_MATCHING_TASK_MESSAGE = "There are no matching tasks found in the list. Try something else?";
    private final String MATCHING_TASK_MESSAGE = "Here are the matching tasks in your list:";
    private final String ADD_TASK_RESPONSE = "Ay here is the task you just added:\n";
    private final String REMOVE_TASK_RESPONSE = "Okai here is the task you just deleted:\n";
    private final String EMPTY_LIST_RESPONSE = "\tThere is no task in your list:>\n";
    private final String NON_EMPTY_LIST_RESPONSE = "\tHere are the tasks in your list to jolt ur memory:>\n";


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
                ADD_TASK_RESPONSE +
                "\t  " + task + "\n" +
                "\tNow you have " + listOfTasks.size() +
                " task(s), please don't forget!");

        return willyResponse;
    }

    /**
     * Remove task instructed by user from the list that bot use to keep track of the task.
     *
     * @param taskNum Task number that the user wants the bot to remove.
     */
    public String removeTask(int taskNum) {
        assert taskNum > 0 : "Please insert a task number greater than 0" ;
        try {
            int i = taskNum - 1;
            Task task = listOfTasks.get(i);
            listOfTasks.remove(i);
            storage.updateStorage(listOfTasks);
            String willyResponse = Willy.response(
                    REMOVE_TASK_RESPONSE +
                    "\t  " + task + "\n" +
                    "\tNow you have " + listOfTasks.size() +
                    " task(s) left ~");

            return willyResponse;

        } catch (Exception e) {
            WillyException error = new WillyException(NON_EXISTENT_TASK_MESSAGE);
            return error.toString();
        }
    }

    /**
     * Prints out all the tasks in the list.
     *
     * @return All the tasks in the list.
     */
    public String readList() {
        String list = Willy.getStyle();
        if (listOfTasks.size() == 0) {
            list += EMPTY_LIST_RESPONSE;
        } else {
            list += NON_EMPTY_LIST_RESPONSE;

            for (int i = 0; i < listOfTasks.size(); i++) {
                Task task = listOfTasks.get(i);
                list = list + "\t" + (i + 1) + ". " + task + "\n";
            }
        }

        list = list + Willy.getStyle();
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
            String willyResponse = Willy.response(TASK_DONE_MESSAGE + "\n\t   " + task);

            return willyResponse;

        } catch (Exception e) {
            WillyException error = new WillyException(NON_EXISTENT_TASK_MESSAGE);
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

        String filteredList = Willy.getStyle() + "\n";
        if (keyList.size() == 0) {
            filteredList = filteredList + "\t" + NO_MATCHING_TASK_MESSAGE + "\n";
        } else {
            for (int i = 0; i < keyList.size(); i++) {
                Task task = keyList.get(i);
                filteredList = filteredList + "\t" + MATCHING_TASK_MESSAGE + "\n"
                        + "\t  " + (i + 1) + "." + task + "\n";
            }
        }
        filteredList = filteredList + Willy.getStyle();

        return filteredList;
    }
}
