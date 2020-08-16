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
                newTask = new Todo(splitInput[1]);
                break;
            case "deadline":
                String[] splitDeadline = splitInput[1].split(" /by ");
                String deadlineDesc = splitDeadline[0];
                String by = splitDeadline[1];
                newTask = new Deadline(deadlineDesc, by);
                break;
            case "event":
                String[] splitEvent = splitInput[1].split(" /at ");
                String eventDesc = splitEvent[0];
                String at = splitEvent[1];
                newTask = new Event(eventDesc, at);
                break;
            default:
                newTask = new Task(input);
                break;
        }
        this.list.add(newTask);
        echo(newTask.toString());
    }

    public void markTaskAsDone(int listIndex) {
        try {
            Task task = this.list.get(listIndex);
            task.markDone();
            System.out.println("Nice! I've marked this task as done: \n" + task);
        } catch (IndexOutOfBoundsException ex2) {
            System.out.println("You do not have this task yet! Type 'list' to check out your tasks.");
        }
    }
}
