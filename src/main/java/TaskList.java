import java.util.List;

/**
 * A class that handles any changes that occur within the
 * list of tasks.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class TaskList {

    List<Task> taskList;

    TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public int noOfTasks() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void setTask(int index, Task task) {
        this.taskList.set(index, task);
    }

    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Deletes a task at the given index from the taskList.
     *
     * @param current The current taskList.
     * @param input A valid string array is of length 2, index 1 being the task number to delete.
     * @param ui The UI object used.
     * @throws DeleteFailureException  If array size is not 2 or index is out of bounds.
     */
    public void deleteTask(TaskList current, String[] input, Ui ui) throws DeleteFailureException {
        try {
            if (input.length == 2) {
                TaskList updated = current;
                int taskNumber = Integer.parseInt(input[1]);
                Task removedTask = current.getTask(taskNumber - 1);
                current.removeTask(taskNumber - 1);
                ui.showSuccessfulDelete(removedTask, updated.noOfTasks());
            } else {
                throw new DeleteFailureException("Duke says: Please try again with a " +
                        "valid format.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteFailureException("Duke says: Please try again with a valid number.");
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Adds a task at the given index from the taskList.
     *
     * @param data The processed data, index 0 being description, 1 being date and 2 being time
     *     if applicable.
     * @param keyWord The type of task.
     * @param tasks The current list of tasks.
     * @param ui The UI object used.
     */
    public void addTask(String[] data, String keyWord, TaskList tasks, Ui ui) {
        if (keyWord.equals("todo")) {
            ToDo toDo = new ToDo(data[0]);
            tasks.addTask(toDo);
            ui.showTasksAdded(toDo, tasks.noOfTasks());
        } else if (keyWord.equals("deadline")) {
            Deadline deadline = new Deadline(data[0], data[1], data[2]);
            tasks.addTask(deadline);
            ui.showTasksAdded(deadline, tasks.noOfTasks());
        } else if (keyWord.equals("event")) {
            Event event = new Event(data[0], data[1], data[2]);
            tasks.addTask(event);
            ui.showTasksAdded(event, tasks.noOfTasks());
        }
    }

    /**
     * Denotes a task to be done at the given index in the taskList.
     *
     * @param splitInput A valid string array is of length 2, index 1 being the task number to
     *     denote as done.
     * @param tasks The current list of tasks.
     * @param ui The UI object used.
     */
    public void doneTask(String[] splitInput, TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(splitInput[1]);
            Task doneTask = tasks.getTask(taskNumber - 1);
            doneTask.markDone();
            tasks.setTask(taskNumber - 1, doneTask);
            ui.showTaskIsDone(doneTask);
        } catch (Exception ex) {
            ui.showInvalidTaskNumber();
        }
    }
}
