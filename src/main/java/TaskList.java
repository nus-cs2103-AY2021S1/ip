import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Encapsulates a taskList object, contains a list of tasks and supports operations that adds, deletes or
 * finds tasks, or marks tasks as done.
 */
public class TaskList {

    /**
     * Represents the list of tasks.
     */
    private final ArrayList<Task> myTaskList;
    
    TaskList() {
        myTaskList = new ArrayList<>();
    }

    TaskList(ArrayList<Task> taskList) {
        this.myTaskList = taskList;
    }

    ArrayList<Task> getMyTaskList() {
        return myTaskList;
    }

    int getListLength() {
        return myTaskList.size();
    }

    public void addNewTask(Task newTask) {
        myTaskList.add(newTask);
    }

    /**
     * Updates the tasks with the given indexes and function.
     * 
     * @param taskIndexes indexes of tasks to be updated.
     * @param updateTask function used to update a task, either mark as done or delete.
     * @return an arrayList of the updated tasks.
     * @throws IndexOutOfBoundsException if task indexes given are out of range.
     */
    ArrayList<Task> updateTasks(ArrayList<Integer> taskIndexes,
                                Consumer<Task> updateTask) throws IndexOutOfBoundsException {
        
        ArrayList<Task> updatedTasks = new ArrayList<>();
        for (int taskIndex : taskIndexes) {
            Task current = myTaskList.get(taskIndex - 1);
            updatedTasks.add(current);
        }
        for (Task task : updatedTasks) {
            updateTask.accept(task);
        }
        return updatedTasks;
    }

    /**
     * Marks one or more tasks as done. Throws an exception if any task index is invalid.
     *
     * @param taskIndexes indexes of tasks to be marked as done.
     * @return the done tasks.
     * @throws DukeException if an index is invalid.
     */
    public ArrayList<Task> doneTasks(ArrayList<Integer> taskIndexes) throws DukeException {
        try {
            Consumer<Task> doneTask = task -> task.markAsDone(); 
            return updateTasks(taskIndexes, doneTask);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("done");
        }
    }

    /**
     * Deletes one or more tasks from the list. Throws an exception if any task index is invalid.
     *
     * @param taskIndexes indexes of tasks to be deleted.
     * @return the deleted tasks.
     * @throws DukeException if an index is invalid.
     */
    public ArrayList<Task> deleteTasks(ArrayList<Integer> taskIndexes) throws DukeException {
        try {
            Consumer<Task> deleteTask = task -> myTaskList.remove(task);
            return updateTasks(taskIndexes, deleteTask);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("delete");
        }
    }

    /**
     * Finds all the tasks with description that contains the input.
     *
     * @param input the required keyword to be searched for.
     * @return a string describing the list of tasks that contains the input in their description.
     */

    String findTask(String input) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : myTaskList) {
            if (t.description.contains(input)) {
                tasks.add(t);
            }
        }
        return new TaskList(tasks).toString();
    }

    /**
     * Returns a numbered list of all the tasks in the task list, with each task on a new line. 
     * @return a numbered list of tasks.
     */
    @Override
    public String toString() {
        if (myTaskList.isEmpty()) {
            return "";
        } else {
            StringBuilder taskListString = new StringBuilder();
            for (int i = 1; i < myTaskList.size() + 1; i++) {
                taskListString
                        .append("     ")
                        .append(i)
                        .append(".")
                        .append(myTaskList.get(i - 1))
                        .append("\n");
            }
            taskListString.delete(taskListString.length() - 1, taskListString.length());
            return taskListString.toString();
        }
    }
}
