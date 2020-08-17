import java.util.Scanner;
public class Duke {
    private static final String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

    private static final Scanner s = new Scanner(System.in);
    private static final String line = "     _____________________________________";

    public static void main(String[] args) {
        greet();
        while (s.hasNextLine()) {
            String userCommand = s.nextLine();
            if (userCommand.equals("bye")) {
                exit();
                break;
            } else {
                echo(userCommand);
            }
        }
    }
        private static void greet() {
            System.out.println(line);
            System.out.println("     Hi, I am\n" + logo);
            System.out.println("     Is there anything I could help with?");
            System.out.println(line);
        }

        private static void echo(String command) {
            System.out.println(line);
            System.out.println("     " + command);
            System.out.println(line);
        }

        private static void exit() {
            System.out.println(line);
            System.out.println("     Bye! I look forward to meeting you next time!");
            System.out.println(line);
        }
}
