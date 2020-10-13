package duke;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        assert taskList != null : "List of tasks should not be null.";
        for (int i = 1; i <= taskList.size(); i++) {
            res.append(i)
                    .append(". ")
                    .append(taskList.get(i - 1));
            if (i != taskList.size()) {
                res.append("\n");
            }
        }
        return res.toString();
    }

    /**
     * Returns information about the {@code Task} objects in its list in a
     * String format for saving.
     * @return String representation of list of tasks for saving.
     */
    public String formatForSave() {
        StringBuilder res = new StringBuilder();
        for (Task t : taskList) {
            res.append(t.getSaveFormat());
            res.append("\n");
        }
        return res.toString();
    }

    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    public Task getTask(int taskNumber) throws DukeException {
        try {
            return taskList.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeException.ExceptionType.REQUESTED_NONEXISTENT_ITEM);
        }
    }

    /**
     * Returns a new {@code TaskList} object containing a subset of the tasks whose
     * descriptions contain the phrase specified.
     * @param phrase String phrase to search the list of tasks against.
     * @return New {@code TaskList} object containing a subset of the tasks.
     */
    public TaskList findTasks(String phrase) {
        List<Task> searchResult = new ArrayList<>();
        for (Task task: taskList) {
            if (task.getDescription().contains(phrase)) {
                searchResult.add(task);
            }
        }
        return new TaskList(searchResult);
    }

    public int getCount() {
        return taskList.size();
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }
}
