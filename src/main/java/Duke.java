import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String spacing = "     ";
    private static final String divider = "_______________________________________________________";
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Greeting();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                scanner.close();
                printMessage("Bye! See you next time :)");
                System.exit(0);
            } else if (input.equals("list")) {
                System.out.println(spacing + divider);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(spacing + (i + 1) + ": " + tasks.get(i));

                }
                System.out.println(spacing + divider);
            } else {
                tasks.add(input);
                printMessage("Added: " + input);
            }

        }
    }

    private static void Greeting() {
        String logo = "  _____            _     \n" +
                " |  __ \\          | |    \n" +
                " | |  | | __ _ ___| |__  \n" +
                " | |  | |/ _` / __| '_ \\ \n" +
                " | |__| | (_| \\__ \\ | | |\n" +
                " |_____/ \\__,_|___/_| |_|";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you today?");
        System.out.println(divider);
    }

    private static void printMessage(String message) {
        System.out.println(spacing + divider);
        System.out.println(spacing + message);
        System.out.println(spacing + divider);
    }
}
