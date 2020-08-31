import java.util.ArrayList;

public class TaskList {

    /** ArrayList used store tasks **/
    private ArrayList<Task> taskList;
    /** boolean value to check if there is any more updates to the tasks by the user **/
    private boolean isUpdating = true;

    /**
     *Class constructor
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Set the indexed task as completed.
     *
     * @param index index The index of the task in the taskList;
     * @throws DukeException  If the index is not within the range of tasks.
     */

    public void doneTask(int index) throws DukeException {
        if (index < 0 || index > taskList.size() - 1) {
            throw new DukeException("please give a correct task index");
        }
        Task doneTask = taskList.get(index);
        doneTask.complete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("  %s", doneTask.toString()));
    }

    /**
     * List and prints all the task in taskList
     *
     */

    public void listTask() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, taskList.get(i).toString()));
        }
    }

    /**
     * Add task into the taskList
     *
     * @param newTask The task to be added into taskList
     * @param print If true, prints out the details of the task being added into the list
     */
    public void addTask(Task newTask, Boolean print) {
        taskList.add(newTask);
        if (print) {
            newTask.printAddTask();
            printNumberOfTask(taskList.size());
        }
    }

    /**
     * Find and print out task that matches to the description
     *
     * @param description of the task
     */
    public void findTask(String description) {
        ArrayList<Task> taskMatchingDescription = new ArrayList<>();

        for (Task task : taskList) {
            if (task.fitsTask(description)) {
                taskMatchingDescription.add(task);
            }
        }

        for (int i = 0; i < taskMatchingDescription.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, taskMatchingDescription.get(i).toString()));
        }
    }
    /**
     * return the size of the taskList
     *
     * @return size of the taskList
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * set isUpdating to false
     *
     */
    public void setTaskListNotUpdating() {
        this.isUpdating = false;
    }

    /**
     * get the corresponding task from taskList
     *
     * @param index The index of the task in the taskList
     * @return The task with the corresponding index from the taskList
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * get the boolean value of whether the task is being updated
     *
     * @return the boolean isUpdating
     */
    public boolean isUpdating() {
        return isUpdating;
    }

    /**
     * delete the task with the corresponding index in taskList
     *
     * @param index The index of the task in the taskList
     * @throws DukeException  If the index is not within the range of tasks.
     */

    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index > taskList.size() - 1) {
            throw new DukeException("please give a correct task index");
        }
        taskList.get(index).printDeleteTask();
        taskList.remove(index);
        printNumberOfTask(taskList.size());
    }

    /**
     * prints the update for the number of tasks in the list
     *
     * @param i The number of tasks in the list
     */
    static void printNumberOfTask(int i) {
        System.out.println(String.format("Now you have %d tasks in the list.", i));
    }
}
