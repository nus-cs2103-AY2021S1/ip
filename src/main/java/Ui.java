import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    protected final Scanner input;
    protected final PrintStream output;
    private static final String Message_GREETING =
            "Hello :))!  I'm your daily manager, Ka To! Welcome Back! \n" +
                    "how could I serve you now? \n" +
                    "You could ask me any question if you like! \n" +
                    "____________________________________________________________";

    private static final String Message_Bye = "Ka To: \n" +
            "I am happy to serve you. See you soon!";

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.input = new Scanner(in);
        this.output = out;
    }

    public void printGreet() {
        System.out.println(Message_GREETING);
    }

    public void printBye() {
        System.out.println(Message_Bye);
    }

    public String getInput() {
        String newInput = input.nextLine();
        return newInput;
    }

    public void printTaskList(TaskList tasklist) {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("This is your task list: \n");

        for (int i = 1; i < tasklist.taskCounts + 1; i++) {
            Task task = tasklist.tasks.get(i - 1);
            System.out.println("" + i + "." + task);
        }

    }

    public void printEmptyList() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, the task list is empty");
    }

    public void addTaskSuccessful(Task added, TaskList list) {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Congratulations! This is added! \n");
        System.out.println(added);
        System.out.println(" You have " + list.getTaskCounts() + " tasks in the list");
    }

    public void deleteTaskSuccessful(Task deleted, TaskList list) {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Kay, this task now is deleted: \n");
        System.out.println(deleted);
        System.out.println("Now you have " + list.getTaskCounts() + " tasks in the list");
    }

    public void markTaskDone(Task done) {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Congratulations! This is now marked as done!\n");
        System.out.println(done);
    }

    public void failToMarkDone() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, please specify the task index to be marked");
    }

    public void failToFindTask() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, please ensure the task index to be marked is correct");
    }

    public void failToDelete() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, please specify the task index to be deleted");
    }

    public void failToFindDetails() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, please specify your task");
    }

    public void failToFindTime() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, please specify the time for the task");
    }

    public void failToUnderstand() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Sorry, I could not answer that ..");
    }

    public void failToLoad() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("File is not found ..");
    }
}