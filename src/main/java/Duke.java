import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();

    /*
     * Prints Duke's welcome message
     */
    private static void welcome() {
        printOutput("     Hello! I'm Duke\n" +
                        "     What can I do for you?", true);
    }

    /*
     * Wraps input in dashes
     */
    private static void printOutput(String input, boolean lastNewLine) {
        if (lastNewLine) {
            System.out.println("    ____________________________________________________________\n" +
                    input + "\n" +
                    "    ____________________________________________________________");

        } else {
            System.out.println("    ____________________________________________________________\n" +
                    input +
                    "    ____________________________________________________________");
        }
    }

    /*
     * Handles input, and calls corresponding functions
     * @return: returns boolean of whether or not to continue
     */
    private static boolean inputHandler(String input) {
        String action = input.split(" ")[0];
        switch(action) {
            case "list":
                listTasks();
                return true;
            case "done":
                done(input);
                return true;
            case "bye":
                exit();
                return false;
            default:
                handleTask(input);
                return true; 
        }
    }

    /*
     * Does all necessary actions before exiting
     */
    private static void exit() {
        printOutput("     Bye. Hope to see you again soon!", true);
    }

    private static void handleTask(String input) {
        Task task = new Task(input);
        taskList.add(task);
        printOutput("     added: " + task.getDescription(), true);
    }

    private static void listTasks() {
        String output = "     Here are the tasks in your list:\n";
        int index = 1;
        for (Task task : taskList) {
            output += "     " + index + "." + task.strTask() + "\n";
            index++;
        }
        printOutput(output, false);
    }

    private static void done(String input) {
        String strIndex = input.split(" ")[1];
        int index = Integer.parseInt(strIndex);
        Task task = taskList.get(index - 1);
        task.setDone(true);
        String output = "     Nice! I've marked this task as done: \n" +
                                "       " + task.strTask();
        printOutput(output, true);
    }

    /*
     * reads user input, and does corresponding action
     */
    public static void main(String[] args) {
        welcome();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (true) {
            boolean toContinue = inputHandler(input);
            if (toContinue) {
                input = scanner.nextLine();
            } else {
                break;
            }
        }
    }
}








