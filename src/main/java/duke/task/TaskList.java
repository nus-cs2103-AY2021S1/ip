package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.parser.Parser;
import duke.storage.Storage;

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
     * @return String representation of added To-Do.
     */
    public String addToDo(String description, Storage storage) {
        Task toDo = new ToDo(description);
        taskList.add(toDo);
        storage.addData(toDo);
        String printing = "Gotcha! I've added this task:\n"
                + toDo + "\n" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        return printing;
    }

    /**
     * Adds Deadline tasks into task list.
     * @param description Description of Deadline.
     * @param date Deadline of Deadline task.
     * @param storage Storage created for user.
     * @return String representation of added Deadline.
     */
    public String addDeadline(String description, LocalDateTime date, Storage storage) {
        Task deadline = new Deadline(description, date);
        taskList.add(deadline);
        storage.addData(deadline);
        String printing = "Gotcha! I've added this task:\n"
                + deadline + "\n" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        return printing;
    }

    /**
     * Adds Event tasks into task list.
     * @param description Description of Event.
     * @param date Date and time of Event.
     * @param storage Storage created for user.
     * @return String representation of added Event.
     */
    public String addEvent(String description, LocalDateTime date, Storage storage) {
        Task event = new Event(description, date);
        taskList.add(event);
        storage.addData(event);
        String printing = "Gotcha! I've added this task:\n"
                + event + "\n" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        return printing;
    }

    /**
     * Deletes tasks from task list.
     * @param index Index of task user wants to remove.
     * @param storage Storage created for user.
     * @return String representation of deleted task.
     */
    public String deleteTask(int index, Storage storage) {
        Task taskToBeDeleted = taskList.get(index - 1);
        taskList.remove(index - 1);
        storage.updateData(taskList);
        String deletedTask = "Roger that! I've removed this task:\n"
                + taskToBeDeleted
                + "\nYou have " + taskList.size()
                + " tasks on your list now.\n";
        return deletedTask;
    }

    /**
     * Marks tasks done on task list.
     * @param index Index of task user wants to mark as done.
     * @param storage Storage created for user.
     * @return String representation of done task.
     */
    public String markTaskDone(int index, Storage storage) {
        Task finishedTask = taskList.get(index - 1);
        finishedTask.markAsDone();
        storage.updateData(taskList);
        String doneTask = "\\(^O^)/ Good job! I've marked this task as done:\n"
                + finishedTask + "\nKeep going!\n";
        return doneTask;
    }

    /**
     * Lists all the tasks on task list.
     * @return String representation of list of tasks.
     */
    public String listTasks() {
        String printing = "";
        printing += "Here are the tasks on your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            int number = i + 1;
            printing += "" + number + ". " + taskList.get(i) + "\n";
        }
        return printing;
    }

    /**
     * Finds the tasks according to the keyword user input.
     * @param keyword Keyword provided by user.
     * @return String representation of found tasks.
     */
    public String findTasks(String keyword) {
        boolean canFind = false;
        String printing = "";
        for (Task task : taskList) {
            if (task.getTaskName().contains(keyword)) {
                canFind = true;
                break;
            }
        }
        if (canFind) {
            int index = 1;
            printing += "Here are the matching tasks on your list:\n";
            for (Task task : taskList) {
                if (task.getTaskName().contains(keyword)) {
                    printing += "" + index + ". " + task + "\n";
                    index++;
                }
            }
        } else {
            return "Sorry, there are no tasks that match your keyword!\n";
        }
        return printing;
    }
}
