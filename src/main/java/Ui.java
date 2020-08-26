import java.util.List;
import java.util.Scanner;

public class Ui {
    private final String LINE = "____________________________________________________________";
    private final String GREETING_MESSAGE = LINE +
            "\n Quack! I am Duck" +
            "\n How can I help you today?\n" + LINE;
    private final String EXIT_MESSAGE = LINE +
            "\n Waddling off now. See you soon! \n" + LINE;

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(GREETING_MESSAGE);
    }

    public void printError(String errorMessage) {
        System.out.println(LINE);
        System.out.println(errorMessage);
        System.out.println(LINE);
    }

    public String getNextInput() {
        return sc.nextLine();
    }

    public void close() {
        System.out.println(EXIT_MESSAGE);
        sc.close();
    }

    public void listStoredTasks(List<Task> stored_tasks) {
        if (stored_tasks.isEmpty()) {
            System.out.println("No tasks stored...");
        } else {
            System.out.println(LINE);
            System.out.println("Quack! Here are the tasks in your list:");
            int count = 1;
            for (Task task : stored_tasks) {
                System.out.println(count + ". " + task);
                count++;
            }
            System.out.println(LINE);
        }
    }

    public void printDoneMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Quack! I have marked this task as done: \n" + task);
        System.out.println(LINE);
    }

    public void printAddMessage(Task task, int count) {
        System.out.println(LINE);
        System.out.println("Quack! I have added: " + task);
        displayTaskCount(count);
        System.out.println(LINE);
    }

    public void printDeleteMessage(Task taskToDelete, int count) {
        System.out.println(LINE);
        System.out.println("Quack! I have deleted this task: \n" + taskToDelete);
        displayTaskCount(count);
        System.out.println(LINE);
    }

    public void displayTaskCount(int numOfTasks) {
        if (numOfTasks == 1) {
            System.out.println("My duck senses tell me you have 1 task in the list.");
        } else {
            System.out.println("My duck senses tell me you have " + numOfTasks + " tasks in the list.");
        }
    }
}
