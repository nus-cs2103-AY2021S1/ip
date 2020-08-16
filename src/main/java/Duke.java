import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
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

    private static void printMessage(String message) {
        System.out.println("___________________________");
        System.out.println(message);
        System.out.println("___________________________");
    }
}
