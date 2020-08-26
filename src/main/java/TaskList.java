import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks stored in the object.
     *
     * @return the current list of tasks
     */
    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the list stored in the object.
     *
     * @return the size of the current list of tasks.
     */
    public int getLength() {
        return this.tasks.size();
    }


    Task createTask(String taskType, String desc) throws MissingDeadlineException{
        if (taskType.equals("todo")) {
            return new Todo(desc);
        } else if (taskType.equals("deadline")) {
            return new Deadline(desc);
        } else {
            return new Event(desc);
        }
    }

    /**
     * Adds a task with a description and if applicable, a deadline to the task list
     * and returns it.
     *
     * @param taskType the type of task to be added
     * @param desc the description of task to be added
     * @return the task added to the task list
     * @throws MissingDeadlineException
     */
    public Task addTask(String taskType, String desc) throws MissingDeadlineException {
        Task task = createTask(taskType, desc);
        this.tasks.add(task);
        return task;
    }

    String formatTask(int num) {
        String lineBreak = num != this.tasks.size() - 1 ? "\n  " : "";
        return (num + 1) + "." + this.tasks.get(num) + lineBreak;
    }

    /**
     * Returns the tasks in a numbered order as a String that can be printed out.
     *
     * @return the String containing the tasks in a numbered order
     */
    public String formattedList() {
        String list = "";

        for (int i = 0; i < this.tasks.size(); i++) {
            list += formatTask(i);
        }

        return list;
    }

    /**
     * Marks the task at the given index as completed and returns it.
     *
     * @param num the index of the task in the list
     * @return the completed task
     * @throws MissingTaskException
     */
    public Task completeTask(int num) throws MissingTaskException {
        if (num > 0 && num <= tasks.size()) {
            Task task = tasks.get(num - 1);
            task.completeTask();
            return task;
        } else {
            throw new MissingTaskException(num);
        }
    }

    /**
     * Deletes the task at the given index and returns it.
     *
     * @param num the index of the task in the list
     * @return the deleted task
     * @throws MissingTaskException
     */
    public Task deleteTask(int num) throws MissingTaskException {
        if (num > 0 && num <= tasks.size()) {
            Task task = tasks.remove(num - 1);
            return task;
        } else {
            throw new MissingTaskException(num);
        }
    }
}
