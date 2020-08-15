import java.util.Scanner;

public class Duke {
    private final Divider divider;
    private final Scanner scanner;
    private final ToDoList list;

    public Duke() {
        divider = new Divider();
        scanner = new Scanner(System.in);
        list = new ToDoList();
    }

    public void sendGreeting() {
        System.out.println(divider.wrapInDivider("Hello! I'm Duke\n What can I do for you?"));
    }

    public void receiveCommands() {
        while(scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(divider.wrapInDivider("Bye. Hope to see you again soon!"));
                scanner.close();
                System.exit(0);
            } else if (command.equals("list")) {
                System.out.println(divider.wrapInDivider(list.toString()));

            } else {
                list.addTask(command);
                System.out.println(divider.wrapInDivider("added: " + command));
            }
        }
    }

}
