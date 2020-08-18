import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Task> taskList = new ArrayList<>();
    final static String line = "    ____________________________________________________________\n";

    public static void main(String[] args) {
        greeting();
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                listCommand();
            } else {
                addCommand(command);
            }
            command = scanner.nextLine();
        }
        goodbye();
    }

    public static void greeting() {
        String message = line
                + "      Eh what's up\n"
                + "      What do you want?\n"
                + line;
        System.out.println(message);
    }

    public static void goodbye() {
        String message = line
                + "     Alright I'll see you around!\n"
                + line;
        System.out.println(message);
    }

    public static void listCommand() {
        String message;
        message = line;
        for (int i = 0; i < taskList.size(); i++) {
            message = message
                    + "      "
                    + (i + 1)
                    + ". "
                    + taskList.get(i)
                    + "\n";
        }
        message = message + line;
        System.out.println(message);
    }

    public static void addCommand(String command) {
        Task task = new Task(command);
        taskList.add(task);
        String message = line
                + "      added: "
                + command
                + "\n"
                + line;
        System.out.println(message);
    }
}