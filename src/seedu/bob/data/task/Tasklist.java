package seedu.bob.data.task;

import seedu.bob.exceptions.BobInvalidDateAndTimeException;
import java.io.FileNotFoundException;
import java.io.IOException;

import seedu.bob.storage.Storage;

import java.util.ArrayList;

/**
 * Represents the task list in Bob.
 */
public class Tasklist {
    private final ArrayList<Task> list;

    public Tasklist(Storage storage) throws FileNotFoundException, BobInvalidDateAndTimeException {
        this.list = storage.getList();
    }

    public Tasklist() {
        list = new ArrayList<>();
    }

    public void updateData(Storage storage) throws IOException {
        storage.updateFile(list);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public int getListSize() {
        return this.list.size();
    }

    public Task markTaskDone(int taskNo) {
        int index = taskNo - 1;
        Task task = this.list.get(index).markDone();
        this.list.set(index, task);
        return task;
    }

    public Task deleteTask(int taskNo) {
        int index = taskNo - 1;
        Task task = list.get(index);
        this.list.remove(index);
        return task;
    }

    private int getNumOfDoneTask() {
        int doneTask = 0;
        for (Task task : list) {
            if (task.checkIsDone()) {
                doneTask++;
            }
        }
        return doneTask;
    }

    private String convertList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getListSize(); i++) {
            int taskNo = i + 1;
            output.append(taskNo).append(". ").append(this.list.get(i)).append("\n");
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return getListSize() == 0
                ? "You currently have no tasks.\n"
                : getNumOfDoneTask() == list.size()
                ? "Wow congrats, you finished all your tasks.\n" + convertList()
                : "You have " + (list.size() - getNumOfDoneTask()) + " unfinished tasks.\n" + convertList();
    }
}
