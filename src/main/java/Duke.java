import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    private static final String spacing = "         ";
    private static final String divider = "_______________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

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
                String message = "";
                for (int i = 0; i < tasks.size(); i++) {
                    message += (i + 1) + ": " + tasks.get(i) + "\n";
                }
                printMessage(message);
            } else if (input.matches("done ([0-9]*)")) {
                int number = Integer.parseInt(input.split(" ")[1]);
                if (number > tasks.size()) {
                    printMessage("Task not found please choose another number!");
                }
                else if (number < 100 && number > 0) {
                    tasks.get(number - 1).markAsDone();
                    printMessage("This task is done, great job!\n" + tasks.get(number - 1));
                }
            } else {
                tasks.add(new Task(input));
                printMessage("Added: " + input);
            }

        }
    }

    private static void Greeting() {
        String logo = "\n" +
                "\n" +
                "██████╗  █████╗ ███████╗██╗  ██╗\n" +
                "██╔══██╗██╔══██╗██╔════╝██║  ██║\n" +
                "██║  ██║███████║███████╗███████║\n" +
                "██║  ██║██╔══██║╚════██║██╔══██║\n" +
                "██████╔╝██║  ██║███████║██║  ██║\n" +
                "╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n" +
                "                                \n" +
                "\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you today?");
        System.out.println(divider);
    }

    private static void printMessage(String message) {
        System.out.println(spacing + divider);
        String[] messages = message.split("\n");
        for (String str : messages) {
            System.out.println(spacing + str);
        }
        System.out.println(spacing + divider);

    }
}
