import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String CHATBOT = "Bob: ";
    private static final String SKIPLINE = "\n";
    private static final String USER = SKIPLINE + "You: ";

    public static void main(String[] args) {
        TaskList tasks = new TaskList();

        // Greetings
        System.out.println(CHATBOT + "Hey there! I'm Bob" + SKIPLINE + "What can I do for you today?");
        System.out.println(USER);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String description = sc.nextLine();

            boolean exit = description.equals("bye");

            // Exit chatbot
            if (exit) {
                break;
            }

            boolean markDone = description.substring(0, 4).equals("done");
            boolean showListOfCommands = description.equals("list");

            // Mark indicated task as done
            if (markDone) {
                int lengthOfCommand = description.length();
                int index = Integer.parseInt(description.substring(5, lengthOfCommand));
                markTaskAsDone(tasks, index - 1);
            }

            // Display list of tasks to user
            else if (showListOfCommands) {
                displayTaskList(tasks);

            // Add a new task to the list
            } else {
                updateTaskList(tasks, description);
            }

            // Prompt user commands
            System.out.println(USER);

        }

        sc.close();

        // Exit chatbot
        System.out.println(SKIPLINE + CHATBOT + "Goodbye! Have a nice day :D");
    }

    private static void displayTaskList(TaskList tasks) {
        System.out.println(SKIPLINE + CHATBOT);

        if (tasks.totalNumberOfTasks() == 0) {
            System.out.println("List is empty :(");
        } else {
            System.out.println("Here is your current list of tasks:");
            System.out.println(tasks);
        }
    }

    private static void updateTaskList(TaskList tasks, String description) {
        Task newTask = new Task(description);
        tasks.addNewTask(newTask);

        System.out.println();
        System.out.println(CHATBOT + "added '" + description + "'");
    }

    private static void markTaskAsDone(TaskList tasks, int index) {
        System.out.println(SKIPLINE + CHATBOT);

        Task doneTask = tasks.getTask(index);
        doneTask.markAsDone();

        System.out.println("Good job completing this task! I've marked this task as done:");
        System.out.println(doneTask);
        System.out.println("Keep up the good work :)");
    }
}
