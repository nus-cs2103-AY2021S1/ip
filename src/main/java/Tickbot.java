import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Tickbot {
    private static Scanner inputScanner = new Scanner(System.in);
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printMessage("hello, This is tickbot! How can I help you?");
        boolean running = true;
        while (running) {
            System.out.print("==> ");
            if (!inputScanner.hasNextLine()) {
                break;
            }
            String command = inputScanner.nextLine();
            switch (command) {
                case "bye": {
                    printMessage("See you next time!");
                    running = false;
                    break;
                }
                case "list": {
                    for (int i = 0; i < tasks.size(); i++) {
                        int index = i + 1;
                        printMessage(index + ". " + tasks.get(i));
                    }
                    break;
                }
                default: {
                    tasks.add(command);
                    printMessage("Task added: " + command);
                }
            }
        }
    }

    private static void printMessage(String message) {
        System.out.println("  " + message);
    }
}
