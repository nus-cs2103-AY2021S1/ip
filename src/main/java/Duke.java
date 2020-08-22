import Task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        introduction();
        Scanner sc  = new Scanner(System.in);
        interact(sc);
        sc.close();
    }

    public static void introduction() {
        String greeting = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greeting);
    }

    public static void interact(Scanner sc) {
        String command = sc.nextLine();
        List<Task> list = new ArrayList<>();
        CommandProcessor commandProcessor = new CommandProcessor(list);
        while (!command.equals("bye")) {
            commandProcessor.runCommand(command);
            command = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n"
        );
    }
}
