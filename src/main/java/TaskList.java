import java.util.ArrayList;

public class TaskList {

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
     * Marks a task as done. Throws an exception if the task index is invalid.
     *
     * @param taskIndex index of task to be marked as done.
     * @return the done task.
     * @throws DukeException if index is invalid.
     */
    public Task doneTask(int taskIndex) throws DukeException {
        try {
            Task temp = myTaskList.get(taskIndex - 1);
            temp.markAsDone();
            return temp;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("done");
        }
    }

    /**
     * Deletes a task from the list. Throws an exception if the task index is invalid.
     *
     * @param taskIndex index of task to be deleted.
     * @return the deleted task.
     * @throws DukeException if index is invalid.
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        try {
            Task temp = myTaskList.get(taskIndex - 1);
            myTaskList.remove(temp);
            return temp;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("delete");
        }
    }
    
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

    @Override
    public String toString() {
        if (myTaskList.isEmpty()) {
            return "";
        } else {
            StringBuilder taskListString = new StringBuilder();
            for (int i = 1; i < myTaskList.size() + 1; i++) {
                taskListString.append("     ").append(i).append(".").append(myTaskList.get(i - 1)).append("\n");
            }
            taskListString.delete(taskListString.length() - 1, taskListString.length());
            return taskListString.toString();
        }
    }
}
