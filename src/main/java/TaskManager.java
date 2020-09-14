import java.util.ArrayList;

/**
 * <p>The TaskManager class stores a task list and manages commands relevant
 * to the task list: add, remove, mark as done, get,....</p>
 */
public class TaskManager {
    private ArrayList<Task> taskList;

    /**
     * Creates a TaskManager object that stores a taskList.
     *
     * @param taskList The task list stored.
     */
    public TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task into the current task list.
     *
     * @param newTask The Task to add.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Removes a task of specified index.
     *
     * @param taskIndex The int that represents index of the task in the list.
     */
    public void removeTask(int taskIndex) throws DukeException {
        try {
            taskList.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundTaskException();
        }
    }

    /**
     * Marks a task of specified index as done.
     *
     * @param index The int that represents index of the task in the list.
     */
    public void markTaskAsDone(int index) throws DukeException {
        try {
            taskList.get(index).setTaskAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundTaskException();
        }
    }

    /**
     * Gets a task of specified index.
     *
     * @param index The int that represents index of the task in the list.
     * @return The Task that has the specified index.
     */
    public Task getTask(int index) throws DukeException {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundTaskException();
        }
    }

    /**
     * Gets the total number of tasks in the task list.
     *
     * @return The int that represents total number of tasks.
     */
    public int getTotalNoOfTasks() {
        return this.taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns all the tasks in the task list that has a specified keyword.
     *
     * @param keyWord A String that represents the keyword.
     * @return A list of tasks that have the specified keyword.
     */
    public ArrayList<Task> findTaskThatHasKeyword(String keyWord) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getTaskDescription().contains(keyWord)) {
                res.add(task);
            }
        }
        return res;
    }

    /**
     * Returns all the tasks in the task list that has a specified tag.
     *
     * @param tag The Tag to find.
     * @return A list of tasks that have the specified tag.
     */
    public ArrayList<Task> findTaskWithTag(Tag tag) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getTagList() != null) {
                if (task.getTagList().getTagList().contains(tag)) {
                    res.add(task);
                } else {
                    System.out.println(tag);
                }
            }
        }
        return res;
    }

    /**
     * Converts a given task list into a formatted string representation.
     *
     * @param givenTaskList A list of task to be converted.
     * @return The string representation of the task list.
     */
    public String convertTaskListToString(ArrayList<Task> givenTaskList) {
        String result = "";
        for (int i = 0; i < givenTaskList.size(); i++) {
            String index = (i + 1) + ". ";
            // remove the empty line created in the last task
            if (i == givenTaskList.size() - 1) {
                result = result + index + givenTaskList.get(i).toString();
                break;
            }
            result = result + index + givenTaskList.get(i).toString() + "\n";
        }
        return result;
    }
}
