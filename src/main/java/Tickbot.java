import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tickbot {
    private static Scanner inputScanner = new Scanner(System.in);
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printMessage("hello, This is tickbot! How can I help you?");
        boolean running = true;
        while (running) {
            System.out.print("==> ");
            if (!inputScanner.hasNextLine()) {
                break;
            }
            String command = inputScanner.nextLine();
            running = processCommand(command);
        }
    }

    private static boolean processCommand(String command) {
        if (command.isBlank()) {
            return true; // empty line, continue inputing
        }
        String[] args = command.split("\\s+");
        switch (args[0]) {
            case "bye": {
                printMessage("See you next time!");
                return false; // end of input
            }
            case "done": {
                int index = Integer.parseInt(args[1]) - 1;
                Task task = tasks.get(index);
                task.setCompleted(true);
                printMessage("Task completed: " + task);
                break;
            }
            case "list": {
                printMessage("Task list:");
                for (int i = 0; i < tasks.size(); i++) {
                    String message = String.format("%d. %s", i + 1, tasks.get(i));
                    printMessage(message);
                }
                break;
            }
            default: {
                tasks.add(new Task(command));
                printMessage("Task added: " + command);
            }
        }
        return true; // continue inputing
    }

    private static void printMessage(String message) {
        System.out.println("  " + message);
    }
}
