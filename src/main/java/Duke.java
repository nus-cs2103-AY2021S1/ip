import java.util.Scanner;

/**
 * The Best2013TBot program that implements a chatbot.
 *
 * @author Zeng Yu Ting
 * @version 1.0
 * @since 2020-15-08
 */
public class Duke {

    private static String indent = "   ";
    private Task[] taskList = new Task[100];
    private int numberOfTasks = 0;

    /**
     * This method greets the user.
     */
    public static void greet() {
        System.out.println(indent + "----------------------------");
        System.out.println(indent + "Hello! I'm Best2103/TBot\n"+ indent + "What can I do for you?");
        System.out.println(indent + "----------------------------");
    }

    /**
     * This method add task for the bot.
     *
     * @param taskName name of the task
     */
    public String addTask(String taskName) {
        numberOfTasks += 1;
        taskList[numberOfTasks - 1] = new Task(taskName);
        System.out.println(indent + "Added: " + taskName);
        return taskName;
    }

    /**
     * This method displays the task list.
     */
    public void displayList() {
        for (int i = 1; i < numberOfTasks + 1; i++) {
            System.out.println(indent + i + ". " + taskList[i - 1]);
        }
    }

    /**
     * This method respond to the user.
     */
    public void respond() {
        Scanner userInput = new Scanner(System.in);
        while(true) {
            String input = userInput.nextLine();
            System.out.println(indent + "----------------------------");
            if (input.trim().equals("bye")) {
                System.out.println(indent + "Bye. Hope to see you again soon!");
                System.out.println(indent + "----------------------------");
                break;
            }

            if (input.trim().equals("list")) {
                displayList();
                System.out.println(indent + "----------------------------");
                continue;
            }
            addTask(input);
            System.out.println(indent + "----------------------------");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.respond();
    }
}
