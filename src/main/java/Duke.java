import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    private static List<String> commands = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);

        greet();
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                exit();
            } else if (command.equals("list")){
                printList();
            } else {
                add(command);
            }
        }
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void echo(String command) {
        System.out.println("" + command);
    }

    private static void add(String command) {
        commands.add(command);
        System.out.println("added: " + command);
    }

    private static void printList() {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i) != null) {
                System.out.println((i + 1) + ". " + commands.get(i));
            }
        }
    }
}
