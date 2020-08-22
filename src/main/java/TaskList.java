import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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

    public static void confused() {
        String str = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(str);
    }

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

    public void list(String toPrint) {
        int i = 1;
        System.out.println("Here are the tasks in your list: ");
        this.taskLs.forEach(n -> System.out.println(this.taskLs.indexOf(n) + 1 + ". " + n));
    }

    public void done(String toPrint) {
        String command = toPrint.replaceAll("[^\\d.]", "");
        int indexCommand = Integer.parseInt(command.trim());
        System.out.println("Nice! I've marked this task as done: ");
        Task completedTask = this.taskLs.get(indexCommand - 1);
        completedTask.markAsDone();
        System.out.println("[" + completedTask.getStatusIcon() + "] " + completedTask.description);
    }

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

}
