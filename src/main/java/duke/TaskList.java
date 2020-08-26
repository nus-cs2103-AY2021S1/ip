package duke;

import java.util.ArrayList;

/**
 *
 */
public class TaskList {
    public ArrayList<Task> tasks;

    /**
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     *
     * @return
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     *
     * @param taskTitle
     * @param tasks
     */
    public static void addNewTodoTask(String taskTitle, ArrayList<Task> tasks) {
        ToDos newTodoTask = new ToDos(taskTitle, false);
        tasks.add(newTodoTask);
        Ui.addTodoTaskMsg(tasks);
    }

    /**
     *
     * @param taskTitle
     * @param deadlineTime
     * @param tasks
     */
    public static void addNewDeadlineTask(String taskTitle, String deadlineTime, ArrayList<Task> tasks) {
        Deadlines newDeadline = new Deadlines(taskTitle, deadlineTime, false);
        tasks.add(newDeadline);
        Ui.addDeadlineTaskMsg(tasks);
    }

    /**
     *
     * @param taskTitle
     * @param eventTime
     * @param tasks
     */
    public static void addNewEventTask(String taskTitle, String eventTime, ArrayList<Task> tasks) {
        Events newEvent = new Events(taskTitle, eventTime, false);
        tasks.add(newEvent);
        Ui.addEventTaskMsg(tasks);
    }

    /**
     *
     * @param index
     * @param tasks
     */
    public static void deleteTask(int index, ArrayList<Task> tasks) {
        Task taskToDelete = tasks.get(index - 1);
        tasks.remove(index - 1);
        int newSizeOfTasks = tasks.size();
        Ui.deleteTaskMsg(index, newSizeOfTasks, taskToDelete);
    }

    /**
     *
     * @param index
     * @param tasks
     */
    public static void doneTask(int index, ArrayList<Task> tasks) {
        tasks.get(index - 1).markAsDone();
        Ui.doneTaskMsg(index, tasks);
    }
}
