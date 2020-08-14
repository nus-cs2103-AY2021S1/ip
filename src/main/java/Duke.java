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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        List<Task> todoList = new ArrayList<>();

        // Flow starts here
        respondStart();

        while (sc.hasNextLine()) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }

            System.out.println("    ----------------------------------------");

            if (command.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.printf("     %d. %s%n", i + 1, todoList.get(i).showTask());
                }
            } else if (command.startsWith("done")) {
                if (command.matches("done(\\W+)(\\d)+")) {
                    // Only mark as done if the pattern is "done <positive number>"
                    int done = Integer.parseInt(command.split("\\W+")[1]);
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
                todoList.add(new Task(command));
                System.out.printf("     added: %s%n", command);
            }

            System.out.println("    ----------------------------------------");
        }

        respondClose();
    }
}
