import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> list;
    private static final String starline = "**************************************************************************";

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void list() {
        System.out.println(starline + "\nHere are the tasks in your list:");
        for (int i=0; i < this.list.size(); i++) {
            printTask(i);
        }
        System.out.println(starline);
    }

    public void printTask(int listIndex) {
        String task = String.format("%d.%s", listIndex+1, this.list.get(listIndex));
        System.out.println(task);
    }

    public void echo(String input) {
        System.out.println("added: " + input);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void add(String input) {
        String[] splitInput = input.split(" ", 2);
        String taskType = splitInput[0];
        Task newTask;
        switch(taskType) {
            case "todo":
                if (input.matches("todo (\\S+\\s?)+")) {
                    newTask = new Todo(splitInput[1]);
                    break;
                } else if (input.matches("todo\\s?")) {
                    throw new IllegalArgumentException("OOPS! The description of a todo cannot be empty.");
                } else {
                    throw new IllegalArgumentException("OOPS! Invalid syntax. To add a todo, use:\n         " + Todo.getFormat());
                }
            case "deadline":
                if (input.matches("deadline (\\S+\\s?)+ /by (\\S+\\s?)+")) {
                    String[] splitDeadline = splitInput[1].split(" /by ");
                    String deadlineDesc = splitDeadline[0];
                    String by = splitDeadline[1];
                    newTask = new Deadline(deadlineDesc, by);
                    break;
                } else if (input.matches("deadline\\s?") || !input.contains(" by ")){
                    throw new IllegalArgumentException("OOPS! The description/deadline of a deadline cannot be empty.");
                } else {
                    throw new IllegalArgumentException("OOPS! Invalid syntax. To add a deadline, use:\n         " + Deadline.getFormat());
                }
            case "event":
                if (input.matches("event (\\S+\\s?)+ /at (\\S+\\s?)+")) {
                    String[] splitEvent = splitInput[1].split(" /at ");
                    String eventDesc = splitEvent[0];
                    String at = splitEvent[1];
                    newTask = new Event(eventDesc, at);
                    break;
                } else if (input.matches("event\\s?") || !input.contains(" at ")) {
                    throw new IllegalArgumentException("OOPS! The description/location of an event cannot be empty.");
                } else {
                    throw new IllegalArgumentException("OOPS! Invalid syntax. To add an event, use:\n         " + Event.getFormat());
                }
            default:
                throw new IllegalArgumentException("OOPS! There is no task of type " + taskType + "!");
        }
        this.list.add(newTask);
        echo(newTask.toString());
    }

    public void markTaskAsDone(String listIndexString) {
        try {
            int listIndexNumber = Integer.parseInt(listIndexString);
            Task task = this.list.get(listIndexNumber - 1);
            task.markDone();
            System.out.println("Nice! I've marked this task as done:\n" + task);
        } catch (IndexOutOfBoundsException ex) { // if list index is not in the list
            System.out.println("OOPS! This task index does not exist! Type 'list' to check out your tasks.");
        } catch (NumberFormatException ex) { // if list index string is not an integer
            System.out.println("OOPS! The keyword 'done' is used to check off tasks as follows:" +
                    "   done <task index>");
        }
    }
    
    public void deleteTask(String listIndexString) {
        try {
            int listIndexNumber = Integer.parseInt(listIndexString);
            Task task = this.list.get(listIndexNumber - 1);
            this.list.remove(listIndexNumber - 1);
            System.out.println("The following task has been deleted:\n" + task);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("OOPS! This task index does not exist! Type 'list' to check out your tasks.");
        } catch (NumberFormatException ex) { // if list index string is not an integer
            System.out.println("OOPS! The keyword 'delete' is used to delete tasks as follows:" +
                    "   delete <task index>");
        }
    }
}
