import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    public TaskList(List<Task> inputTasks) {
        for (Task task : inputTasks) {
            this.taskList.add(task);
        }
    }

    public TaskList() {

    }

    /**
     * Returns the number of tasks in the task list.
     * @return Number of tasks in the task list.
     */
    Integer countTotalTasks() {
        return this.taskList.size();
    }

    /**
     * Returns the task at the specified index in the task list.
     * @param index The index of the task of interest in the task list.
     * @return The Task instance found at the specified index in the task list.
     * @throws IndexOutOfBoundsException If the index specified is outside the range of the task list.
     */
    Task getTask(int index) throws IndexOutOfBoundsException{
        return this.taskList.get(index);
    }

    /**
     * Adds a Task instance to the task list.
     * @param task Task instance to be added to the task list.
     * @throws DukeException If the Task instance does not have a name.
     */
    void addTask(Task task) throws DukeException {
        if (task.getName().length() == 0) {
            throw new DukeException(task.returnMissingNameError());
        }
        this.taskList.add(task);
        String numOfTasks = this.taskList.size() == 1 ? "1 task" : this.taskList.size() + " tasks";
        String message = "Got it. I've added the following task: " + Ui.NEW_LINE
                + Ui.PADDING + "  " + task.toString() + Ui.NEW_LINE
                + Ui.PADDING + "Now you have "  + numOfTasks + " in total.";
        Ui.print(message);
    }

    /**
     * Display the list of tasks in the task list.
     */
    void displayTasks() {
        if (this.taskList.size() == 0) {
            Ui.print("Your list is empty, try adding some tasks to it");
            return;
        }
        StringBuilder output = new StringBuilder("You have the following tasks in your list:" + Ui.NEW_LINE);
        int counter = 1;
        for (Task task: this.taskList) {
            output.append(Ui.PADDING).append(counter).append(". ").append(task.toString()).append(Ui.NEW_LINE);
            counter++;
        }
        output = new StringBuilder(output.substring(0, output.length() - 1));
        Ui.print(output.toString());
    }

    /**
     * Display the list of tasks matching the search term specified by the user.
     * @param searchTerm The search term used to find tasks in the task list.
     */
    void displayTasksFound(String searchTerm) {
        int counter = 0;
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:" + Ui.NEW_LINE);
        for (Task task: this.taskList) {
            if (task.getName().contains(searchTerm)) {
                counter++;
                output.append(Ui.PADDING).append(counter).append(". ").append(task.toString()).append(Ui.NEW_LINE);
            }
        }
        if (counter == 0) {
            Ui.print("No matching tasks found. Try another search term");
            return;
        }
        output = new StringBuilder(output.substring(0, output.length() - 1));
        Ui.print(output.toString());
    }

    /**
     * Sets the task at the specified index in the task list to done.
     * @param index The index of the task in the task list.
     * @throws DukeException If the index specified is outside the range of the task list.
     */
    void setTaskDone(int index) throws DukeException {
        if (index <= 0 || index > this.taskList.size()) {
            throw new DukeException("Invalid index, cannot find task.");
        }
        this.taskList.get(index-1).setDoneness(true);
        String message = "Nice job! I'll mark that as done:" + Ui.NEW_LINE + Ui.PADDING
                + "  " + this.taskList.get(index-1).toString();
        Ui.print(message);
    }

    /**
     * Deletes the task at the specified index in the task list.
     * @param index The index of the task in the task list.
     * @throws DukeException If the index specified is outside the range of the task list.
     */
    void deleteTask(int index) throws DukeException {
        if (index <= 0 || index > this.taskList.size()) {
            throw new DukeException("Invalid index, cannot find task.");
        }
        Task task = this.taskList.get(index-1);
        this.taskList.remove(index-1);
        String numOfTasks = this.taskList.size() == 1 ? "1 task" : this.taskList.size() + " tasks";
        String message = "Noted. The following task has been removed:"
                + Ui.NEW_LINE + Ui.PADDING + "  " + task.toString() + Ui.NEW_LINE
                + Ui.PADDING + "Now you have "  + numOfTasks + " left.";
        Ui.print(message);
    }

    /**
     * Deletes all tasks in the task list.
     * @throws DukeException If the task list is already empty.
     */
    void deleteAllTasks() throws DukeException {
        if (this.taskList.size() == 0) {
            throw new DukeException("Your list is already empty.");
        }
        this.taskList.clear();
        String message = "Noted. All tasks have been removed.";
        Ui.print(message);
    }
}
