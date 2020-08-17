import task.Task;
import task.Todo;
import task.Event;
import task.Deadline;
import task.TaskManager;
import utils.Formatter;

import java.util.Scanner;
import java.util.Arrays;

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
            String[] words = userInput.toLowerCase().split(" ");
            if (words[0].equals("done")) {
                int taskIndex = Integer.parseInt(words[1]);
                formatter.print(taskManager.markTaskAsDone(taskIndex));
            } else if (words[0].equals("todo")) {
                String content = userInput.replaceFirst("todo ", " ");
                Todo newTodoTask = new Todo(content);
                formatter.print(taskManager.addTask(newTodoTask));
            } else if (words[0].equals("deadline")) {
                userInput = userInput.replaceFirst("deadline ", " ");
                String[] userInputArgs = userInput.split("\\s*/by\\s*");
                String content = userInputArgs[0];
                String dateTime = userInputArgs[1];
                Deadline newDeadlineTask = new Deadline(content, dateTime);
                formatter.print(taskManager.addTask(newDeadlineTask));
            } else if (words[0].equals("event")) {
                userInput = userInput.replaceFirst("event ", " ");
                String[] userInputArgs = userInput.split("\\s*/at\\s*");
                String content = userInputArgs[0];
                String dateTime = userInputArgs[1];
                Event newEventTask = new Event(content, dateTime);
                formatter.print(taskManager.addTask(newEventTask));
            } else if (userInput.toLowerCase().equals("bye")) {
                formatter.print("Bye. Hope my service has been satisfactory. Hope to see you again soon.");
                break;
            } else if (userInput.toLowerCase().equals("list")) {
                formatter.print(taskManager.getAllTasks());
            }
        }
        scanner.close();
    }

}
