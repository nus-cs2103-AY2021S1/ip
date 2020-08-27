package duke.logic;

import duke.CommonString;
import duke.exception.InvalidInstructionException;
import duke.task.DukeTask;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<DukeTask> taskList;

    public TaskList(ArrayList<DukeTask> taskList) {
        this.taskList = taskList;
    }

    public void addToList(DukeTask dukeTask) {
        taskList.add(dukeTask);
    }

    public DukeTask deleteFromList(int index) {
        return taskList.remove(index);
    }

    public void markDone(int index) {
        taskList.get(index).markAsDone();
    }

    public int getSize() {
        return taskList.size();
    }

    public ArrayList<DukeTask> getTaskList() {
        return taskList;
    }
}
