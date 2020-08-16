import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {
    String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    String indent = "        ";

    public void start() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(indent + "What can I do for you delightful human?\n" + divider);

        loop: while(scanner.hasNext()) {
            String input = scanner.nextLine();
            System.out.println(divider);

            if (input.startsWith("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                Task curr = tasks.get(index - 1);
                curr.markAsDone();
                System.out.println(indent + "Good job! I've marked this task as done:");
                System.out.println(indent + "  [" + curr.getStatusIcon() + "] " + curr.getDescription());
                System.out.println(divider);
            } else if (input.equals("list")) {
                System.out.println(indent + "Here are your tasks for today:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task curr = tasks.get(i);
                    System.out.println(indent + (i + 1) + ".[" + curr.getStatusIcon() +
                            "] " + curr.getDescription());
                }
                System.out.println(divider);
            } else if (input.equals("bye")) { // Termination statement
                System.out.println(indent + "Guess its time for us to part ways\n"
                        + indent + "Thanks for the memories\n" + indent + ":`(");
                System.out.println(divider);
                break;
            } else { // Echo back user input
                Task curr = new Task(input);
                tasks.add(curr);
                System.out.println(indent + "Added: " + curr.getDescription());
                System.out.println(divider);
            }
        }
    }
}
