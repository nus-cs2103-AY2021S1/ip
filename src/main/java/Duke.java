import java.util.Scanner;

public class Duke {
    private static final String spacing = "     ";
    private static final String divider = "_______________________________________________________";

    public static void main(String[] args) {
        Greeting();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                scanner.close();
                printMessage("Bye! See you next time :)");
                System.exit(0);
            }
            printMessage(input);
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
