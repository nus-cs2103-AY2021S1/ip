import java.util.Scanner;

public class Duke {
    private Scanner scanner;

    private static final int MAXSIZE = 100;
    private String[] todoList;
    private int todoListSize;

    private String logo, greetingMessage, exitMessage;

    public void initialise() {
        scanner = new Scanner(System.in);
        todoList = new String[MAXSIZE];
        todoListSize = 0;
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

    private void list() {
        for (int i = 0; i < todoListSize; i++) {
            int number = i + 1;
            String entry = String.format("%d. %s", number, todoList[i]);
            System.out.println(entry);
        }
    }

    private void addToList(String item) {
        todoList[todoListSize] = item;
        todoListSize++;
        System.out.println("added: " + item);
    }

    private void exit() {
        System.out.println(exitMessage);
        System.exit(0);
    }

    private void listenForCommands() {
        String command = scanner.nextLine();
        switch (command) {
            case ("list"):
                list();
                break;
            case ("bye"):
                exit();
                break;
            default:
                addToList(command);
        }
        listenForCommands();
    }
}