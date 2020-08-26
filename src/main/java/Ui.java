import java.util.Scanner;

/**
 * Ui class
 * Handles the I/O of the program
 */
public class Ui {

    private Parser parser;
    private Scanner sc;
    private TaskList tasks;

    /**
     * Constructor that takes in a parser and the tasklist
     * @param parser
     * @param tasks
     */
    public Ui(Parser parser,TaskList tasks) {
        this.parser = parser;
        this.sc = new Scanner(System.in);
        this.tasks = tasks;
    }

    private void printWelcomeMsg() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("______________________");
    }

    private void printByeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------");
    }

    /**
     * Runs the Ui
     */
    public void run() {
        printWelcomeMsg();
        String[] inputs = sc.nextLine().split(" ",2);
        while(!inputs[0].equals("bye")) {
            parser.parseCommand(inputs,tasks);
            inputs = sc.nextLine().split(" ",2);
        }
        printByeMsg();
    }

    /**
     * Returns the final TaskList
     * @return Updated TaskList
     */
    public TaskList getUpdatedTasks() {
        return this.tasks;
    }



}
