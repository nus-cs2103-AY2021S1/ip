import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents a class for storage that deals with interactions with the user.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String LS = System.lineSeparator();
    private static final String LINE =  "____________________________________________________________\n";

    private static final String GREETING= "Hello! I'm Duke from the chat bot universe ~ \n" +
            "Nice to meet you! \n" +
            "I'll be your task manager from now onwards.\n";

    private static final String BYE = "Awwww, I guess you are gonna leave... \n" +
            "I'll keep track of your tasks nicely. \n" +
            "Text me if you wanna talk again! Have a nice day!\n";


    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public Scanner getIn() {
        return in;
    }

    public PrintStream getOut() {
        return out;
    }

    /**
     * This method is used to greet the user.
     */
    public void greet() {
        System.out.println(LINE + GREETING + LINE);
    }

    /**
     * This method is used to say good bye to the user.
     */
    public void bye() {
        System.out.println(LINE + BYE + LINE);
    }

    /**
     * This method is used to get command from the user.
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /**
     * This method is used to print all the tasks in the task list.
     * @param taskList The task list used for printing.
     */
    public void printAll(TaskList taskList) {
        System.out.println(LINE + "Here are the tasks in your list: \n");
        for (int i = 1; i < taskList.getNoOfTasks() + 1; i++) {
            Task cur = taskList.list.get(i - 1);
            System.out.println("" + i + "." + cur);
        }
        System.out.println(LINE);
    }

    /**
     * This method is used to warn the user that the list is empty.
     */
    public void emptyList() {
        Exception ex = new InvalidInputException("Oops, your list is currently empty. Add some tasks first!");
        System.err.println(LINE + ex.getMessage() + "\n" + LINE);
    }

    // ADD RELATED
    public void addSuccessful(Task added, TaskList list) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task: \n" + added);
        System.out.println("Now you have " + list.getNoOfTasks() + " tasks in the list. ");
        System.out.println(LINE);
    }

    /**
     * This method is used to warn the user that the command is undefined.
     */
    public void unknownCommand() {
        Exception ex = new InvalidInputException("Ah oh! I didn't know what that means >n<, sorry! ");
        System.err.println(LINE + ex.getMessage() + "\n" + LINE);
    }

    // DELETE RELATED
    public void deleteSuccessful(Task deleted, TaskList list) {
        System.out.println(LINE);
        System.out.println("Got it. I've removed this task: \n" + deleted);
        System.out.println("Now you have " + list.getNoOfTasks() + " tasks in the list. ");
        System.out.println(LINE);
    }

    /**
     * This method is used to warn the user that task to be deleted is not specified.
     */
    public void incompleteDeleteCommand() {
        Exception ex = new InvalidInputException("Hey, you forgot to tell me which task to delete");
        System.err.println(LINE + ex.getMessage() + "\n" + LINE);
    }


    // MODIFY
    public void markDoneSuccessful(Task done) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done: \n" + done);
        System.out.println(LINE);
    }

    public void markDoneFailure() {
        Exception ex = new InvalidInputException("Hey, you forgot to tell me which task is done!");
        System.err.println(LINE + ex.getMessage() + "\n" + LINE);
    }

    /**
     * This method is used to warn the user that the task referred to has not been created.
     */
    public void uncreatedTask() {
        Exception ex = new InvalidInputException("Oops, this task has not been created yet!");
        System.err.println(LINE + ex.getMessage() + "\n" + LINE);
    }

    /**
     * This method is used to warn the user that there is not prior data from Duke.
     */
    public void showLoadingError() {
        System.out.println("Creating the storage file...");
    }

    public void missingDescription(String type) {
        Exception ex = new InvalidInputException("Hey, you forget the description for your " + type +"!");
        System.err.println(LINE + ex.getMessage() + "\n" + LINE);
    }

    /**
     * This method is used to warn the user that the deadline information is missing.
     */
    public void missingDeadline() {
        Exception ex = new InvalidInputException("Sorry, but I can't help if you don't tell me the exact deadline!");
        System.err.println(LINE + ex.getMessage() + "\n" + LINE);
    }

    /**
     * This method is used to warn the user that the event time is missing.
     */
    public void missingEventTime() {
        Exception ex = new InvalidInputException("I see...But what time is this event at?");
        System.err.println(LINE + ex.getMessage() + "\n" + LINE);
    }


}

