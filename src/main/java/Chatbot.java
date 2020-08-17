import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {
    String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    String indent = "        ";

    public void start() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(indent + "What can I do for you delightful human?\n" + divider);

        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            System.out.println(divider);

            if (input.startsWith("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                Task curr = tasks.get(index - 1);
                curr.markAsDone();
                System.out.println(indent + "Good job! I've marked this task as done:");
                System.out.println(indent + "  " + curr);
                System.out.println(divider);
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                Task curr;
                if (input.startsWith("todo")) { // Create Todo task
                    // Input does not contain the required keyword
                    if (input.equals("todo") || input.substring(5).isEmpty()) {
                        System.out.println(indent + "My apologies sir but the description of todo cannot be empty :(");
                        System.out.println(divider);
                        continue;
                    }
                    curr = new Todo(input.substring(5));
                    tasks.add(curr);
                } else if (input.startsWith("deadline")) { // Create Deadline task
                    // Input does not contain the required keyword
                    if (input.equals("deadline") || !input.substring(5).contains("/by")) {
                        System.out.println(indent + "My apologies sir but you will have to use the correct " +
                                "format to create a deadline :(");
                        System.out.println(divider);
                        continue;
                    }
                    String[] splicedInput = input.substring(9).split(" /by ");
                    curr = new Deadline(splicedInput[0], splicedInput[1]);
                    tasks.add(curr);
                } else { // Create Event task
                    // Input does not contain the required keyword
                    if (input.equals("event") || !input.substring(5).contains("/at")) {
                        System.out.println(indent + "My apologies sir but you will have to use the correct " +
                                "format to create a event :(");
                        System.out.println(divider);
                        continue;
                    }
                    String[] splicedInput = input.substring(6).split(" /at ");
                    curr = new Event(splicedInput[0], splicedInput[1]);
                    tasks.add(curr);
                }
                System.out.println(indent + "Affirmative. I've added this task:\n  " +
                        indent + curr);
                System.out.println(indent + "Now you have " + tasks.size() + " tasks in the list.");
                System.out.println(divider);
            } else if (input.equals("list")) {
                System.out.println(indent + "Here are your tasks for today:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task curr = tasks.get(i);
                    System.out.println(indent + (i + 1) + "." + curr);
                }
                System.out.println(divider);
            } else if (input.equals("bye")) { // Termination statement
                System.out.println(indent + "Guess its time for us to part ways\n"
                        + indent + "Thanks for the memories\n" + indent + ":`(");
                System.out.println(divider);
                break;
            } else { // Invalid command
                System.out.println(indent + "Sorry sir but I don't know what you mean :(");
                System.out.println(divider);
            }
        }
    }
}
