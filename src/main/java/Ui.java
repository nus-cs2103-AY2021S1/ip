import java.util.Scanner;

public class Ui {

    private Parser parser;
    private Scanner sc;
    private TaskList tasks;

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

    public void run() {
        printWelcomeMsg();
        String[] inputs = sc.nextLine().split(" ",2);
        while(!inputs[0].equals("bye")) {
            parser.parseCommand(inputs,tasks);
            inputs = sc.nextLine().split(" ",2);
        }
        printByeMsg();
    }

    public TaskList getUpdatedTasks() {
        return this.tasks;
    }



}
