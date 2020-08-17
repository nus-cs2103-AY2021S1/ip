import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static boolean isValid(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String separator = "   -------------------------------------------------";

        List<Task> tasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            if (next.equals("bye")) {
                break;
            }
            System.out.println(separator);
            if (next.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("     " + (i + 1) + "." + tasks.get(i));
                }
            } else if (next.startsWith("done")) {
                int len = next.length();
                if (len >= 6 && isValid(next.substring(5, len))) {
                    int digit = Integer.parseInt(next.substring(5, len));

                    if (digit <= tasks.size() && digit > 0) {
                        Task current = tasks.get(digit - 1);
                        if (current.checkIfDone()) {
                            System.out.println("    Task has already been marked done!");
                        } else {
                            current.markAsDone();
                            System.out.println("    Nice! I've marked this task as done:");
                            System.out.println("      " + current);
                        }
                    } else {
                        System.out.println("    Task number does not exist in the list");
                    }
                } else {
                    System.out.println("    Input format is invalid");
                }
            } else { // Adding new task
                Task newTask = null;
                int startIndex;
                String description;
                String by;

                if (next.startsWith("todo")) {
                    startIndex = 5;
                    description = next.substring(startIndex);
                    newTask = new ToDo(description);
                } else if (next.startsWith("deadline")) {
                    int indexBy = next.indexOf(" /by ");
                    startIndex = 9;
                    description = next.substring(startIndex, indexBy);
                    by = next.substring(indexBy + 5);
                    newTask = new Deadline(description, by);
                } else if (next.startsWith("event")) {
                    int indexBy = next.indexOf(" /at ");
                    startIndex = 6;
                    description = next.substring(startIndex, indexBy);
                    by = next.substring(indexBy + 5);
                    newTask = new Event(description, by);
                }
                tasks.add(newTask);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + newTask);
                System.out.println("    Now you have " + tasks.size() + " tasks in your list.");
            }
            System.out.println(separator);
            System.out.println();
        }
        sc.close();
        System.out.println(separator);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
