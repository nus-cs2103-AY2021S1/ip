package chatbot.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

import chatbot.common.Message;
import chatbot.exception.ChatbotException;


/**
 * A class containing a list of tasks, with methods for adding new tasks, deleting and returning
 * tasks due on a specific date.
 */

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the current list of tasks.
     * @return list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a task at the specified index.
     * @param index index to locate task
     * @return task at the given index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the current total number of tasks.
     * @return total number of tasks
     */
    public int count() {
        return this.tasks.size();
    }

    /**
     * Adds a new task to the list of tasks.
     * @param task new task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at a specified index on the list.
     * @param index index to locate task
     * @return task that is removed
     * @throws ChatbotException if given index is out-of-bounds
     */
    public Task removeTask(int index) throws ChatbotException {
        Task removed;

        try {
            removed = this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException(Message.MESSAGE_ITEM_DO_NOT_EXIST);
        }

        return removed;
    }

    /**
     * Marks a task on the list as done.
     * @param index index to locate the task
     * @return task to be marked as done
     * @throws ChatbotException if given index is out-of-bounds
     */
    public Task markAsDone(int index) throws ChatbotException {
        Task taskDone;

        try {
            taskDone = getTask(index).markDone();
            this.tasks.set(index, taskDone);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException(Message.MESSAGE_ITEM_DO_NOT_EXIST);
        }

        return taskDone;
    }

    /**
     * Returns a list of tasks that fulfills the given predicate.
     * @param pred given predicate
     * @return list of tasks
     */
    public ArrayList<Task> retrieveTasksByPred(Predicate<Task> pred) {

        Iterator<Task> iter = this.tasks.iterator();
        ArrayList<Task> tasks = new ArrayList<>();

        while (iter.hasNext()) {
            Task task = iter.next();
            if (pred.test(task)) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Retrieve tasks by order, specified by a comparator.
     * @param comp comparator to decide order
     * @return list of tasks in the specified order
     */
    public ArrayList<Task> retrieveTasksByOrder(Comparator<Task> comp) {
        ArrayList<Task> copyOfTasks = new ArrayList<>(tasks);
        copyOfTasks.sort(comp);

        return copyOfTasks;
    }
}
