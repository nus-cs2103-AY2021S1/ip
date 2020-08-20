import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /** Initialize objects required **/
        // Using Scanner for reading inputs
        Scanner scanner = new Scanner(System.in);
        // Initialize List for Duke to store stuff in
        /** Note: limit storage to 100 items **/
        Task[] list = new Task[100];

        // Text Images
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String lineBreaker = "--.--.--.--.--.--.--.--.--.--.--." +
                "--.--.--.--.--.--.--.--.--.--.--";
        String outputBreaker = ">>> ";


        // Greetings
        System.out.println("\nHello, I'm Duke!");
        System.out.println("What can I help you with today?");
        System.out.println("\n" + lineBreaker);
        System.out.println();

        // Read user input
        String userInput = scanner.nextLine();
        // System Loop
        // End only if user input is "bye"
        while (!userInput.equals("bye")) {
            // Process input
            String[] instructions = userInput.split(" ", 2);

            // React to commands
            // Command: "list"
            if (userInput.equals("list")) {
                printList(list);
            } else if (instructions[0].equals("todo")){
                // Command for to do Tasks creation
                System.out.print(outputBreaker);
                list[Task.quantity] = new Todo(instructions[1]);
                Task.quantity++;
                System.out.println("added: " + list[Task.quantity - 1]);
                System.out.println("There is now " + Task.quantity + " tasks in the list!");

            } else if (instructions[0].equals("deadline")){
                // Command: "deadline <taskName> /by <deadline>"
                System.out.print(outputBreaker);
                // Extract Details from command
                String[] details = instructions[1].split(" /by ", 2);
                list[Task.quantity] = new Deadline(details[0], details[1]);
                Task.quantity++;
                System.out.println("added: " + list[Task.quantity - 1]);
                System.out.println("There is now " + Task.quantity + " tasks in the list!");

            } else if (instructions[0].equals("event")){
                // Command: "deadline <taskName> /by <deadline>"
                System.out.print(outputBreaker);
                // Extract Details from command
                String[] details = instructions[1].split(" /at ", 2);
                list[Task.quantity] = new Event(details[0], details[1]);
                Task.quantity++;
                System.out.println("added: " + list[Task.quantity - 1]);
                System.out.println("There is now " + Task.quantity + " tasks in the list!");

            } else if (instructions[0].equals("done")){
                // Command: "done <task>"
                // If valid <task>, mark done
                try {
                    int taskNumber = Integer.parseInt(instructions[1]);

                    // Only add if entry exist entry exist at index
                    if (checkList(list, taskNumber)) {
                        list[taskNumber - 1].markedDone(true);
                        System.out.print(outputBreaker);
                        System.out.println("Congratulations! I've helped you mark the task as done:");
                        System.out.print("\t" + list[taskNumber - 1].toString() + "\n");
                    }
                } catch (NumberFormatException nfe) {
                    // Store it as a task
                    //System.out.println("Caught nfe");
                    System.out.print(outputBreaker);
                    list[Task.quantity] = new Task(userInput);
                    Task.quantity++;
                    System.out.println("added: " + userInput);

                } catch (IndexOutOfBoundsException e) {
                    // Store it as a task
                    //System.out.println("Caught index out of bounds");
                    System.out.print(outputBreaker);
                    list[Task.quantity] = new Task(userInput);
                    Task.quantity++;
                    System.out.println("added: " + userInput);
                }
            } else {
                //Default reaction to words
                // Store tasks by default
                System.out.print(outputBreaker);
                list[Task.quantity] = new Task(userInput);
                Task.quantity++;
                System.out.println("added: " + userInput);
            }

            System.out.println("\n" + lineBreaker + "\n");
            // Read user input once more
            userInput = scanner.nextLine();
        }

        // Farewell message
        System.out.print(outputBreaker);
        System.out.println("Bye! Hoped I helped!");
        System.out.println("\n" + lineBreaker);
    }

    /** Prints all the contents of the list in order **/
    public static void printList(Task[] list) {
        System.out.print("Here is what I have! ^^\n");
        // Handles printing empty list
        if (Task.quantity == 0) {
            System.out.println("Whoops! I don't have anything of note yet...");
        }
        for (int i = 0; i < Task.quantity; i++) {
            // Enumerator
            System.out.print((i+1) + ".");
            // Actual Task
            System.out.println(list[i]);
        }
    }

    /** Checks if index is valid **/
    public static boolean checkList(Task[] list, int id) {
        if (id <= 0 || id > Task.quantity) {
            return false;
        } else {
            return true;
        }
    }
}
