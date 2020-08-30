package botbot;

import botbot.tasks.Task;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Represents the task list in the chatbot.
 */
public class TaskList extends LinkedList<Task> {
    /**
     * Creates a task list.
     */
    public TaskList() {
        super();
    }

    /**
     * Creates a task list.
     *
     * @param tasks Tasks in task list.
     */
    public TaskList(Collection<Task> tasks) {
        super(tasks);
    }

    /**
     * Deletes a task in the task list.
     * 
     * @param id ID of task to be deleted.
     */
    public void delete(int id) {
        remove(id);
    }
    
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size(); i++) {
            result += String.format("      %s. %s\n", i + 1, this.get(i));
        }
        return result;
    }
}
