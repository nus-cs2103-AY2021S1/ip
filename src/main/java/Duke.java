import java.util.ArrayList;
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
        ArrayList<Task> taskList = new ArrayList<>();
        int numTasks = 0;

        // Declare task tokens parsed from user input
        String[] taskTokens;
        String taskName;

        // Main conversation loop
        Scanner sc = new Scanner(System.in);
        boolean isSpeaking = true;
        while (isSpeaking) {

            // Process user input
            String userInput = sc.nextLine();
            String[] userTokens = userInput.split(" ");
            String userCommand = userTokens[0];
            String userTask = String.join(" ", Arrays.copyOfRange(userTokens, 1, userTokens.length));

            // Validate command
            try {
                InputValidation.validateCommand(userCommand);
            } catch (DukeException e) {
                System.out.println("Sorry, I can't do that command!");
            }

            switch(userCommand) {

                // Exit the program
                case "bye":
                    isSpeaking = false;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                // List the tasks available in taskList
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < numTasks; i++) {
                        System.out.println(i + 1 + "." + taskList.get(i));
                    }
                    break;

                // Create a to-do task
                case "todo":
                    // Validate task
                    try {
                        InputValidation.validateTask(userCommand, userTask);
                    } catch (DukeException e) {
                        System.out.println("Sorry, I can't do that task!");
                        break;
                    }
                    taskList.add(new Todo(userTask));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + taskList.get(numTasks));
                    System.out.println("Now you have " + (numTasks + 1) + " tasks in the list.");
                    numTasks++;
                    break;


                // Create a deadline task (contains "/by")
                case "deadline":
                    // Validate deadline
                    try {
                        InputValidation.validateDeadline(userCommand, userTask);
                    } catch (DukeException e) {
                        System.out.println("Sorry, I can't do that deadline!");
                        break;
                    }
                    taskTokens = userTask.split(" /by ");
                    taskName = taskTokens[0];
                    String taskBy = taskTokens[1];
                    taskList.add(new Deadline(taskName, taskBy));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + taskList.get(numTasks));
                    System.out.println("Now you have " + (numTasks + 1) + " tasks in the list.");
                    numTasks++;
                    break;

                // Create a event task (contains "/at")
                case "event":
                    // Validate event
                    try {
                        InputValidation.validateEvent(userCommand, userTask);
                    } catch (DukeException e) {
                        System.out.println("Sorry, I can't do that event!");
                        break;
                    }
                    taskTokens = userTask.split(" /at ");
                    taskName = taskTokens[0];
                    String taskAt = taskTokens[1];
                    taskList.add(new Event(taskName, taskAt));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + taskList.get(numTasks));
                    System.out.println("Now you have " + (numTasks + 1) + " tasks in the list.");
                    numTasks++;
                    break;

                // Mark the identified task as done
                case "done":
                    int id = Integer.parseInt(userTokens[1]) - 1;
                    taskList.get(id).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + taskList.get(id));
                    break;
            }
        }
    }
}
