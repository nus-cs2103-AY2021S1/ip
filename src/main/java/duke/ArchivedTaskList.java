package duke;

import java.util.ArrayList;

/**
 * Contains the oll archived tasks, and it provide operations to modify
 * archived task in the archived task list.
 */
public class ArchivedTaskList {
    private ArrayList<Task> archivedTasks;

    /**
     * ArchivedTaskList Constructor.
     *
     * @param archivedTasks ArrayList to store all archived tasks.
     */
    public ArchivedTaskList(ArrayList<Task> archivedTasks) {
        this.archivedTasks = archivedTasks;
    }

    /**
     * Archive all tasks in the TaskList. Save them into ArchivedTaskList.
     *
     * @param taskList         All active tasks.
     * @param archivedTaskList All archived tasks.
     */
    public static void archiveAllTasks(ArrayList<Task> taskList, ArrayList<Task> archivedTaskList) {
        for (Task task : taskList) {
            ArchivedTaskList.addArchivedTask(task, archivedTaskList);
        }
        taskList.clear();
    }

    /**
     * Gets all archived tasks in the archived task list.
     *
     * @return An ArrayList of all achived tasks.
     */
    public ArrayList<Task> getArchivedTaskList() {
        return this.archivedTasks;
    }

    /**
     * Gets how many tasks in the archived task list.
     *
     * @return Number of archived takes in the archived task list.
     */
    public int getTaskListSize() {
        return this.archivedTasks.size();
    }

    /**
     * Add a new archived task.
     *
     * @param taskToArchive The task which will be archived.
     * @param archivedTasks The list contains all archived tasks.
     */
    public static void addArchivedTask(Task taskToArchive, ArrayList<Task> archivedTasks) {
        archivedTasks.add(taskToArchive);
    }
}
