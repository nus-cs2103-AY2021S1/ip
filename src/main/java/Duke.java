import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /** Initialize objects required **/
        // Using Scanner for reading inputs
        Scanner scanner = new Scanner(System.in);
        // Initialize List for Duke to store stuff in
        /** Note: limit storage to 100 items **/
        ArrayList<Task> list = new ArrayList<>();

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
        System.out.println("Hello, I'm\n" + logo);
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
            } else if (instructions[0].equals("done")){
                // Command: "done <task>"
                // If valid <task>, mark done
                try {
                    int taskNumber = Integer.parseInt(instructions[1]);
                    list.get(taskNumber - 1).markedDone(true);
                    System.out.print(outputBreaker);
                    System.out.println("Congratulations! I've helped you mark the task as done:");
                    System.out.print("[✓] " + list.get(taskNumber - 1) + "\n");

                } catch (NumberFormatException nfe) {
                    // Store it as a task
                    //System.out.println("Caught nfe");
                    System.out.print(outputBreaker);
                    list.add(new Task(userInput));
                    System.out.println("added: " + userInput);

                } catch (IndexOutOfBoundsException e) {
                    // Store it as a task
                    //System.out.println("Caught index out of bounds");
                    System.out.print(outputBreaker);
                    list.add(new Task(userInput));
                    System.out.println("added: " + userInput);
                }
            } else {
                //Default reaction to words
                // Store tasks by default
                System.out.print(outputBreaker);
                list.add(new Task(userInput));
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
    public static void printList(ArrayList<Task> list) {
        System.out.print("\nHere is what I have! ^^\n");
        for (int i = 0; i < list.size(); i++) {
            // Enumerator
            System.out.print((i+1) + ".");
            // Status
            if (!list.get(i).getStatus()) {
                System.out.print("[✗] ");
            } else {
                System.out.print("[✓] ");
            }
            // Actual Task
            System.out.println(list.get(i));
        }
    }
}
