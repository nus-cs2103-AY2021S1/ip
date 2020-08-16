import task.Task;
import task.TaskManager;
import utils.Formatter;
import java.util.Scanner;

/**
 * A command line interface application that reads user inputs, executes the input and prints out the
 * result of executing the input
 */
public class CommandLineInterface {
    /**
     * This will determine the number of underscores each divider should be made of
     */
    private static final int dividerLength = 70;
    /**
     * This will determine the left padding size of the messages that the application outputs
     */
    private static final int leftPadding = 7;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Formatter formatter = new Formatter(dividerLength, leftPadding);
    private static final TaskManager taskManager = new TaskManager();

    public static void run() {
        String welcomeMessage = "Hello! I'm Erica \n" + "How may I be of assistance?\n";
        formatter.print(welcomeMessage);
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            String[] words = userInput.split(" ");
            if(words[0].equals("done")){
                int taskIndex = Integer.parseInt(words[1]);
                formatter.print(taskManager.markTaskAsDone(taskIndex));
            } else if (userInput.toLowerCase().equals("bye")) {
                formatter.print("Bye. Hope my service has been satisfactory. Hope to see you again soon.");
                break;
            } else if (userInput.toLowerCase().equals("list")) {
                formatter.print(taskManager.getAllTasks());
            } else {
                Task newUserTask = new Task(userInput);
                formatter.print(taskManager.addTask(newUserTask));
            }
        }
        scanner.close();
    }
}
