import java.util.Scanner;

public class Duke {
    private final String line = "------------------------------------";
    private final String stringFormat = line + "\n%s\n" + line + "\n\n";

    private Scanner scanner;

    private Duke() {
        this.scanner = new Scanner(System.in);
        greet();
    }

    private void greet() {
        System.out.printf(stringFormat, "Hello! I'm Duke\nWhat can I do for you?");
    }

    private void echo(String str) {
        System.out.printf(stringFormat, str);
    }

    private void processBye() {
        System.out.printf(stringFormat, "Bye. Hope to see you again soon!");
    }

    private boolean processAction() {
        String action = this.scanner.nextLine();
        switch (action) {
            case "bye":
                this.processBye();
                return false;
            default:
                this.echo(action);
                break;
        }
        return true;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        boolean status;
        do {
            status = duke.processAction();
        } while (status);

    }
}
