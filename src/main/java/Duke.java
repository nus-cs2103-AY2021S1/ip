import java.util.Scanner;

public class Duke {
    private static String logo =
                      " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|";

    private static void greet() {
        System.out.println(logo);
        System.out.println("Hello! This is Duke.\nWhat can I do for you?");
    }

    private static boolean isExit(String line) {
        return line.toLowerCase().contains("bye");
    }

    private static void exit() {
        System.out.println("Bye. Duke is always there for you!");
        System.out.println(logo);
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (true) {
            if (isExit(line)) {
                exit();
                break;
            }
            else {
                System.out.println(line);
                line = scanner.nextLine();
            }
        }

    }

    public static void main(String[] args) {
        greet();
        echo();
    }
}
