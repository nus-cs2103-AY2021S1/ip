package util;

import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents the list containing tasks.
 */
public class TaskList {
    /** List containing the different tasks. */
    private final List<Task> lst;

    /**
     * Creates a new empty TaskList.
     */
    public TaskList() {
        this.lst = new ArrayList<>();
    }

    /**
     * Creates a new TaskList of tasks based on an input list of strings. The constructor parses these strings to create tasks before adding them to the TaskList.
     * 
     * @param inputLst List containing tasks represented as strings.
     */
    public TaskList(List<String> inputLst) {
        String DONE = "1";
        this.lst = new ArrayList<>();
        for (String line : inputLst) {
            String[] splitInput = line.split(" \\| ");
            TaskType taskType = TaskType.valueOf(splitInput[0]);
            switch (taskType) {
            case TODO:
                this.add(new ToDoTask(splitInput[2], splitInput[1].equals(DONE)));
                break;
            case DEADLINE:
                this.add(new DeadlineTask(splitInput[2], splitInput[1].equals(DONE), splitInput[3]));
                break;
            case EVENT:
                this.add(new EventTask(splitInput[2], splitInput[1].equals(DONE), splitInput[3]));
                break;
            }

        }
    }

    /**
     * Adds a task to the TaskList.
     */
    public void add(Task task) {
        lst.add(task);
    }

    /**
     * Removes a task from the TaskList.
     */
    public void remove(Task task) {
        lst.remove(task);
    }

    /**
     * Return number of tasks in the list.
     * 
     * @return  Number of tasks in the list.
     */
    public int size() {
        return lst.size();
    }

    /**
     * Returns a task based on its index in the list.
     * 
     * @return Task based on its index in the list.
     */
    public Task get(int index) {
        return lst.get(index);
    }

    /** Constants representing the different tasks. */
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
}
