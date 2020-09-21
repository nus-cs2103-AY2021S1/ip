import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList implements Serializable {

    // Attributes
    private final List<Task> taskList;

    // Constructor

    /**
     * Creates a new TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> listOfTasks) {
        this.taskList = listOfTasks;
    }

    // Methods

    /**
     * Adds a new task into the TaskList.
     * @param newTask task to be added into the TaskList.
     * @return String denoting result of adding the task into the TaskList.
     */
    public String createTask(Task newTask) {
        taskList.add(newTask);
        int newSize = taskList.size();
        String todoText = String.format("Got it. I've added this task:\n"
                + " %s\n"
                + "Now you have %s tasks in the list", newTask, newSize);
        return todoText;
    }

    /**
     * Marks a task with specified task number in TaskList as done.
     * @param taskNumber task number of task to be marked as done.
     * @return String denoting result of marking task with specified task number as done.
     * @throws IndexOutOfBoundsDukeException If task number given is not valid.
     */
    public String markAsDone(int taskNumber) throws IndexOutOfBoundsDukeException {
        try {
            Task task = taskList.get(taskNumber - 1);
            task.markAsDone();
            String doneText = String.format("Nice! I've marked this task as done:\n"
                    + " %s", task);
            return doneText;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsDukeException(taskNumber, taskList.size(), "task");
        }
    }

    /**
     * Deletes a task with specified task number.
     * @param taskNumber task number of task to be deleted.
     * @return String denoting result of deleting task.
     * @throws IndexOutOfBoundsDukeException If task number given is not valid.
     */
    public String deleteTask(int taskNumber) throws IndexOutOfBoundsDukeException {
        try {
            Task task = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            String deleteText = String.format("Okay. I've removed this task:\n"
                    + " %s", task);
            return deleteText;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsDukeException(taskNumber, taskList.size(), "task");
        }
    }

    /**
     * Finds all tasks matching a substring and returns them in the form of a TaskList.
     * @param substring part of a string to find tasks with it.
     * @return TaskList containing all tasks matching the given substring.
     */
    public TaskList findTasks(String substring) {
        List<Task> listOfTasks = Arrays.asList(
                taskList.stream()
                        .filter(task -> task.description.contains(substring)) // Filter tasks
                 .toArray(Task[]::new)
         );
        return new TaskList(listOfTasks);
    }

    // String Representation
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int sizeOfList = taskList.size();

        for (int i = 0; i < sizeOfList; i++) {
            int number = i + 1;
            String text = taskList.get(i).toString();
            sb.append(String.format(" %s. %s\n", number, text));
        }

        return sb.toString();
    }

}
