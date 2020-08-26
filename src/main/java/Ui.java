import java.util.List;
import java.util.Scanner;

// deals with interactions with the user
public class Ui {
    private Scanner sc;
    private String line = "_________________________________________________________________";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine().toLowerCase();
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public void sayHi() {
        String str = ("\t" + line + "\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + "\t" + line);
        System.out.println(str);

    }

    public void sayBye() {
        System.out.println("\t" + line + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t" + line);
        sc.close();
    }

    public void printAddTask(Task task, int numOfTask) {
        String tasks = numOfTask == 1 ? "task" : "tasks";
        System.out.println("\t" + line + "\n\tGot it. I've added this task:\n"
                + "\t  " + task + "\n"
                + "\tNow you have " + numOfTask + " " + tasks + " in the list.\n"
                + "\t" + line);
    }

    public void showList(TaskList task) {
        if (task.getList().size() > 0) {
            System.out.println("\t" + line + "\n\tHere are the tasks in your list:");
            int index = 1;
            List<Task> ls = task.getList();
            for (Task t : ls) {
                System.out.println("\t" + index + ". " + t);
                index++;
            }
            System.out.println("\t" + line);
        } else {
            System.out.println("\t" + line);
            System.out.println("\tThere is no task in the list.");
            System.out.println("\t" + line);
        }
    }

    public void printDone(TaskList list, int num) {
        System.out.println("\t" + line + "\n\tNice! I've marked this task as done:\n\t  "
                + list.getList().get(num-1)
                + "\n\t" + line);
    }

    public void printInvalidNumber() {
        System.out.println("☹ OOPS!!! The task number is invalid.");
    }

    public void errorWithFile() {
        System.out.println("☹ OOPS!!! Cannot store tasks.");
    }

    public void printDelete(TaskList list, int num) {
        System.out.println("\t" + line + "\n\tNoted. I've removed this task:\n\t  "
                + list.getList().get(num-1)
                + "\n\tNow you have " + (list.getList().size() - 1) + " tasks in the list."
                + "\n\t" + line);
    }

    public void printNoDescription() {
        System.err.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public void printNoDate() {
        System.err.println("☹ OOPS!!! The description or date of a deadline cannot be empty.");
    }

    public void printNoDateEvent() {
        System.err.println("☹ OOPS!!! The description or date of an event cannot be empty.");
    }

    public void printInvalidDate() {
        System.err.println("☹ OOPS!!! The date must be valid and in the format of YYYY-MM-DD.");
    }

    public void errorWithLoading() {
        System.err.println("☹ OOPS!!! Your file cannot be loaded :-(");
    }
}
