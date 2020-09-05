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

    public String listTask() {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            outputString.append(String.format("%d. %s\n", i + 1, taskList.get(i).toString()));
        }
        return outputString.toString();
    }

    /**
     * Add task into the taskList
     *
     * @param newTask The task to be added into taskList
     * @param print If true, prints out the details of the task being added into the list
     */
    public String addTask(Task newTask, Boolean print) {
        taskList.add(newTask);
        String outputString = "";
        if (print) {
            outputString += newTask.printAddTask();
            outputString += printNumberOfTask(taskList.size());
        }

        return outputString;
    }

    /**
     * Find and print out task that matches to the description
     *
     * @param description of the task
     */
    public String findTask(String description) {
        ArrayList<Task> taskMatchingDescription = new ArrayList<>();
        StringBuilder outputString = new StringBuilder();

        for (Task task : taskList) {
            if (task.fitsTask(description)) {
                taskMatchingDescription.add(task);
            }
        }

        for (int i = 0; i < taskMatchingDescription.size(); i++) {
            outputString.append(String.format("%d. %s\n", i + 1, taskMatchingDescription.get(i).toString()));
        }

        return outputString.toString();
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

    public String deleteTask(int index) throws DukeException {
        String outputString = "";
        if (index < 0 || index > taskList.size() - 1) {
            throw new DukeException("please give a correct task index");
        }

        outputString += taskList.get(index).printDeleteTask() + "\n";
        taskList.remove(index);
        outputString += printNumberOfTask(taskList.size());
        return outputString;
    }

    /**
     * prints the update for the number of tasks in the list
     *
     * @param i The number of tasks in the list
     */
    static String printNumberOfTask(int i) {
        return String.format("Now you have %d tasks in the list.", i);
    }
}
