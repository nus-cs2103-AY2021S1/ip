package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;


/**
 * The TaskList class represents the list containing tasks.
 */
public class TaskList {
    /**
     * List containing the different tasks.
     */
    private final List<Task> lst;

    /**
     * Creates a new empty TaskList.
     */
    public TaskList() {
        this.lst = new ArrayList<>();
    }

    /**
     * Creates a new TaskList of tasks based on an input list of strings.
     * The constructor parses these strings to create tasks before adding them to the TaskList.
     *
     * @param inputLst List containing tasks represented as strings.
     */
    public TaskList(List<String> inputLst) {
        String done = "1";
        this.lst = new ArrayList<>();
        for (String line : inputLst) {
            String[] splitInput = line.split(" \\| ");
            TaskType taskType = TaskType.valueOf(splitInput[0]);
            switch (taskType) {
            case TODO:
                this.add(new ToDoTask(splitInput[2], splitInput[1].equals(done)));
                break;
            case DEADLINE:
                this.add(new DeadlineTask(splitInput[2], splitInput[1].equals(done), splitInput[3]));
                break;
            case EVENT:
                this.add(new EventTask(splitInput[2], splitInput[1].equals(done), splitInput[3]));
                break;
            default:
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
     * Returns number of tasks in the list.
     *
     * @return Number of tasks in the list.
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

    /**
     * Returns a list of tasks based on the query.
     *
     * @param query String representing the search query.
     * @return Returns a list of tasks based on the query.
     */
    public List<Task> filter(String query) {
        return lst.stream().filter(task -> {
            String taskDesc = task.getDescription();
            String[] wordsInTask = taskDesc.split(" ");
            return Arrays.asList(wordsInTask).contains(query);
        }).collect(Collectors.toList());
    }

    /**
     * Constants representing the different tasks.
     */
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
}
