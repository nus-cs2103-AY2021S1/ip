import java.util.Arrays;
import java.util.Scanner;

/** Duke is a chatbot that allows users to send input to perform tasks.
 */
public class Duke {
    public static void main(String[] args) {
        // Introduction messages
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // Initialise list of tasks
        int taskCapacity = 100;
        Task[] taskList = new Task[taskCapacity];
        int numTasks = 0;


        // Main conversation loop
        Scanner sc = new Scanner(System.in);
        boolean isSpeaking = true;
        while (isSpeaking) {

            // Get user input
            String userInput = sc.nextLine();

            String[] userTokens = userInput.split(" ");

            // Respond by user commands
            String userCommand = userTokens[0];
            switch(userCommand) {

                // Exit the program
                case "bye":
                    isSpeaking = false;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                // List the tasks available in taskList
                case "list":
                    for (int i = 0; i < numTasks; i++) {
                        System.out.println(i + 1 + "." + taskList[i]);
                    }
                    break;

                // Mark the identified task as done
                case "done":
                    int id = Integer.parseInt(userTokens[1]) - 1;
                    taskList[id].setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + taskList[id]);
                    break;

                // For all other user input, add to task list
                default:
                    taskList[numTasks] = new Task(userInput);
                    numTasks++;
                    System.out.println("added: " + userInput);
            }
        }
    }
}
