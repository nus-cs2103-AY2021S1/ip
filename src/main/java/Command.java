import java.util.Scanner;

public class Command {
    private final Scanner scanner;
    private final Divider divider;

    public Command() {
        scanner = new Scanner(System.in);
        divider = new Divider();
    }

    public void getCommands() {
        while(scanner.hasNext()) {
            String command = scanner.next();
            if (command.equals("bye")) {
                System.out.println(divider.wrapInDivider("Bye. Hope to see you again soon!"));
                scanner.close();
                System.exit(0);
            } else {
                System.out.println(divider.wrapInDivider(command));
            }
        }
    }
}


