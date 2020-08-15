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
            } else if (command.startsWith("done")) {
                String[] split = command.split("\\s+");
                if (split.length == 2 && split[1].matches("[0-9]+")) {
                    int taskNumber = Integer.parseInt(split[1]) - 1;
                    list.doTask(taskNumber);
                    System.out.println(divider.wrapInDivider("Nice! I've marked this task as done:\n   " +
                            list.getTask(taskNumber)));
                }
            } else {
                list.addTask(command);
                System.out.println(divider.wrapInDivider("added: " + command));
            }
        }
    }

}
