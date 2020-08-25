import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/** Duke is a chatbot that allows users to send input to perform tasks.
 */
public class Duke {
    public static void main(String[] args) {
        // Introduction messages
        System.out.println("Hello! I'm Duke! I'm a chatbot-based To-Do list manager.");
        System.out.println("My available commands are: todo, deadline, event, done, list, delete, bye");
        System.out.println("What can I do for you today? :)");

        // Load from saved file
        DukeSaver dukeSaver = null;
        try {
            dukeSaver = new DukeSaver("data/duke.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialise list of tasks
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            taskList = dukeSaver.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


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
                System.out.println("Sorry, that looks like an invalid command! " + e.getMessage());
            }

            switch(userCommand) {

                // Exit the program
                case "bye":
                    isSpeaking = false;
                    System.out.println("Bye! Hope to see you again soon!");
                    break;

                // List the tasks available in taskList
                case "list":
                    if (taskList.isEmpty()) {
                        System.out.println("You have no remaining tasks! Cheers!");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println(i + 1 + "." + taskList.get(i));
                        }
                    }
                    break;

                // Create a to-do task
                case "todo":
                    try {
                        // Validate task
                        InputValidation.validateTask(userCommand, userTask);

                        taskList.add(new Todo(userTask));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + taskList.get(taskList.size() - 1));
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

                        // Save list of tasks to disk.
                        dukeSaver.save(taskList);

                    } catch (DukeException e) {
                        System.out.println("Sorry, I can't add that task! " + e.getMessage());
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;


                // Create a deadline task (contains "/by")
                case "deadline":
                    try {
                        // Validate deadline
                        InputValidation.validateDeadline(userCommand, userTask);

                        taskTokens = userTask.split(" /by ");
                        taskName = taskTokens[0];
                        String taskBy = taskTokens[1];
                        taskList.add(new Deadline(taskName, taskBy));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + taskList.get(taskList.size() - 1));
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

                        dukeSaver.save(taskList);
                    } catch (DukeException e) {
                        System.out.println("Sorry, I can't add that deadline! " + e.getMessage());
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                // Create a event task (contains "/at")
                case "event":
                    try {
                        // Validate event
                        InputValidation.validateEvent(userCommand, userTask);

                        taskTokens = userTask.split(" /at ");
                        taskName = taskTokens[0];
                        String taskAt = taskTokens[1];
                        taskList.add(new Event(taskName, taskAt));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + taskList.get(taskList.size() - 1));
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

                        // Save list of tasks to disk.
                        dukeSaver.save(taskList);
                    } catch (DukeException e) {
                        System.out.println("Sorry, I can't add that event! " + e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                // Mark the identified task as done
                case "done":
                    try {
                        // Validate done
                        InputValidation.validateIdentifier(userInput, userTokens);

                        int id = Integer.parseInt(userTokens[1]) - 1;
                        taskList.get(id).setDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + taskList.get(id));

                        // Save list of tasks to disk.
                        dukeSaver.save(taskList);
                    } catch (DukeException e) {
                        System.out.println("Sorry, I can't mark that as done! " + e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                // Delete a task
                case "delete":
                    try {
                        System.out.println("validating delete");
                        InputValidation.validateIdentifier(userInput, userTokens);
                        Task deletedTask = taskList.remove(Integer.parseInt(userTokens[1]) - 1);
                        System.out.println("Noted. I have removed this task:");
                        System.out.println("  " + deletedTask);

                        // Save list of tasks to disk.
                        dukeSaver.save(taskList);
                    } catch (DukeException e) {
                        System.out.println("Sorry, I can't delete that task! " + e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
