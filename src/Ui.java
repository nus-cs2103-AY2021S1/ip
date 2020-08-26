import java.util.Scanner;

public class Ui {
    private Scanner userInput;
    private String lastError;

    public Ui() {
        userInput = new Scanner(System.in);
    }

    protected String getInput() {
        return userInput.nextLine();
    }

    protected void startBot() {
        printBorder();
        System.out.println("Hello I'm Duke\n");
        System.out.println("What can I do for you?\n");
        printBorder();
    }

    protected void stopBot() {
        printBorder();
        System.out.println("Bye. Hope to see you again soon!");
        printBorder();
    }

    protected void printError(String error) {
        this.lastError = error;
        System.out.println(error);
    }

    public void printBorder() {
        System.out.println("____________________________________________________________\n");
    }

    public void printTaskList(TaskList tasks) {
        printBorder();
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
    }

    public void doneTask(Task t) {
        printBorder();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }


    public void deleteTask(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n" + task);
    }

    public void addTask(Task task, TaskList tasks) throws DukeException {
        System.out.println("Got it. I've added this task:\n" + task);
        try {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
        } catch (Exception e) {
            throw new DukeException("failed to provide task info sufficiently");
        }
    }
}