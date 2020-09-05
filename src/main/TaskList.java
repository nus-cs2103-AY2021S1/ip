import java.util.ArrayList;

/**
 * Represents a task list that contains an array list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


    /**
     * Returns the task list as an array list.
     *
     * @return Task list.
     */
    public ArrayList<Task> getTasks(){
        return taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param newTask New task.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
        String addTaskMessage = "Got it. I've added this task:\n" +
                newTask.toString() + "\n" +
                "Now you have " + taskList.size() + (taskList.size() > 1 ? " tasks " : " task ") + "in the list.\n";
        new Ui(addTaskMessage).printMessageWithBorders();
    }

    /**
     * Deletes a task from the task list based on the index.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        Task task = taskList.remove(index);
        String deleteTaskMessage = "Noted. I've removed this task:\n" +
                task.toString() + "\n";
        new Ui(deleteTaskMessage).printMessageWithBorders();
    }

    /**
     * Marks a task from the task list as done based on the index.
     *
     * @param index Index of the task to be marked as done.
     */
    public void markDone(int index) {
        Task task = taskList.get(index);
        task.makeDone();
        String markDoneMessage = "Nice! I've marked this task as done:\n" +
                "[\u2713] " + task.getDescription() + "\n";
        new Ui(markDoneMessage).printMessageWithBorders();
    }
}
