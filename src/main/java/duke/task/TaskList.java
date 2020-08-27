package duke.task; 

import java.util.List;
import duke.DukeException;
import duke.util.Parser;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task into the list, according to user's input.
     * @param input User's input
     */
    public void addTask(String input) throws DukeException {
        String[] split = input.split(" ", 2);

        if (split.length < 2) {
            throw new DukeException("\tSorry! The description of a todo cannot be empty :')");
        } else {
            String keyword = split[0];
            String description = split[1];

            switch (keyword) {
            case "todo":
                Task task = new ToDo(description, false);
                this.tasks.add(task);
                printAddedConfirmation(task);
                break;
            case "deadline":
                String[] splitSlash = description.split(" /by ");
                if (splitSlash.length != 2) {
                    throw new DukeException("\tPaise! :') Please use the format: deadline <task> /by <time>" +
                            "\n\t\t*time format: <yyyy-mm-dd> or  <yyyy-mm-dd HH:mm>");
                }
                task = new Deadline(splitSlash[0], false, Parser.parseDate(splitSlash[1]));
                this.tasks.add(task);
                printAddedConfirmation(task);
                break;
            case "event":
                splitSlash = description.split(" /at ");
                if (splitSlash.length != 2) {
                    throw new DukeException("\tPaise! :') Please use the correct format: event <task> /at <time> +" +
                            "\n\t\t*time format: <yyyy-mm-dd> or  <yyyy-mm-dd HH:mm>");
                }
                task = new Event(splitSlash[0], false, Parser.parseDate(splitSlash[1]));
                this.tasks.add(task);
                printAddedConfirmation(task);
                break;
            default:
                break;
            }
        }
    }

    public void printAddedConfirmation(Task task) {
        int size = this.tasks.size();
        System.out.println("\tOkay! I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println(String.format("\tNow you have %d %s in the list. Jiayous! :D", size, size > 1 ? "tasks" : "task"));
    }

    /**
     * Sets the task's isDone status to true.
     * @param index Index of the task in the task list.
     */
    public void markTaskAsDone(int index) throws DukeException {
        Task task = this.tasks.get(index);
        task.toggleIsDone();
        try {
            this.tasks.set(index, task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry! THe index is out of bounds! :')");
        } catch (NumberFormatException e) {
            throw new DukeException("\tPaise! :') Please use the correct format: done <order of task in the list>");
        }
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + task);
    }

    /**
     * Removes the (index + 1)th task from the task list.
     * @param index Index of the to-be-deleted task in the task list.
     */
    public void deleteTask(int index) throws DukeException {
        try {
            Task task = this.tasks.get(index);
            int size = this.tasks.size();
            this.tasks.remove(index);

            System.out.println("\tOkay! I've removed this task:\n\t\t" + task.toString());
            System.out.println(String.format("\tNow you have %d %s in the list. Jiayous! :D", size - 1, size - 1 > 1 ? "tasks" : "task"));

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry! The index is out of bounds! :')");
        } catch (NumberFormatException e) {
            throw new DukeException("\tPaise! :') Please use the correct format: delete <order of task in the list>");
        }
    }

    /**
     * Returns the list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }
}
