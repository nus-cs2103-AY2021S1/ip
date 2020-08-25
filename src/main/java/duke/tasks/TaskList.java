package duke.tasks;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a TaskList of all the tasks managed in Duke.
 */
public class TaskList {
    protected ArrayList<Task> taskLs;

    public TaskList() {
        taskLs = new ArrayList<>();
    }

    public ArrayList<Task> getTaskLs() {
        return this.taskLs;
    }

    public void add(Task toAdd) {
        this.taskLs.add(toAdd);
    }

    /**
     * Prints out a statement to inform users that the input
     * is invalid.
     */
    public static void confused() {
        String str = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(str);
    }

    /**
     * Removes the task from the list of tasks.
     * @param toDelete task to be removed.
     */
    public void delete(String toDelete) {
        String command = toDelete.replaceAll("[^\\d.]", "");
        int i = Integer.parseInt(command.trim());
        Task deletedTask = this.taskLs.get(i-1);
        this.taskLs.remove(i-1);
        int numTask = this.taskLs.size();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(deletedTask);
        System.out.println("Now you have " + numTask + " tasks in the list.");
    }

    /**
     * Lists all the task from the list of tasks.
     */
    public void list() {
        int i = 1;
        System.out.println("Here are the tasks in your list: ");
        this.taskLs.forEach(n -> System.out.println(this.taskLs.indexOf(n) + 1 + ". " + n));
    }

    /**
     * Lists the task from the list of tasks.
     */
    public void findList() {
        int i = 1;
        this.taskLs.forEach(n -> System.out.println(this.taskLs.indexOf(n) + 1 + ". " + n));
    }

    /**
     * Marks the task as done.
     * @param toPrint contains int to mark the specific task as done.
     */
    public void done(String toPrint) {
        String command = toPrint.replaceAll("[^\\d.]", "");
        int indexCommand = Integer.parseInt(command.trim());
        System.out.println("Nice! I've marked this task as done: ");
        Task completedTask = this.taskLs.get(indexCommand - 1);
        completedTask.markAsDone();
        System.out.println("[" + completedTask.getStatusIcon() + "] " + completedTask.description);
    }

    /**
     * Creates a todo task and adds it to the list of tasks.
     * @param toPrint Description of task.
     */
    public void todo(String toPrint) {
        try {
            toPrint = toPrint.substring(4);
            if (toPrint.isEmpty()) {
                throw new DukeException("");
            }
            Todo taskTodo = new Todo(toPrint);
            this.taskLs.add(taskTodo);

            System.out.println("Got it. I've added this task:");
            System.out.println(taskTodo);
            System.out.println("Now you have " + this.taskLs.size() + " tasks in the list.");

        } catch (DukeException e) {
            Todo.invalidInput();
        }
    }

    /**
     * Creates a event task and adds it to the list of tasks.
     * @param toPrint Description and at of task.
     */
    public void event(String toPrint) {
        try {
            toPrint = toPrint.substring(5);
            String[] arrtoPrint = toPrint.split("/at ");
            Event taskEvent = new Event(arrtoPrint[0], arrtoPrint[1]);
            this.taskLs.add(taskEvent);

            System.out.println("Got it. I've added this task:");
            System.out.println(taskEvent);
            System.out.println("Now you have " + this.taskLs.size() + " tasks in the list.");

        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            Event.invalidInput();
        }
    }

    /**
     * Creates a deadline task and adds it to the list of tasks.
     * @param toPrint Description and by of task.
     */
    public void deadline(String toPrint) {
        try {
            toPrint = toPrint.substring(8);
            String[] arrtoPrint = toPrint.split("/by ");

            Deadline taskDeadline = new Deadline(arrtoPrint[0], arrtoPrint[1]);
            this.taskLs.add(taskDeadline);

            System.out.println("Got it. I've added this task:");
            System.out.println(taskDeadline);
            System.out.println("Now you have " + this.taskLs.size() + " tasks in the list.");

        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            Deadline.invalidInput();
        }
    }

    /**
     * Finds the tasks in the list of tasks matching the keyword.
     * @param toPrint Keyword entered by user.
     */
    public void find(String toPrint) {
        TaskList duplicateTaskLs = new TaskList();
        duplicateTaskLs.taskLs = new ArrayList<>(this.getTaskLs());

        toPrint = toPrint.substring(4);
        String finalToPrint = toPrint;

        duplicateTaskLs.taskLs.removeIf(n -> !n.getDescription().contains(finalToPrint));
        System.out.println("Here are the matching tasks in your list: ");
        duplicateTaskLs.findList();
    }

}
