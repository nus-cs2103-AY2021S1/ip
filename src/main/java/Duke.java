import java.util.ArrayList;
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
        ArrayList<String> list = new ArrayList<>();
        String command = scanner.nextLine();
        String message;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                message = line;
                for (int i = 0; i < list.size(); i++) {
                    message = message
                            + "      "
                            + (i + 1)
                            + ". "
                            + list.get(i)
                            + "\n";
                }
                message = message + line;
            } else {
                message = line
                        + "      added: "
                        + command
                        + "\n"
                        + line;
                list.add(command);
            }
            System.out.println(message);
            command = scanner.nextLine();
        }
        message = line
                + "     Alright I'll see you around!\n"
                + line;
        System.out.println(message);
    }
}