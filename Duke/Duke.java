package Duke;
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
     * This method adds todoTask for the bot.
     *
     * @param taskName name of the task
     * @return taskName name of the task
     */
    public String addTodo(String taskName) {
        numberOfTasks += 1;
        System.out.println(indent + "Got it. I've added this task:");
        taskList[numberOfTasks - 1] = new Todo(taskName);
        System.out.println(indent + "  " + taskList[numberOfTasks - 1]);
        displayNoOfTasks();
        return taskName;
    }

    /**
     * This method adds deadlineTask for the bot.
     *
     * @param input string to be cleaned and parsed
     * @return boolean true or false depending on the success
     */
    public boolean addDeadline(String input) {
        String cleaned = input.replace("deadline", "").trim();
        String[] splittedInput = input.split("/");
        numberOfTasks += 1;
        System.out.println(indent + "Got it. I've added this task:");
        taskList[numberOfTasks - 1] = new Deadline(splittedInput[0], splittedInput[1].replace("by", "").trim());
        System.out.println(indent + "  " + taskList[numberOfTasks - 1]);
        displayNoOfTasks();
        return true;
    }

    /**
     * This method adds eventTask for the bot.
     *
     * @param input string to be cleaned and parsed
     * @return boolean true or false depending on the success
     */
    public boolean addEvent(String input) {
        String cleaned = input.replace("event", "").trim();
        String[] splittedInput = input.split("/");
        numberOfTasks += 1;
        System.out.println(indent + "Got it. I've added this task:");
        taskList[numberOfTasks - 1] = new Event(splittedInput[0], splittedInput[1].replace("at", "").trim());
        System.out.println(indent + "  " + taskList[numberOfTasks - 1]);
        displayNoOfTasks();
        return true;
    }

    public void displayNoOfTasks() {
        System.out.println(indent + "Now you have " + numberOfTasks + (numberOfTasks > 1 ? " tasks" : " task") + " in the list.");
    }

    /**
     * This method adds task for the bot.
     *
     * @param taskName name of the task
     * @return taskName name of the task
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < numberOfTasks + 1; i++) {
            System.out.println(indent + i + "." + taskList[i - 1]);
        }
    }

    /**
     * This method responds to the user's input.
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

            if (input.contains("done")) {
                int number = Integer.parseInt(String.valueOf(input.charAt(5)))-1;
                if (number >= 0 && number < numberOfTasks) {
                    System.out.println("Nice! I've marked this task as done:");
                    taskList[number].markAsDone();
                    System.out.println(indent + "----------------------------");
                }
                continue;
            }

            if (input.contains("todo")) {
                addTodo(input.replace("todo","").trim());
                System.out.println(indent + "----------------------------");
                continue;
            }

            if (input.contains("deadline")) {
                addDeadline(input);
                System.out.println(indent + "----------------------------");
                continue;
            }

            if (input.contains("event")) {
                addEvent(input);
                System.out.println(indent + "----------------------------");
                continue;
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
