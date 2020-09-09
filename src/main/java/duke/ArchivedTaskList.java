package duke;

import javax.naming.PartialResultException;
import java.util.ArrayList;

public class ArchivedTaskList {
    private ArrayList<Task> archivedTasks;

    public ArchivedTaskList(ArrayList<Task> archivedTasks) {
        this.archivedTasks = archivedTasks;
    }

    public static void archiveAllTasks(ArrayList<Task> taskList, ArrayList<Task> archivedTaskList) {
        for (Task task : taskList) {
            ArchivedTaskList.addArchivedTask(task, archivedTaskList);
        }
        taskList.clear();
    }

    public ArrayList<Task> getArchivedTaskList() {
        return this.archivedTasks;
    }

    public int getTaskListSize() {
        return this.archivedTasks.size();
    }

    public static void addArchivedTask(Task taskToArchive, ArrayList<Task> archivedTasks) {
        archivedTasks.add(taskToArchive);
    }
}
