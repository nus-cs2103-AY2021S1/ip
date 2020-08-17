import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static void respondStart() {
        System.out.println("    ----------------------------------------");
        System.out.println("     Hellowww!! I'm Alexa, your personal todo manager!");
        System.out.println("     How can I help you today?");
        System.out.println("    ----------------------------------------");
    }

    private static void respondClose() {
        System.out.println("     ----------------------------------------");
        System.out.println("     Bye? I hope it's not forever! Come back soon!");
        System.out.println("     ----------------------------------------");
    }

    private static String[] parseInput(String str) {
        String[] arr = str.split("\\W+", 2);
        if (arr.length < 2) {
            // TODO: handle error
            return arr;
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        List<Task> todoList = new ArrayList<>();

        // Flow starts here
        respondStart();

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }

            System.out.println("    ----------------------------------------");

            if (input.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.printf("     %d. %s%n", i + 1, todoList.get(i).showTask());
                }
            } else if (input.startsWith("done")) {
                if (input.matches("done\\W+\\d+")) {
                    // Only mark as done if the pattern is "done <positive number>"
                    int done = Integer.parseInt(input.split("\\W+")[1]);
                    if (done > 0 && done <= todoList.size()) {
                        todoList.get(done - 1).markAsDone();
                        System.out.println("     Good job! I've marked this task as done:");
                        System.out.printf("      %s%n", todoList.get(done - 1).showTask());
                    } else {
                        System.out.println("     Sorry, I can't find it in your list!");
                    }
                } else {
                    System.out.println("     Invalid format!");
                    System.out.println("     Usage: done <task number>");
                }

            } else {
                // add instructions
                String[] parsed = parseInput(input);
                String command = parsed[0];
                if (command.equals("todo")) {
                    todoList.add(new Todo(parsed[1]));
                } else if (command.equals("deadline")) {
                    String[] parsedDeadline = parsed[1].split(" /by ", 2);
                    todoList.add(new Deadline(parsedDeadline[0], parsedDeadline[1]));
                } else if (command.equals("event")) {
                    String[] parsedEvent = parsed[1].split(" /at ", 2);
                    todoList.add(new Event(parsedEvent[0], parsedEvent[1]));
                } else {
                    // TODO: handle unknown cases here
                    System.out.println("Invalid command!");
                    continue;
                }

                int size = todoList.size();
                System.out.println("     Got it. I've added this task:");
                System.out.printf("       %s%n", todoList.get(size - 1).showTask());
                System.out.printf("     Now you have %d %s in the list%n", size, size > 1 ? "tasks" : "task");
            }

            System.out.println("    ----------------------------------------");
        }

        respondClose();
    }
}
