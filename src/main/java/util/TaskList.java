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
     * Creates a new TaskList of tasks based on an input list of strings obtained from Serina.txt.
     * The constructor parses these strings to create tasks before adding them to the TaskList.
     *
     * @param inputLst List containing tasks represented as strings. (Param can have 0 arguments)
     */
    @SafeVarargs
    public TaskList(List<String> ...inputLst) {
        String done = "1";
        this.lst = new ArrayList<>();
        if (inputLst.length >= 1) {
            for (String line : inputLst[0]) {
                String[] splitInput = line.split(" \\| ");
                TaskType taskType = TaskType.valueOf(splitInput[0]);
                String taskDescription = splitInput[2];
                String taskDate;
                boolean hasTag;
                Tag tag;
                boolean isDone = splitInput[1].equals(done);
                switch (taskType) {
                case TODO:
                    hasTag = splitInput.length == 4;
                    if (hasTag) {
                        tag = new Tag(splitInput[3]);
                        this.add(new ToDoTask(taskDescription, isDone).setTag(tag));
                    } else {
                        this.add(new ToDoTask(taskDescription, isDone));
                    }
                    break;
                case DEADLINE:
                    hasTag = splitInput.length == 5;
                    taskDate = splitInput[3];
                    if (hasTag) {
                        tag = new Tag(splitInput[4]);
                        this.add(new DeadlineTask(taskDescription, isDone, taskDate).setTag(tag));
                    } else {
                        this.add(new DeadlineTask(taskDescription, isDone, taskDate));
                    }
                    break;
                case EVENT:
                    hasTag = splitInput.length == 5;
                    taskDate = splitInput[3];
                    if (hasTag) {
                        tag = new Tag(splitInput[4]);
                        this.add(new EventTask(taskDescription, isDone, taskDate).setTag(tag));
                    } else {
                        this.add(new EventTask(taskDescription, isDone, taskDate));
                    }
                    break;
                default:
                    assert false : taskType;
                }
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
     * Returns a index of a task in the list.
     *
     * @return index of a task in the list.
     */
    public int indexOf(Task task) {
        return lst.indexOf(task);
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
            // check if any string in the task description matches the query string
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
