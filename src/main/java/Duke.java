import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(formatResponse(greeting));

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")){
            if (userInput.equals("list")) {
                System.out.println(formatResponse(listTasks()));
            } else if (userInput.matches("done \\d+")) {
                String index = userInput.split(" ")[1];
                Task task = tasks.get(Integer.valueOf(index) - 1);
                task.completeTask();
                System.out.println(formatResponse("Nice! I've marked this task as done:\n  "
                        + task.toString()));
            } else {
                Task task = new Task(userInput);
                tasks.add(task);
                System.out.println(formatResponse("added: " + task.toString()));
            }

            userInput = sc.nextLine();
        }

        System.out.println(formatResponse("Bye. Hope to see you again soon!"));
    }

    // Formats a given String by adding horizontal lines to the top and bottom
    // as well as indenting 1 tab
    private static String formatResponse(String string) {
        String horizontalLine = "----------------------------------------------------------------";
        return String.format("%s\n%s\n%s", horizontalLine, string, horizontalLine)
                .replaceAll("(?m)^", "\t");
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
