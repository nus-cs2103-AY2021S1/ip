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
                    curr = new Todo(input.substring(5));
                    tasks.add(curr);
                } else if (input.startsWith("deadline")) { // Create Deadline task
                    String[] splicedInput = input.substring(9).split(" /by ");
                    curr = new Deadline(splicedInput[0], splicedInput[1]);
                    tasks.add(curr);
                } else { // Create Event task
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
            } else {
                Task curr = new Task(input);
                tasks.add(curr);
                System.out.println(indent + "Added: " + curr);
                System.out.println(divider);
            }
        }
    }
}
