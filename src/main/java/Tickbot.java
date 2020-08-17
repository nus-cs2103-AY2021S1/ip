import java.util.Objects;
import java.util.Scanner;

public class Tickbot {
    private static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        printMessage("hello, This is tickbot! How can I help you?");
        while (true) {
            System.out.print("==> ");
            String command = inputScanner.nextLine();
            if (Objects.equals(command, "bye")) {
                printMessage("See you next time!");
                break;
            } else {
                printMessage(command);
            }
        }
    }

    private static void printMessage(String message) {
        System.out.println("  " + message);
    }
}
