import java.util.Scanner;

public class Duke {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String line = "    ____________________________________________________________\n";
        String greeting = line
                + "      Eh what's up\n"
                + "      What do you want?\n"
                + line;
        System.out.println(greeting);

        String command = scanner.nextLine();
        String message;
        while (!command.equals("bye")) {
            message = line
                    + "      "
                    + command
                    + "\n"
                    + line;
            System.out.println(message);
            command = scanner.nextLine();
        }
        message = line
                + "     Alright I'll see you around!\n"
                + line;
        System.out.println(message);
    }
}