import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private String logo, greetingMessage, exitMessage;

    public void initialise() {
        scanner = new Scanner(System.in);
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greetingMessage = "Hello! My name is Duke.\n" + "What can I do for you?";
        exitMessage = "Bye. Hope to see you again soon!";
        greet();
        listenForCommands();
    }

    private void greet() {
        System.out.println(logo);
        System.out.println(greetingMessage);
    }

    private void listenForCommands() {
        String command = scanner.nextLine();
        switch (command) {
            case ("bye"):
                System.out.println(exitMessage);
                break;
            default:
                System.out.println(command);
                listenForCommands();
        }
    }
}