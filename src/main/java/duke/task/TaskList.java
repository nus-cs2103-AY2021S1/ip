package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.storage.Storage;

/**
 * Represent the list of task for the Duke program.
 */
public class TaskList {
    private List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public int size() {
        return this.list.size();
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Update the storage.
     * @param storage storage to be updated
     */
    public void updateStorage(Storage storage) {
        storage.update(list);
    }

    /**
     * Add the task into the TaskList and update the storage at the same time.
     * @param task task to be added
     * @param storage storage to be updated
     */
    public void add(Task task, Storage storage) {
        this.list.add(task);
        this.updateStorage(storage);
    }

    /**
     * Return task that is removed and update the storage at the same time.
     * @param index index of element
     * @param storage storage to be updated
     * @return task
     */
    public Task remove(int index, Storage storage) {
        assert index < list.size() || index > 0 : "index out of bound";

        Task task = this.list.remove(index);
        this.updateStorage(storage);

        return task;
    }

    /**
     * Return TaskList containing the search results.
     * @param searchString the string to be searched
     * @return TaskList
     */
    public TaskList find(String searchString) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task t : this.list) {
            if (t.getDescription().contains(searchString)) {
                matchingTasks.add(t);
            }
        }

        return new TaskList(matchingTasks);
    }
}
