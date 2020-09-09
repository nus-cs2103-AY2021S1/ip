package duke;

import java.util.ArrayList;

public class ArchivedTaskList {
    private ArrayList<Task> archivedTasks;

    public ArchivedTaskList(ArrayList<Task> archivedTasks) {
        this.archivedTasks = archivedTasks;
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
