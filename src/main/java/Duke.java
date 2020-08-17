import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {
        String name = "Omega";
        Duke.printHorizontalLine();
        System.out.println("Hi! I am " + name + ", your personal assistant.");
        System.out.println("How may I help you today?");
        Duke.printHorizontalLine();
        Duke.interactWithUser();
    }

    private static void interactWithUser() {
        boolean exitProgram = false;
        List<Task> listOfTasks = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
        while (!exitProgram) {
            System.out.println();
            String userInput = scn.nextLine();
            String[] userInputArgs = userInput.split(" ");
            Duke.printHorizontalLine();
            if (userInput.equals("bye")) {
                System.out.println("Goodbye! Shutting down now...");
                exitProgram = true;
            } else if (userInput.equals("list")) {
                int n = listOfTasks.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < n; i++) {
                    System.out.println(String.format("%d.%s", i + 1, listOfTasks.get(i)));
                }
            } else if (userInputArgs[0].equals("done")) {
                int idx = Integer.parseInt(userInputArgs[1]);
                Task task = listOfTasks.get(idx - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task);
            } else {
                Task task = createTask(userInputArgs);
                listOfTasks.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println(String.format("Now you have %d tasks in the list.", listOfTasks.size()));
            }
            Duke.printHorizontalLine();
        }
    }

    private static Task createTask(String[] userInputArgs) {
        if (userInputArgs[0].equals("todo")) {
            String description = Duke.reassembleString(userInputArgs, 1, userInputArgs.length);
            return new Todo(description);
        } else if (userInputArgs[0].equals("deadline")) {
            int byIdx = Arrays.asList(userInputArgs).indexOf("/by");
            String description = Duke.reassembleString(userInputArgs, 1, byIdx);
            String by = Duke.reassembleString(userInputArgs, byIdx + 1, userInputArgs.length);
            return new Deadline(description, by);
        } else if (userInputArgs[0].equals("event")) {
            int atIdx = Arrays.asList(userInputArgs).indexOf("/at");
            String description = Duke.reassembleString(userInputArgs, 1, atIdx);
            String at = Duke.reassembleString(userInputArgs, atIdx + 1, userInputArgs.length);
            return new Event(description, at);
        } else {
            throw new IllegalArgumentException("Invalid command.");
        }
    }

    private static String reassembleString(String[] arr, int from, int to) {
        return String.join(" ", Arrays.copyOfRange(arr, from, to));
    }

    private static void printHorizontalLine() {
        String horizontalLine = "---------------------------------------------";
        System.out.println(horizontalLine);
    }
}
