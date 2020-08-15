import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        acceptCommand(sc);
    }

    private static void greet() {
        String greeting = "Hello! I'm Duke \nWhat can I do for you?";
        printResponse(greeting);
    }

    private static void acceptCommand(Scanner sc) {
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")){
            if (userInput.equals("list")) {
                printResponse(listTasks());
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(userInput.indexOf(' ') + 1);
                Task task = new ToDo(description);
                tasks.add(task);
                String response = String.format(
                        "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                        task, tasks.size()
                );
                printResponse(response);
            } else if (userInput.matches("done \\d+")) {
                int index = Integer.parseInt(userInput.split(" ")[1]);
                if (index > tasks.size()) {
                    printResponse("No such task!");
                } else {
                    Task task = tasks.get(index-1);
                    task.completeTask();
                    String response = "Nice! I've marked this task as done:\n  " + task.toString();
                    printResponse(response);
                }
            }

            userInput = sc.nextLine();
        }

        printResponse("Bye. Hope to see you again soon!");
    }

    // Wrapper method for printing with horizontal line borders and 1 tab indent
    private static void printResponse(String string) {
        String horizontalLine = "----------------------------------------------------------------";
        String formatted = String.format("%s\n%s\n%s", horizontalLine, string, horizontalLine)
                .replaceAll("(?m)^", "\t");
        System.out.println(formatted);
    }

    // Returns a formatted String of all the tasks
    private static String listTasks() {
        StringJoiner result = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.add(String.format("%d.%s", i+1, tasks.get(i)));
        }
        return result.toString();
    }
}
