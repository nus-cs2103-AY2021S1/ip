package duke.task;

import duke.parser.Parser;
import duke.storage.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents the task list of user.
 */
public class TaskList {
    /**
     * Arraylist for the task list.
     */
    private final ArrayList<Task> taskList;

    /**
     * Creates an empty task list if user does not have existing
     * data in files.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates a task list with user's existing data.
     * @param tasks String representation of tasks in user's data.
     */
    public TaskList(ArrayList<String> tasks) {
        this.taskList = new ArrayList<>();
        for (String task : tasks) {
            taskList.add(Parser.textToTask(task));
        }
    }

    /**
     * Returns number of tasks in task list.
     * @return number of tasks.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Adds To-Do tasks into task list.
     * @param description Description of To-Do.
     * @param storage Storage created for user.
     */
    public void addToDo(String description, Storage storage) {
        Task toDo = new ToDo(description);
        taskList.add(toDo);
        storage.saveData(taskList);
        String printing = "\tGotcha! I've added this task:\n\t\t"
                + toDo + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        System.out.print(printing);
    }

    /**
     * Adds Deadline tasks into task list.
     * @param description Description of Deadline.
     * @param date Deadline of Deadline task.
     * @param storage Storage created for user.
     */
    public void addDeadline(String description, LocalDateTime date, Storage storage) {
        Task deadline = new Deadline(description, date);
        taskList.add(deadline);
        storage.saveData(taskList);
        String printing = "\tGotcha! I've added this task:\n\t\t"
                + deadline + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        System.out.print(printing);
    }

    /**
     * Adds Event tasks into task list.
     * @param description Description of Event.
     * @param date Date and time of Event.
     * @param storage Storage created for user.
     */
    public void addEvent(String description, LocalDateTime date, Storage storage) {
        Task event = new Event(description, date);
        taskList.add(event);
        storage.saveData(taskList);
        String printing = "\tGotcha! I've added this task:\n\t\t"
                + event + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        System.out.print(printing);
    }

    /**
     * Deletes tasks from task list.
     * @param index Index of task user wants to remove.
     * @param storage Storage created for user.
     */
    public void deleteTask(int index, Storage storage) {
        Task taskToBeDeleted = taskList.get(index - 1);
        taskList.remove(index - 1);
        storage.saveData(taskList);
        String deletedTask = "\tRoger that! I've removed this task:\n\t\t"
                + taskToBeDeleted
                + "\n\tYou have " + taskList.size()
                + " tasks on your list now.\n";
        System.out.print(deletedTask);
    }

    /**
     * Marks tasks done on task list.
     * @param index Index of task user wants to mark as done.
     * @param storage Storage created for user.
     */
    public void markTaskDone(int index, Storage storage) {
        Task finishedTask = taskList.get(index - 1);
        finishedTask.markAsDone();
        storage.saveData(taskList);
        String doneTask = "\t\\(^O^)/ Good job! I've marked this task as done:\n\t\t"
                + finishedTask + "\n\tKeep going!\n";
        System.out.print(doneTask);
    }

    /**
     * Lists all the tasks on task list.
     */
    public void listTasks() {
        System.out.print("\tHere are the tasks on your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            int number = i + 1;
            System.out.print("\t" + number + ". " + taskList.get(i) + "\n");
        }
    }
}
